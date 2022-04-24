package wazzle.view.controller;

import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wazzle.controller.common.WazzleController;
import wazzle.view.FXMLFiles;
import wazzle.view.Images;
import wazzle.view.SceneSwitcher;

public final class TutorialMiniGameView extends View<WazzleController> {
	@FXML private ImageView gifTutorial;
	@FXML private TextArea textArea;
	@FXML private VBox contentWrapper;
	@FXML private VBox mainWrapper;
	@FXML private Button exitButton;
	@FXML private VBox elementWrapper;
	
	public TutorialMiniGameView(final Stage stage) {
		this.stage = stage;
		this.visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(stage.heightProperty(), stage.widthProperty()));
	}
	
	@Override
	public void nextScene(final ActionEvent event) throws IOException {
		SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), FXMLFiles.MAIN_MENU.getPath());		
	}
	
	@Override
	protected void buildView() {
		this.setGraphics();
	}

	@Override
	protected void setGraphics() {
		final var fontSize = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.05).asString(), ";");
		final var textFontSize = Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.025).asString(), ";");
		final var paddingValue = Bindings.concat("-fx-padding: ", visualUnit.multiply(0.05).asString(), ";");
		final var spacingValue = Bindings.concat("-fx-spacing: ", visualUnit.multiply(0.02).asString(), ";");
		final var explanationGif = new Image(Images.TUTORIAL_MINI_GAME.getPath());
		this.elementWrapper.styleProperty().bind(spacingValue);
		this.contentWrapper.maxHeightProperty().bind(contentWrapper.widthProperty());
		this.contentWrapper.styleProperty().bind(Bindings.concat(paddingValue, spacingValue));
		this.gifTutorial.setImage(explanationGif);
		this.gifTutorial.fitWidthProperty().bind(visualUnit.multiply(0.7));
		this.gifTutorial.fitHeightProperty().bind(visualUnit.multiply(0.7));
		this.writeTutorialToLabel();
		this.textArea.styleProperty().bind(Bindings.concat(textFontSize, paddingValue));
		this.exitButton.styleProperty().bind(fontSize);
	}
	
	private void writeTutorialToLabel() {
		this.textArea.setText("Lo scopo del gioco e' quello di indovinare la parola nascosta in sei tentativi. Inizia "
                + "digitando una parola qualsiasi di cinque lettere nella prima riga usando la tua tastiera"
                + " o quella a schermo, se la lettera e' indovinata correttamente e si trova nel posto giusto,"
                + " sara' contrassegnata di verde, se la lettera e' nella parola ma nel posto sbagliato sara'"
                + " contrassegnata di giallo, se la lettera non e' nella parola non verra' colorata di nessun"
                + " colore. Riuscirai a trovare la parola corretta? Buona fortuna!");
	}
}
