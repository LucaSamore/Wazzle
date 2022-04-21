package wazzle.view.controller;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
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
//			final var sliderValue = (int) this.gridDimensionSlider.getValue();
		//	this.controller.getSettingsController().updateSettings(this.controller.getSettingsController().getAllDifficulties().get(difficultySelectorCBox.getValue()).get(sliderValue),sliderValue);
			this.controller.saveSettings();
		}
		this.stage.setUserData(this.controller);
		SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), FXMLFiles.MAIN_MENU.getPath());
	}

	@Override
	protected void buildView() {
		this.setGraphics();
//		this.controller.getCurrentDifficulty().getAllDifficulties().keySet()
//				.forEach(e -> difficultySelectorCBox.getItems().add(e));

//		this.difficultySelectorCBox.getSelectionModel().select(this.controller.getCurrentDifficulty().getCurrentDifficultyName());
//		this.gridDimensionSlider.setValue(this.controller.getCurrentDifficulty().getCurrentGridShape());
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