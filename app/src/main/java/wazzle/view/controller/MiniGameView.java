package wazzle.view.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MiniGameView {

	@FXML
	private GridPane wordsGrid;

	@FXML
	private GridPane firstRowGrid;

	@FXML
	private GridPane secondRowGrid;

	@FXML
	private GridPane thirdRowGrid;

	@FXML
	private Button sendWord;

	@FXML
	private Button deleteButton;

	@FXML
	private HBox commandsWrapper;

	@FXML
	private VBox buttonWrapper;

	@FXML
	private VBox mainWrapper;

	@FXML
	private VBox keyboardVbox;

	@FXML
	private StackPane incave;

	private static final String UPPER_ROW_CHARACTERS = "qwertyuiop";
	private static final String MIDDLE_ROW_CHARACTERS = "asdfghjkl";
	private static final String LOWER_ROW_CHARACTERS = "zxcvbnm";

	private Map<Integer, String> keyboardCharacters;
	private Map<Integer, GridPane> keyboardKeys;

	private Stage stage;
	private DoubleProperty visualUnit;

	public MiniGameView(Stage stage) {
		this.stage = stage;
		this.keyboardCharacters = new HashMap<>();
		this.keyboardCharacters.put(0, UPPER_ROW_CHARACTERS);
		this.keyboardCharacters.put(1, MIDDLE_ROW_CHARACTERS);
		this.keyboardCharacters.put(2, LOWER_ROW_CHARACTERS);

		this.firstRowGrid = new GridPane();
		this.secondRowGrid = new GridPane();
		this.thirdRowGrid = new GridPane();

		this.keyboardKeys = new HashMap<>();
		this.keyboardKeys.put(0, firstRowGrid);
		this.keyboardKeys.put(1, secondRowGrid);
		this.keyboardKeys.put(2, thirdRowGrid);

		this.stage = stage;

		visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(stage.widthProperty().multiply(0.05), stage.heightProperty().multiply(0.05)));
	}

	public void initialize() {

		populateLettersGrid(6, 5);
		populateKeyboardGrid();
		setGraphic();

	}

	private void populateKeyboardGrid() {
		for (int rowIndex = 0; rowIndex < keyboardKeys.size(); rowIndex++) {
			for (int colIndex = 0; colIndex < keyboardCharacters.get(rowIndex).length(); colIndex++) {
				addKeysToKeyboard(rowIndex, keyboardCharacters.get(rowIndex).charAt(colIndex), colIndex);
			}
		}

	}

	private void addKeysToKeyboard(int rowIndex, char charAt, int colIndex) {

		this.incave = new StackPane();
		Pane containerPane = new Pane();
		containerPane.maxWidthProperty().bind(incave.widthProperty());
		containerPane.maxHeightProperty().bind(containerPane.widthProperty());

		incave.getStyleClass().add("incave");

		Label letterLabel = new Label(String.valueOf(charAt));
		letterLabel.getStyleClass().add("letters");
		letterLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", visualUnit.asString(), ";"));

		StackPane.setAlignment(letterLabel, Pos.CENTER);
		StackPane.setAlignment(containerPane, Pos.CENTER);

		incave.minWidthProperty().bind(letterLabel.heightProperty());


		containerPane.setOnMouseClicked(e -> {
			System.out.println(((Label)((StackPane)((Pane)e.getSource()).getParent()).getChildren().get(0)).getText());
		});

		incave.getChildren().addAll(letterLabel, containerPane);

		switch (rowIndex) {
		case 0: {
			firstRowGrid.add(incave, colIndex, 0);
			break;

		}
		case 1: {
			secondRowGrid.add(incave, colIndex, 0);
			break;

		}
		case 2: {
			thirdRowGrid.add(incave, colIndex, 0);
			break;

		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + rowIndex);
		}

	}

	private void populateLettersGrid(int numRows, int numCols) {
		for (int colIndex = 0; colIndex < numCols; colIndex++) {
			for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
				addMiniGamePane(colIndex, rowIndex);
			}
		}

	}
	

	private void addMiniGamePane(int colIndex, int rowIndex) {

		incave = new StackPane();
		Pane coloredPane = new Pane();

//////DEBUG
		var random = new Random();
		int randomValue = random.nextInt(3);
		switch (randomValue) {
		case 0:
			coloredPane.setStyle("-fx-background-color: #0000;" + "-fx-background-radius: 10");
			break;
		case 1:
			coloredPane.setStyle("-fx-background-color: yellow;" + "-fx-background-radius: 10");
			break;
		case 2:
			coloredPane.setStyle("-fx-background-color: #45E521;" + "-fx-background-radius: 10");
			break;
		}
////DEGUG

		coloredPane.maxWidthProperty().bind(incave.widthProperty().multiply(0.6));
		coloredPane.maxHeightProperty().bind(coloredPane.widthProperty());

		incave.getStyleClass().add("incave");

		Label letterLabel = new Label("" + (char) (65 + new Random().nextInt(15)));
		letterLabel.getStyleClass().add("letters");

		letterLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", visualUnit.asString(), ";"));

		StackPane.setAlignment(coloredPane, Pos.CENTER);
		StackPane.setAlignment(letterLabel, Pos.CENTER);

		incave.minWidthProperty().bind(letterLabel.heightProperty());
		incave.getChildren().addAll(coloredPane, letterLabel);
		// TODO, first row grid è nel posto sbagliato.

		wordsGrid.add(incave, colIndex, rowIndex);

	}

	private void setGraphic() {
		firstRowGrid.maxWidthProperty().bind(incave.widthProperty().multiply(UPPER_ROW_CHARACTERS.length()));
		sendWord.styleProperty().bind(Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.75).asString(), ";"));
		deleteButton.fontProperty().bind(sendWord.fontProperty());

		buttonWrapper.spacingProperty().bind(visualUnit.multiply(0.75));

		wordsGrid.styleProperty().bind(Bindings.concat("-fx-padding: ", visualUnit.multiply(0.6).asString(), ";"));

		wordsGrid.hgapProperty().bind(visualUnit.multiply(0.2));
		wordsGrid.vgapProperty().bind(wordsGrid.hgapProperty());

		mainWrapper.spacingProperty().bind(visualUnit);

		commandsWrapper.spacingProperty().bind(visualUnit);
		commandsWrapper.styleProperty()
				.bind(Bindings.concat("-fx-padding: ", visualUnit.multiply(0.6).asString(), ";"));
	}

	public void goToScene(ActionEvent event) throws IOException {
	}
	

	public void sendWord() {
		// TODO document why this method is empty
	}
	
}
