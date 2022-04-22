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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wazzle.controller.common.WazzleControllerImpl;
import wazzle.controller.minigame.MiniGameController;
import wazzle.controller.minigame.MiniGameControllerImpl;
import wazzle.model.minigame.MiniGame.State;
import wazzle.view.FXMLFiles;
import wazzle.view.SceneSwitcher;

public class StatisticsMiniGameView extends View<MiniGameController>{
	@FXML
	private VBox mainStatisticWindow;

	@FXML
	private VBox mainWrapper;

	@FXML
	private HBox buttonWrapper;

	@FXML
	private Button playAgainButton;

	@FXML
	private Button exitButton;

	@FXML
	private Label targetWordLabel;

	@FXML
	private Label targetWordValueLabel;

	@FXML
	private Label attemptLabel;

	@FXML
	private Label attemptsValueLabel;

	@FXML
	private Label resultLabel;

	@FXML
	private Label resultValueLabel;

	@FXML
	private Label bonusLabel;

	@FXML
	private Label bonusValueLabel;

	
	private Stage stage;
	private DoubleProperty visualUnit;
	private MiniGameController miniGameController;

	public StatisticsMiniGameView(Stage stage) {
		this.stage = stage;
		visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(stage.heightProperty(), stage.widthProperty()));
		this.miniGameController = (MiniGameControllerImpl) this.stage.getUserData();
	}

	@Override
	public void nextScene(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();
		if ("playAgainButton".equals(node.getId())) {
			this.stage.setUserData(new MiniGameControllerImpl(this.miniGameController.getMainController()));
			SceneSwitcher.<MiniGameView>switchScene(event, new MiniGameView(this.stage), FXMLFiles.MINI_GAME.getPath());
		} else {
			this.stage.setUserData(this.miniGameController.getMainController());
			SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), FXMLFiles.MAIN_MENU.getPath());
		}		
	}

	@Override
	protected void buildView() {
		setGraphics();		
	}

	@Override
	protected void setGraphics() {
		this.targetWordValueLabel.setText("" + this.miniGameController.getTargetWord());
		this.attemptsValueLabel.setText("" + this.miniGameController.getCurrentAttemptsNumber());

		try {
			this.miniGameController.obtainedBonus().ifPresent(b -> {
				this.resultValueLabel.setText("Hai vinto!");
				this.bonusValueLabel.setText(b);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableValue<String> fontSizeValue = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.05).asString(),
				";");
		ObservableValue<String> smallerFontSizeValue = Bindings.concat("-fx-font-size: ",
				visualUnit.multiply(0.05).asString(), ";");
		ObservableValue<String> paddingValue = Bindings.concat("-fx-padding: ", visualUnit.multiply(0.05).asString(),
				";");

		this.mainStatisticWindow.maxHeightProperty().bind(mainStatisticWindow.widthProperty());
		this.mainStatisticWindow.minWidthProperty().bind(visualUnit.multiply(0.7));
		this.mainStatisticWindow.styleProperty().bind(Bindings.concat(fontSizeValue, paddingValue));

		this.mainWrapper.spacingProperty().bind(visualUnit.multiply(0.02));
		this.buttonWrapper.spacingProperty().bind(visualUnit.multiply(0.02));

		this.exitButton.styleProperty().bind(fontSizeValue);
		this.playAgainButton.styleProperty().bind(fontSizeValue);

		this.targetWordLabel.styleProperty().bind(smallerFontSizeValue);
		this.attemptLabel.styleProperty().bind(smallerFontSizeValue);
		this.bonusLabel.styleProperty().bind(smallerFontSizeValue);		
	}

}
