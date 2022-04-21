package wazzle.view.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wazzle.controller.common.WazzleController;
import wazzle.controller.common.WazzleControllerImpl;
import wazzle.model.maingame.Difficulty;
import wazzle.view.FXMLFiles;
import wazzle.view.SceneSwitcher;

public final class SettingsView extends View<WazzleController>{

	@FXML
	private VBox mainSettingWindow;

	@FXML
	private Slider gridDimensionSlider;

	@FXML
	private VBox mainSettingsWrapper;

	@FXML
	private ComboBox<String> difficultySelectorCBox;

	@FXML
	private Label difficultySelectorLabel;

	@FXML
	private Label gridDimensionLabel;

	@FXML
	private Button okButton;

	@FXML
	private Button cancelButton;

	public SettingsView(final Stage stage) {
		this.stage = stage;
		this.visualUnit = new SimpleDoubleProperty();
		this.visualUnit.bind(Bindings.min(stage.heightProperty().multiply(0.05), stage.widthProperty().multiply(0.05)));
		this.controller = (WazzleControllerImpl) stage.getUserData();
		this.onClose();
	}

	@Override
	public void nextScene(ActionEvent event) throws IOException {
		final Node node = (Node) event.getSource();

		if (node.getId().equals("okButton")) {
			final var name = this.difficultySelectorCBox.getValue();
			final var gridShape = (int) this.gridDimensionSlider.getValue();
			
			this.controller.getSettingsController().setCurrentDifficulty(this.controller
					.getSettingsController()
					.getDifficultyByNameAndShape(name, gridShape)
					.get());
			
			this.controller.saveSettings();
		}
		
		this.stage.setUserData(this.controller);
		SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), FXMLFiles.MAIN_MENU.getPath());
	}

	@Override
	protected void buildView() {
		this.setGraphics();
		
		this.difficultySelectorCBox.getItems().addAll(this.controller
				.getSettingsController()
				.getAllDifficulties()
				.stream()
				.map(Difficulty::getDifficultyName)
				.distinct()
				.collect(Collectors.toList()));
		
		System.out.println(this.controller.getSettingsController().getAllDifficulties());
		System.out.println(this.controller.getSettingsController().getCurrentDifficulty());

		this.difficultySelectorCBox.getSelectionModel().select(this.controller.getSettingsController().getCurrentDifficulty().getDifficultyName());
		this.gridDimensionSlider.setValue(this.controller.getSettingsController().getCurrentDifficulty().getGridShape());
	}

	@Override
	protected void setGraphics() {
		final ObservableValue<String> fontSize = Bindings.concat("-fx-font-size: ", visualUnit.asString(), ";");
		final ObservableValue<String> cbBoxItemFont = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.7).asString(), ";");
		final ObservableValue<String> paddingValue = Bindings.concat("-fx-padding: ",
				visualUnit.multiply(0.5).asString(), ";");

		this.difficultySelectorCBox.styleProperty().bind(cbBoxItemFont);
		this.difficultySelectorCBox.getStyleClass().add("longIncave");
		this.gridDimensionSlider.maxWidthProperty().bind(visualUnit.multiply(10));
		this.mainSettingWindow.getStyleClass().add("letters");
		this.mainSettingWindow.styleProperty().bind(paddingValue);
		this.mainSettingWindow.getChildren().forEach(e -> e.styleProperty().bind(paddingValue));
		this.mainSettingsWrapper.spacingProperty().bind(visualUnit);

		this.difficultySelectorLabel.styleProperty().bind(fontSize);
		this.gridDimensionLabel.styleProperty().bind(fontSize);
		this.difficultySelectorCBox.maxWidthProperty().bind(gridDimensionSlider.widthProperty());
		this.okButton.styleProperty().bind(fontSize);
		this.cancelButton.styleProperty().bind(fontSize);
	}

}