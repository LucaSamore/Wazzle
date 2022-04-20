package wazzle.view.controller;

import java.io.IOException;

import javafx.beans.binding.Bindings;
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

public final class StatisticsMainGameView extends View<MainGameController>{
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

	public StatisticsMainGameView(Stage stage) {
		this.stage = stage;
		this.visualUnit = new SimpleDoubleProperty();
		this.visualUnit.bind(Bindings.min(stage.heightProperty(), stage.widthProperty()));
		this.controller = (MainGameControllerImpl) this.stage.getUserData();
		this.onClose();
	}

	@Override
	public void nextScene(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();
		
		switch (node.getId()) {

		case "exitButton":
			this.stage.setUserData(new WazzleControllerImpl());
			SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), FXMLFiles.MAIN_MENU.getPath());
			break;

		case "playAgainButton":
			this.stage.setUserData(new MainGameControllerImpl(this.controller.getMainController()));
			SceneSwitcher.<LoadingView>switchScene(event, new LoadingView(this.stage), FXMLFiles.LOADING_SCREEN.getPath());
			break;

		default:
			break;
		}
	}

	@Override
	protected void buildView() {
		this.setGraphics();
	}

	@Override
	protected void setGraphics() {
		this.controller.getGame().get().wordsFound();
		this.wordsFoundValueLabel.setText(""+ (int)this.controller.getGame().get().wordsFound().size());
		this.totalWordsValueLabel.setText(""+ (int)this.controller.getGame().get().wordsToBeFound().size());
		this.pointsValueLabel.setText(""+ (int)this.controller.getGame().get().getCurrentScore());
		this.longestWordFoundValueLabel.setText(""+ this.controller.longestWord());
		this.mostValuableWordValueLabel.setText(""+ this.controller.highestScoreWord());
		
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
}
