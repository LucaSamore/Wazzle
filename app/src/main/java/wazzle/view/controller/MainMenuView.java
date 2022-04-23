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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wazzle.controller.common.WazzleController;
import wazzle.controller.maingame.MainGameControllerImpl;
import wazzle.controller.minigame.MiniGameControllerImpl;
import wazzle.view.FXMLFiles;
import wazzle.view.Images;
import wazzle.view.SceneSwitcher;

/**
 * This class is a concrete implementation for {@link View}.
 * It provides method for manage the main menu view.
 */
public final class MainMenuView extends View<WazzleController>{
	
	@FXML
	private Pane mainMenuRightPane;

	@FXML
	private VBox mainWrapperButtons;
	
	@FXML 
	private GridPane container;

	@FXML
	private Button startMainGameButton;

	@FXML
	private Button tutorialMainGameButton;
	
	@FXML
	private Button tutorialMiniGameButton;
	
	@FXML
	private Button startMiniGameButton;

	@FXML
	private Button gameHistoryButton;
	
	@FXML
	private Button exitButton;

	@FXML
	private Label titleLabel;

	@FXML
	private Button settingsButton;
	
	@FXML
	private ImageView settingsIcon;

	private static final double ZERO_ZERO_THREE = 0.03;
	private static final double ZERO_ZERO_FIVE = 0.05;
	private static final double ZERO_ZERO_SIX = 0.06;
	private static final double ZERO_ONE = 0.1;
	private static final double ZERO_FOUR = 0.4;
	private static final double ZERO_FIVE = 0.5;
	private StringExpression titleFontSize;
	private StringExpression fontSize;
	private StringExpression smallFontSize;

	/**
	 * Construct a new {@link MainMenuView} given a stage.
	 * 
	 * @param stage		The stage passed from the views.
	 * @throws IOException
	 */
	public MainMenuView(final Stage stage) throws IOException {
		this.stage = stage;
		this.controller = (WazzleController) stage.getUserData();
		this.visualUnit = new SimpleDoubleProperty();
		this.visualUnit.bind(Bindings.min(this.stage.heightProperty(), this.stage.widthProperty()));
	}
	
	/**
	 * Construct a new {@link MainMenuView} given the primary stage and the starting view properties
	 * .
	 * @param stage		The primary stage.
	 * @param visualUnit		The starting view properties.
	 * @throws IOException
	 */
	public MainMenuView(final Stage stage, DoubleProperty visualUnit) throws IOException {
		this.stage = stage;
		this.controller = (WazzleController) stage.getUserData();
		this.visualUnit = visualUnit;
	}

	/**
	 * Exit from application after clicking the exit button.
	 * 
	 * @param event		The exit button click event.
	 */
	public void exitApplication(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * Switch scene from main menu to all the possible options.
	 */
	@Override
	public void nextScene(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();
		switch (node.getId()) {
		case "startMiniGameButton":
			this.stage.setUserData(new MiniGameControllerImpl(this.controller));
			SceneSwitcher.<MiniGameView>switchScene(event, new MiniGameView(this.stage), FXMLFiles.MINI_GAME.getPath());
			break;
		case "startMainGameButton":
			this.stage.setUserData(new MainGameControllerImpl(this.controller));
			SceneSwitcher.<LoadingView>switchScene(event, new LoadingView(this.stage), FXMLFiles.LOADING_SCREEN.getPath());
			break;
		case "tutorialMainGameButton":
			SceneSwitcher.<TutorialMainGameView>switchScene(event, new TutorialMainGameView(this.stage), FXMLFiles.TUTORIAL_MAIN_GAME.getPath());
			break;
		case "tutorialMiniGameButton":
			SceneSwitcher.<TutorialMiniGameView>switchScene(event, new TutorialMiniGameView(this.stage), FXMLFiles.TUTORIAL_MINI_GAME.getPath());
			break;
		case "gameHistoryButton":
			this.stage.setUserData(this.controller);
			SceneSwitcher.<HistoryView>switchScene(event, new HistoryView(this.stage), FXMLFiles.HISTORY.getPath());
			break;
		case "settingsButton":
			this.stage.setUserData(this.controller);
			SceneSwitcher.<SettingsView>switchScene(event, new SettingsView(this.stage), FXMLFiles.SETTINGS.getPath());
			break;
		case "exitButton":
			this.exitApplication(event);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value in MainMenu: " + node.getId());
		}
	}

	/**
	 * Build the main menu view with all its graphics and all its information.
	 */
	@Override
	protected void buildView() {
		this.setGraphics();
	}

	/**
	 * Set the graphics of the main menu view.
	 */
	@Override
	protected void setGraphics() {
		this.titleFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(ZERO_ONE).asString(), ";");
		this.fontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(ZERO_ZERO_FIVE).asString(), ";");
		this.smallFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(ZERO_ZERO_THREE).asString(), ";");
		this.mainMenuRightPane.prefWidthProperty().bind(this.stage.widthProperty().multiply(ZERO_FOUR));
		this.mainMenuRightPane.maxWidthProperty().bind(this.stage.widthProperty().multiply(ZERO_FOUR));
		this.mainWrapperButtons.maxWidthProperty().bind(this.visualUnit.multiply(ZERO_FIVE));
		this.mainWrapperButtons.spacingProperty().bind(this.stage.heightProperty().multiply(ZERO_ZERO_FIVE));
		this.mainWrapperButtons.styleProperty().bind(this.fontSize);
		this.container.vgapProperty().bind(this.visualUnit.multiply(ZERO_ZERO_SIX));
		this.container.hgapProperty().bind(this.visualUnit.multiply(ZERO_ZERO_SIX));
		this.tutorialMainGameButton.styleProperty().bind(this.smallFontSize);
		this.tutorialMiniGameButton.styleProperty().bind(this.smallFontSize);
		this.mainWrapperButtons.getStyleClass().add("letters");
		Image cogwheelSettingsImage = new Image(Images.SETTINGS_ICON.getPath());
		this.settingsIcon.setImage(cogwheelSettingsImage);
		this.settingsIcon.fitWidthProperty().bind(this.visualUnit.multiply(ZERO_ONE));
		this.settingsIcon.fitHeightProperty().bind(this.visualUnit.multiply(ZERO_ONE));
		this.titleLabel.styleProperty().bind(this.titleFontSize);
	}
}