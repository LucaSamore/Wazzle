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
import wazzle.controller.maingame.MainGameController;
import wazzle.controller.maingame.MainGameControllerImpl;
import wazzle.controller.minigame.MiniGameController;
import wazzle.controller.minigame.MiniGameControllerImpl;
import wazzle.model.minigame.MiniGame.State;
import wazzle.view.SceneSwitcher;
import wazzle.view.WindowCloser;

public class StatisticsMiniGame {
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

	private static final String MAIN_MENU_PATH = "layouts/mainMenu.fxml";
	private Stage stage;
	private DoubleProperty visualUnit;
	private MiniGameController miniGameController;

	public StatisticsMiniGame(Stage stage) {
		this.stage = stage;
		visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(stage.heightProperty(), stage.widthProperty()));

		this.miniGameController = (MiniGameControllerImpl) this.stage.getUserData();
	}

	public void initialize() {
		setGraphic();
	}

	private void setGraphic() {
		this.targetWordValueLabel.setText("" + this.miniGameController.getTargetWord());
		this.attemptsValueLabel.setText("" + this.miniGameController.getCurrentAttemptsNumber());
		
		if(miniGameController.getState() == State.FAILED) {
			resultValueLabel.setText("Hai perso!");
		}

		ObservableValue<String> fontSizeValue = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.05).asString(),
				";");
		ObservableValue<String> paddingValue = Bindings.concat("-fx-padding: ", visualUnit.multiply(0.05).asString(),
				";");

		mainStatisticWindow.maxHeightProperty().bind(mainStatisticWindow.widthProperty());
		mainStatisticWindow.minWidthProperty().bind(visualUnit.multiply(0.7));
		mainStatisticWindow.styleProperty().bind(Bindings.concat(fontSizeValue, paddingValue));

		mainWrapper.spacingProperty().bind(visualUnit.multiply(0.02));
		buttonWrapper.spacingProperty().bind(visualUnit.multiply(0.02));

		exitButton.styleProperty().bind(fontSizeValue);
		playAgainButton.styleProperty().bind(fontSizeValue);

		targetWordLabel.styleProperty()
				.bind(Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.025).asString(), ";"));
		attemptLabel.styleProperty()
				.bind(Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.025).asString(), ";"));
	}

	public void goToScene(ActionEvent event) throws IOException {
		this.stage.setUserData(new WazzleControllerImpl());
		SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), MAIN_MENU_PATH);

	}

}
