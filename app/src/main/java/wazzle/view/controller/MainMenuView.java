package wazzle.view.controller;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wazzle.controller.common.WazzleController;
import wazzle.controller.maingame.MainGameControllerImpl;
import wazzle.view.FXMLFiles;
import wazzle.view.SceneSwitcher;

public final class MainMenuView {
	
	@FXML
	private Pane mainMenuRightPane;

	@FXML
	private VBox controllerHolder;

	@FXML
	private VBox mainWrapperButtons;

	@FXML
	private Button startMainGameButton;

	@FXML
	private Button startMiniGameButton;

	@FXML
	private Button gameHistoryButton;

	@FXML
	private Label titleLabel;

	@FXML
	private Button settingsButton;
	
	@FXML
	private ImageView settingsIcon;

	private static final double ZERO_ZERO_FIVE = 0.05;
	private static final double ZERO_ONE = 0.1;
	private static final double ZERO_FOUR = 0.4;
	private static final double ZERO_FIVE = 0.5;
	private Stage stage;
	private DoubleProperty visualUnit;
	private StringExpression titleFontSize;
	private StringExpression fontSize;
	private WazzleController wazzleController;

	public MainMenuView(final Stage stage) throws IOException {
		this.stage = stage;
		this.wazzleController = (WazzleController) stage.getUserData();
		this.visualUnit = new SimpleDoubleProperty();
		this.visualUnit.bind(Bindings.min(this.stage.heightProperty(), this.stage.widthProperty()));
	}
	
	public MainMenuView(final Stage stage, DoubleProperty visualUnit) throws IOException {
		this.stage = stage;
		this.wazzleController = (WazzleController) stage.getUserData();
		this.visualUnit = visualUnit;
	}

	public void initialize() {
		this.setGraphic();
	}

	private void setGraphic() {
		this.titleFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(ZERO_ONE).asString(), ";");
		this.fontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(ZERO_ZERO_FIVE).asString(), ";");
		
		mainMenuRightPane.prefWidthProperty().bind(this.stage.widthProperty().multiply(ZERO_FOUR));
		mainMenuRightPane.maxWidthProperty().bind(this.stage.widthProperty().multiply(ZERO_FOUR));
		
		mainWrapperButtons.maxWidthProperty().bind(this.visualUnit.multiply(ZERO_FIVE));
		mainWrapperButtons.spacingProperty().bind(this.stage.heightProperty().multiply(ZERO_ZERO_FIVE));
		mainWrapperButtons.styleProperty().bind(this.fontSize);
		mainWrapperButtons.getStyleClass().add("letters");
		
		Image cogwheelSettingsImage = new Image("img/settingsIcon.png");
		
		settingsIcon.setImage(cogwheelSettingsImage);
		settingsIcon.fitWidthProperty().bind(this.visualUnit.multiply(ZERO_ONE));
		settingsIcon.fitHeightProperty().bind(this.visualUnit.multiply(ZERO_ONE));
		
		titleLabel.styleProperty().bind(this.titleFontSize);
	}

	public void exitApplication(ActionEvent event) {
		System.exit(0);
	}
	
	public void goToScene(final ActionEvent event) throws IOException {

		Node node = (Node) event.getSource();
		
		switch (node.getId()) {

		case "startMiniGameButton":
			break;

		case "startMainGameButton":
			this.stage.setUserData(new MainGameControllerImpl(this.wazzleController));
			SceneSwitcher.<LoadingView>switchScene(event, new LoadingView(this.stage), FXMLFiles.LOADING_SCREEN.getPath());
			break;
		
		case "gameHistoryButton":
			this.stage.setUserData(this.wazzleController);
			SceneSwitcher.<HistoryView>switchScene(event, new HistoryView(this.stage), FXMLFiles.HISTORY.getPath());
			break;

		case "settingsButton":
			this.stage.setUserData(this.wazzleController);
			SceneSwitcher.<SettingsView>switchScene(event, new SettingsView(this.stage), FXMLFiles.SETTINGS.getPath());
			break;
			
		case "exitButton":
			this.exitApplication(event);
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value in MainMenu: " + node.getId());
		}
	}
}