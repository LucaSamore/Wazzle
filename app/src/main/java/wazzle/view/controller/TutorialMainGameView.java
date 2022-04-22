package wazzle.view.controller;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wazzle.view.Images;

public final class TutorialMainGameView {
	@FXML
	private ImageView gifTutorial;

	@FXML
	private Label textLabel;

	@FXML
	private VBox contentWrapper;

	@FXML
	private VBox mainWrapper;

	@FXML
	private Button exitButton;
	
	@FXML 
	private VBox elementWrapper;
	
	private DoubleProperty visualUnit;
	
	public TutorialMainGameView(final Stage stage) {
		this.visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(stage.heightProperty(), stage.widthProperty()));
	}
	
	public void initialize() {
		this.setGraphics();		
	}
	
	public void nextScene(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub		
	}

	protected void setGraphics() {
		final var fontSize = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.05).asString(), ";");
		final var textFontSize = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.025).asString(), ";");
		final var paddingValue = Bindings.concat("-fx-padding: ", visualUnit.multiply(0.05).asString(), ";");
		final var spacingValue = Bindings.concat("-fx-spacing: ", visualUnit.multiply(0.02).asString(), ";");
		final var explanationGif = new Image(getClass().getResourceAsStream(Images.TUTORIAL_MAIN_GAME.getPath()));
		elementWrapper.styleProperty().bind(spacingValue);
		contentWrapper.maxHeightProperty().bind(contentWrapper.widthProperty());
		contentWrapper.styleProperty().bind(Bindings.concat(paddingValue, spacingValue));
		gifTutorial.setImage(explanationGif);
		gifTutorial.fitWidthProperty().bind(visualUnit.multiply(0.7));
		gifTutorial.fitHeightProperty().bind(visualUnit.multiply(0.7));
		this.writeTutorialToLabel();
		textLabel.styleProperty().bind(Bindings.concat(textFontSize, paddingValue));
		exitButton.styleProperty().bind(fontSize);
	}
	
	private void writeTutorialToLabel() {
		
	}
}
