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
//import wazzle.controller.common.WazzleController;
//import wazzle.controller.common.WazzleControllerImpl;
//import wazzle.controller.maingame.MainGameController;
//import wazzle.controller.maingame.MainGameControllerImpl;

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
	private Button leaderboardButton;

	@FXML
	private Label titleLabel;

	@FXML
	private Button settingsButton;
	
	@FXML
	private ImageView settingsIcon;

	private Stage stage;
	private DoubleProperty visualUnit;
	//private WazzleController wazzleController;
	//private MainGameController mainGameController;
	//private MiniGameController miniGameController;

	public MainMenuView(final Stage stage) throws IOException {
		this.stage = new Stage();
		this.stage.setWidth(stage.widthProperty().get());
		this.stage.setHeight(stage.heightProperty().get());
		
		visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(stage.heightProperty(), stage.widthProperty()));
		
		//this.wazzleController = new WazzleControllerImpl();
		//this.mainGameController = new MainGameControllerImpl(this.wazzleController);
	}

	public void initialize() {
		setGraphic();
	}

	private void setGraphic() {
		StringExpression titleFontSize = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.1).asString(), "px;");
		StringExpression fontSize = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.05).asString(), "px;");

		mainMenuRightPane.prefWidthProperty().bind(stage.widthProperty().multiply(0.4));
		mainMenuRightPane.maxWidthProperty().bind(stage.widthProperty().multiply(0.4));
		
		mainWrapperButtons.maxWidthProperty().bind(visualUnit.multiply(0.5));
		mainWrapperButtons.spacingProperty().bind(stage.heightProperty().multiply(0.05));
		mainWrapperButtons.styleProperty().bind(fontSize);
		mainWrapperButtons.getStyleClass().add("letters");
		
		Image cogwheelSettingsImage = new Image("img/settingsIcon.png");
		
		settingsIcon.setImage(cogwheelSettingsImage);
		settingsIcon.fitWidthProperty().bind(visualUnit.multiply(0.1));
		settingsIcon.fitHeightProperty().bind(visualUnit.multiply(0.1));
		
		titleLabel.styleProperty().bind(titleFontSize);
	}

	public void goToScene(final ActionEvent event) {

		Node node = (Node) event.getSource();
		
		switch (node.getId()) {

		case "startMiniGameButton":
			break;

		case "startMainGameButton":
			break;

		case "settingsButton":
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value in MainMenu: " + node.getId());
		}
	}

}