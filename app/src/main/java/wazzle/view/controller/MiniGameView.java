package wazzle.view.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wazzle.controller.maingame.MainGameController;
import wazzle.controller.minigame.MiniGameController;
import wazzle.controller.minigame.MiniGameControllerImpl;
import wazzle.model.minigame.MiniGameWord;

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
	private VBox mainWrapper;

	@FXML
	private VBox keyboardVbox;

	@FXML
	private StackPane incave;

	private static final String UPPER_ROW_CHARACTERS = "qwertyuiop";
	private static final String MIDDLE_ROW_CHARACTERS = "asdfghjkl";
	private static final String LOWER_ROW_CHARACTERS = "zxcvbnm";
	private static final int NUM_ROWS = 6;
	private static final int NUM_COLS = 5;

	private Map<Integer, String> keyboardCharacters;
	private Map<Integer, GridPane> keyboardKeys;

	private Stage stage;

	private final MiniGameController controller;
	private DoubleProperty visualUnit;
	private int currentTypeIndex;
	private int currentRowIndex;
	private String currentWord;

	public MiniGameView(Stage stage) {
		this.stage = stage;
		this.currentWord = "";
		this.controller = (MiniGameControllerImpl) stage.getUserData();
		this.currentTypeIndex = 0;
		this.currentRowIndex = 0;
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

		visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(stage.widthProperty().multiply(0.05), stage.heightProperty().multiply(0.05)));
	}

	public void initialize() {

		populateLettersGrid(NUM_ROWS, NUM_COLS);
		populateKeyboardGrid();
		setGraphic();

	}

	private Node getNodeByCoords(final int row, final int column) {
		ObservableList<Node> childrens = this.wordsGrid.getChildren();
		for (Node node : childrens) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				return node;
			}
		}
		return null;
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
			String letter = ((Label) ((StackPane) ((Pane) e.getSource()).getParent()).getChildren().get(0)).getText();
			typeLetterGrid(letter);
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
		for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
			for (int colIndex = 0; colIndex < numCols; colIndex++) {
				addMiniGamePane(null, colIndex, rowIndex, 2);
			}
		}
	}

	private void addMiniGamePane(String letter, int colIndex, int rowIndex, int state) {
		System.out.println("Added pane with letter " + letter + " at col " + colIndex + " row " + rowIndex
				+ " with color: " + state);

		incave = new StackPane();
		incave.getStyleClass().add("incave");

		Pane coloredPane = new Pane();

		switch (state) {
		case 0:
			coloredPane.setStyle("-fx-background-color:#45E521;" + "-fx-background-radius: 10");
			break;
		case 1:
			coloredPane.setStyle("-fx-background-color: yellow;" + "-fx-background-radius: 10");
			break;
		case 2:
			coloredPane.setStyle("-fx-background-color: #0000;" + "-fx-background-radius: 10");
			break;
		}

		coloredPane.maxWidthProperty().bind(incave.widthProperty().multiply(0.6));
		coloredPane.maxHeightProperty().bind(coloredPane.widthProperty());

		Label letterLabel = new Label(letter);
		letterLabel.getStyleClass().add("letters");
		letterLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", visualUnit.asString(), ";"));
		StackPane.setAlignment(coloredPane, Pos.CENTER);
		StackPane.setAlignment(letterLabel, Pos.CENTER);
		incave.minWidthProperty().bind(letterLabel.heightProperty());
		incave.getChildren().addAll(coloredPane, letterLabel);
		wordsGrid.add(incave, colIndex, rowIndex);

	}

	private void typeLetterGrid(String string) {
		if (currentTypeIndex < NUM_COLS) {
			removeGridElement(currentTypeIndex, currentRowIndex);
			addMiniGamePane(string, currentTypeIndex, currentRowIndex, 2);
			currentTypeIndex++;
			this.currentWord += string;
		}
	}

	private void setGraphic() {
		firstRowGrid.maxWidthProperty().bind(incave.widthProperty().multiply(UPPER_ROW_CHARACTERS.length()));
		sendWord.styleProperty().bind(Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.75).asString(), ";"));
		deleteButton.fontProperty().bind(sendWord.fontProperty());
		wordsGrid.styleProperty().bind(Bindings.concat("-fx-padding: ", visualUnit.multiply(0.6).asString(), ";"));
		wordsGrid.hgapProperty().bind(visualUnit.multiply(0.2));
		wordsGrid.vgapProperty().bind(wordsGrid.hgapProperty());
		mainWrapper.spacingProperty().bind(visualUnit);
	}

	public void goToScene(ActionEvent event) throws IOException {
//		Node node = (Node) event.getSource();
//		stage = (Stage) node.getScene().getWindow();
//		FXMLLoader loader = new FXMLLoader();
//		loader.setController(new MenuController(stage));
//		loader.setLocation(getClass().getResource(PathToFXMLFiles.MAINMENU.getPath()));
//		Parent root = loader.load();
//		Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
//		stage.setScene(scene);
//		stage.show();
	}

	private void removeGridElement(final int column, final int row) {
		System.out.println("removed elem at: row" + row + "column: " + column);
		this.wordsGrid.getChildren().remove(getNodeByCoords(row, column));
	}

	public void sendWord() {
		if (currentTypeIndex == NUM_COLS) {
			if (this.controller.guessWord(currentWord)) {
				System.out.println("Gioco finito!");
			} else {
				this.currentTypeIndex = 0;
				this.controller.guessWord(currentWord);
				MiniGameWord word = this.controller.computeDifferencies();
				this.currentWord = "";
				for (int i = 0; i < NUM_COLS; i++) {
					removeGridElement(i, this.currentRowIndex);
					addMiniGamePane(word.getCompositeWord().get(i).getKey().toString(), i, this.currentRowIndex,
							word.getCompositeWord().get(i).getValue().getColor());
				}
				this.currentRowIndex++;
				if (this.currentRowIndex == NUM_ROWS) {
					System.out.println("Gioco finito!");
				}
			}
		}
	}

	public void cancelWord() {
		for (int i = 0; i < NUM_COLS; i++) {
			removeGridElement(i, this.currentRowIndex);
			addMiniGamePane("", i, this.currentRowIndex, 2);
		}
		this.currentTypeIndex = 0;
	}
}
