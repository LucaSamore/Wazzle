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
import wazzle.view.FXMLFiles;
import wazzle.view.SceneSwitcher;
import wazzle.view.WindowCloser;

public class StatisticsMainGameView {
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
	private Label wordsFoundLabel;

	@FXML
	private Label wordsFoundValueLabel;

	@FXML
	private Label separatorWordLabel;

	@FXML
	private Label totalWordsValueLabel;

	@FXML
	private Label pointsLabel;

	@FXML
	private Label pointsValueLabel;

	@FXML
	private Label longestWordFoundLabel;

	@FXML
	private Label longestWordFoundValueLabel;

	@FXML
	private Label mostValuableWordLabel;

	@FXML
	private Label mostValuableWordValueLabel;

	private Stage stage;
	private DoubleProperty visualUnit;
	private MainGameController mainGameController;

	public StatisticsMainGameView(Stage stage) {
		this.stage = stage;
		visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(stage.heightProperty(), stage.widthProperty()));
		
		this.mainGameController = (MainGameControllerImpl) this.stage.getUserData();
		WindowCloser.onExit(this.stage);
	}

	public void initialize() {
		setGraphic();
	}

	private void setGraphic() {
		this.mainGameController.getGame().get().wordsFound();
		this.wordsFoundValueLabel.setText(""+ (int)this.mainGameController.getGame().get().wordsFound().size());
		this.totalWordsValueLabel.setText(""+ (int)this.mainGameController.getGame().get().wordsToBeFound().size());
		this.pointsValueLabel.setText(""+ (int)this.mainGameController.getGame().get().getCurrentScore());
		this.longestWordFoundValueLabel.setText(""+ this.mainGameController.longestWord());
		this.mostValuableWordValueLabel.setText(""+ this.mainGameController.highestScoreWord());
		
		ObservableValue<String> fontSizeValue = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.05).asString(), ";");
		ObservableValue<String> paddingValue = Bindings.concat("-fx-padding: ", visualUnit.multiply(0.05).asString(), ";");
		
		mainStatisticWindow.maxHeightProperty().bind(mainStatisticWindow.widthProperty());
		
		mainStatisticWindow.minWidthProperty().bind(visualUnit.multiply(0.7));

		mainWrapper.spacingProperty().bind(visualUnit.multiply(0.02));
		buttonWrapper.spacingProperty().bind(visualUnit.multiply(0.02));
		
		mainStatisticWindow.styleProperty().bind(Bindings.concat(fontSizeValue, paddingValue));
		exitButton.styleProperty().bind(fontSizeValue);
		playAgainButton.styleProperty().bind(fontSizeValue);
		
		
		longestWordFoundLabel.styleProperty()
				.bind(Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.025).asString(), ";"));
		mostValuableWordLabel.styleProperty()
				.bind(Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.025).asString(), ";"));
	}

	public void goToScene(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();
		
		switch (node.getId()) {

		case "exitButton":
			this.stage.setUserData(new WazzleControllerImpl());
			SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), FXMLFiles.MAIN_MENU.getPath());
			break;

		case "playAgainButton":
			this.stage.setUserData(new MainGameControllerImpl(this.mainGameController.getMainController()));
			SceneSwitcher.<LoadingView>switchScene(event, new LoadingView(this.stage), FXMLFiles.LOADING_SCREEN.getPath());
			break;

		default:
			break;
		}
	}
}
