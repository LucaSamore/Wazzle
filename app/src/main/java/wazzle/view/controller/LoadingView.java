/**
 * add RunLater 
 */

package wazzle.view.controller;

import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import wazzle.controller.maingame.MainGameController;
import wazzle.view.Loader;

public class LoadingView {

	@FXML
	private ImageView imageContainer;

	@FXML
	private Label firstDot;

	@FXML
	private Label secondDot;

	@FXML
	private Label thirdDot;

	@FXML
	private Label loadingLabel;
	
	private static final String DATASET_NAME = "dataset.txt";
	private static final String IMAGE_PATH = "img/WaffleLoadingScreen.png";
	private static final String NEXT_SCENE_FXML = "layouts/MainGame.fxml";
	
	private AnimationTimer animationTimer;
	private DoubleProperty visualUnit;
		
	private boolean flag = false;
	
	private final MainGameController mainGameController;

	public LoadingView(Stage stage) {
		
		this.mainGameController = (MainGameController) stage.getUserData();
		animationTimer = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				if (flag) {
						animationTimer.stop();
						try {
							goToMainGame(stage);
						} catch (IOException e) {
							e.printStackTrace();
						}

				}				
			}
		};
		
		visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(stage.heightProperty(), stage.widthProperty()));

	}

	public void initialize() {
		animationTimer.start();
		setGraphic();
		setAnimation();
		setGrid();
	}
	

	private void setGrid() {
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws IOException {
				var shape = mainGameController.getMainController().getSettings().getCurrentGridShape();				
				mainGameController.startNewGame(mainGameController
						.getMainController()
						.getFileController()
						.getDataset(DATASET_NAME), new Pair<Integer,Integer>(shape,shape), mainGameController.getMainController().getSettings().getCurrentDifficulty());
				
				
				flag = true;
				return null;
			}
		};
		new Thread(task).start();
	}
	
	

	private void setAnimation() {
		Image loadingWaffle = new Image(ClassLoader.getSystemResourceAsStream(IMAGE_PATH));
		imageContainer.setImage(loadingWaffle);

		DoubleProperty dotJumpHeight = new SimpleDoubleProperty();
		dotJumpHeight.bind(loadingLabel.heightProperty().multiply(0.5 * -1));

		RotateTransition rotation = new RotateTransition();
		rotation.setNode(imageContainer);
		rotation.setDuration(Duration.millis(1000));
		rotation.setCycleCount(Animation.INDEFINITE);
		rotation.setByAngle(360);
		rotation.setAxis(Rotate.Z_AXIS);
		rotation.play();

		TranslateTransition translateFirst = new TranslateTransition();
		translateFirst.setNode(firstDot);
		translateFirst.setDuration(Duration.millis(200));
		translateFirst.setCycleCount(2);
		translateFirst.byYProperty().bind(dotJumpHeight);
		translateFirst.setAutoReverse(true);

		TranslateTransition translateSecond = new TranslateTransition();
		translateSecond.setNode(secondDot);
		translateSecond.setDuration(Duration.millis(200));
		translateSecond.setCycleCount(2);
		translateSecond.byYProperty().bind(dotJumpHeight);
		translateSecond.setAutoReverse(true);

		TranslateTransition translateThird = new TranslateTransition();
		translateThird.setNode(thirdDot);
		translateThird.setDuration(Duration.millis(200));
		translateThird.setCycleCount(2);
		translateThird.byYProperty().bind(dotJumpHeight);
		translateThird.setAutoReverse(true);

		SequentialTransition seqT = new SequentialTransition();

		seqT.getChildren().addAll(translateFirst, translateSecond, translateThird);
		seqT.setCycleCount(Animation.INDEFINITE);
		seqT.play();
	}

	private void setGraphic() {
		ObservableValue<String> fontSize = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.1).asString(), ";");
		
		imageContainer.fitHeightProperty().bind(imageContainer.fitWidthProperty());
		imageContainer.fitWidthProperty().bind(visualUnit.multiply(0.3));
		
		loadingLabel.styleProperty().bind(fontSize);
		firstDot.styleProperty().bind(fontSize);
		secondDot.styleProperty().bind(fontSize);
		thirdDot.styleProperty().bind(fontSize);
	}

	public void goToMainGame(Stage stage) throws IOException {
		Parent element = Loader.<MainGameView,Parent>loadFXMLElement(new MainGameView(stage), NEXT_SCENE_FXML);
		Scene scene = new Scene(element, stage.getScene().getWidth(), stage.getScene().getHeight());
		stage.setUserData(this.mainGameController);
		stage.setScene(scene);
		stage.show();
	}
}
