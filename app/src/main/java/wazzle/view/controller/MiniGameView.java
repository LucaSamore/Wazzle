package wazzle.view.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wazzle.controller.minigame.MiniGameController;
import wazzle.view.ErrorAlert;
import wazzle.view.FXMLFiles;
import wazzle.view.SceneSwitcher;

public final class MiniGameView extends View<MiniGameController> {

  @FXML private GridPane wordsGrid;

  @FXML private GridPane firstRowGrid;

  @FXML private GridPane secondRowGrid;

  @FXML private GridPane thirdRowGrid;

  @FXML private Button sendWord;

  @FXML private Button deleteButton;

  @FXML private Button leaveButton;

  @FXML private VBox mainWrapper;

  @FXML private VBox keyboardVbox;

  @FXML private HBox buttonsWrapper;

  @FXML private StackPane incave;

  @FXML private HBox controlWrapper;

  private static final String UPPER_ROW_CHARACTERS = QWERTYKeyboard.UPPER_ROW.getKeyboardRow();
  private static final String MIDDLE_ROW_CHARACTERS = QWERTYKeyboard.MIDDLE_ROW.getKeyboardRow();
  private static final String LOWER_ROW_CHARACTERS = QWERTYKeyboard.LOWER_ROW.getKeyboardRow();
  private static final int NOT_SET = -1;
  private static final int CORRECT = 0;
  private static final int WRONG_PLACE = 1;
  private static final int WRONG = 2;
  private static final String BACKGROUND_RADIUS_10 = "-fx-background-radius: 10";
  private static final String BACKGROUND_COLOR_NOT_SET = "-fx-background-color:#0000;";
  private static final String BACKGROUND_COLOR_WRONG = "-fx-background-color: #0000;";
  private static final String BACKGROUND_COLOR_CORRECT = "-fx-background-color:#45E521;";
  private static final String BACKGROUND_COLOR_WRONG_PLACE = "-fx-background-color: yellow;";

  private EventHandler<KeyEvent> keyPressedHandler;
  private EventHandler<MouseEvent> clickedOnKeyHandler;
  private int currentTypeIndex;
  private int currentRowIndex;
  private List<String> currentWord;
  private int numRows;
  private int numCols;
  private Set<Character> bannedChars;

  public MiniGameView(Stage stage) {
    this.stage = stage;
    this.currentWord = new LinkedList<>();
    this.bannedChars = new HashSet<>();
    this.controller = (MiniGameController) stage.getUserData();

    this.currentTypeIndex = 0;
    this.currentRowIndex = 0;

    this.firstRowGrid = new GridPane();
    this.secondRowGrid = new GridPane();
    this.thirdRowGrid = new GridPane();

    this.visualUnit = new SimpleDoubleProperty();
    this.visualUnit.bind(
        Bindings.min(stage.widthProperty().multiply(0.05), stage.heightProperty().multiply(0.05)));
    this.setKeyPressedEventHandlers();

    try {
      this.controller.startGame();
    } catch (IOException e) {
      ErrorAlert.show();
    }
    this.bannedChars.addAll(this.controller.getAllWrongLetters());
    this.numRows = this.controller.getMaxAttemptsNumber();
    this.numCols = this.controller.getWordLength();
  }

  private void setKeyPressedEventHandlers() {
    this.keyPressedHandler =
        (KeyEvent event) -> {
          switch (event.getCode()) {
            case BACK_SPACE:
              if (currentTypeIndex != 0) {
                currentTypeIndex--;
                this.currentWord.remove(currentTypeIndex);
                removeGridElement(this.currentTypeIndex, this.currentRowIndex);
                addMiniGamePane("", this.currentTypeIndex, this.currentRowIndex, NOT_SET);
              }
              break;
            case ENTER:
              this.sendWord.fire();
              break;
            default:
              var targetKey = event.getCode().toString().toLowerCase().charAt(0);
              if (event.getCode().isLetterKey() && !this.bannedChars.contains(targetKey)) {
                typeLetterInGrid(event.getCode().toString().toLowerCase());
              }
              break;
          }
          event.consume();
        };

    this.clickedOnKeyHandler =
        e -> {
          String letter =
              ((Label) ((StackPane) ((Pane) e.getSource()).getParent()).getChildren().get(0))
                  .getText();
          typeLetterInGrid(letter);
        };
  }

  private void addKeyPressedListener() {
    this.mainWrapper.addEventFilter(KeyEvent.KEY_PRESSED, this.keyPressedHandler);
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

  private void populateKeyboardRow(final GridPane rowToPopulate, final String string) {
    for (int colIndex = 0; colIndex < string.length(); colIndex++) {
      addKeysToKeyboard(rowToPopulate, colIndex, string.charAt(colIndex));
    }
  }

  private void disableKeyboardKey(final GridPane gridToRemoveFrom) {
    for (Node elementToDisable : gridToRemoveFrom.getChildren()) {
      Character elementToBanFromKeyboard =
          ((Label) ((StackPane) elementToDisable).getChildren().get(0)).getText().charAt(0);
      if (this.bannedChars.contains(elementToBanFromKeyboard)) {
        elementToDisable.setStyle("");
        elementToDisable.getStyleClass().add("darkIncave");
        elementToDisable.setDisable(true);
        elementToDisable.removeEventFilter(MouseEvent.MOUSE_CLICKED, this.clickedOnKeyHandler);
      }
    }
  }

  private void disableKeyboardKeys() {
    this.disableKeyboardKey(this.firstRowGrid);
    this.disableKeyboardKey(this.secondRowGrid);
    this.disableKeyboardKey(this.thirdRowGrid);
  }

  private void addKeysToKeyboard(GridPane targetGridPane, int colIndex, char charAt) {

    this.incave = new StackPane();
    this.incave.getStyleClass().add("incave");

    Pane containerPane = new Pane();
    containerPane.maxWidthProperty().bind(incave.widthProperty());
    containerPane.maxHeightProperty().bind(containerPane.widthProperty());

    Label letterLabel = new Label(String.valueOf(charAt));
    letterLabel.getStyleClass().add("letters");
    letterLabel
        .styleProperty()
        .bind(Bindings.concat("-fx-font-size: ", visualUnit.asString(), ";"));

    StackPane.setAlignment(letterLabel, Pos.CENTER);
    StackPane.setAlignment(containerPane, Pos.CENTER);

    incave.minWidthProperty().bind(letterLabel.heightProperty());

    containerPane.setOnMouseClicked(this.clickedOnKeyHandler);

    incave.getChildren().addAll(letterLabel, containerPane);
    targetGridPane.add(incave, colIndex, 0);
  }

  private void populateLettersGrid() {
    for (int rowIndex = 0; rowIndex < this.numRows; rowIndex++) {
      for (int colIndex = 0; colIndex < this.numCols; colIndex++) {
        String currentLetter = "";
        int currentColor = NOT_SET;
        if (this.controller.getGuessedMinigameWordsSoFar().size() > rowIndex) {
          currentLetter +=
              this.controller
                  .getGuessedMinigameWordsSoFar()
                  .get(rowIndex)
                  .getCompositeWord()
                  .get(colIndex)
                  .getCharacter();
          currentColor =
              this.controller
                  .getGuessedMinigameWordsSoFar()
                  .get(rowIndex)
                  .getCompositeWord()
                  .get(colIndex)
                  .getResult();
        }

        addMiniGamePane(currentLetter, colIndex, rowIndex, currentColor);
      }
    }
    currentRowIndex = this.controller.getGuessedMinigameWordsSoFar().size();
  }

  private void addMiniGamePane(
      final String letter, final int colIndex, final int rowIndex, final int state) {

    incave = new StackPane();
    incave.getStyleClass().add("incave");

    Pane coloredPane = new Pane();

    switch (state) {
      case CORRECT:
        coloredPane.setStyle(BACKGROUND_COLOR_CORRECT + BACKGROUND_RADIUS_10);
        break;
      case WRONG_PLACE:
        coloredPane.setStyle(BACKGROUND_COLOR_WRONG_PLACE + BACKGROUND_RADIUS_10);
        break;
      case WRONG:
        coloredPane.setStyle(BACKGROUND_COLOR_WRONG + BACKGROUND_RADIUS_10);
        break;
      default:
        coloredPane.setStyle(BACKGROUND_COLOR_NOT_SET + BACKGROUND_RADIUS_10);
        break;
    }

    coloredPane.maxWidthProperty().bind(incave.widthProperty().multiply(0.6));
    coloredPane.maxHeightProperty().bind(coloredPane.widthProperty());

    Label letterLabel = new Label(letter);
    letterLabel.getStyleClass().add("letters");
    letterLabel
        .styleProperty()
        .bind(Bindings.concat("-fx-font-size: ", visualUnit.asString(), ";"));
    StackPane.setAlignment(coloredPane, Pos.CENTER);
    StackPane.setAlignment(letterLabel, Pos.CENTER);
    incave.minWidthProperty().bind(letterLabel.heightProperty());
    incave.getChildren().addAll(coloredPane, letterLabel);
    wordsGrid.add(incave, colIndex, rowIndex);
  }

  private void typeLetterInGrid(final String string) {
    if (this.currentTypeIndex < this.numCols) {
      removeGridElement(this.currentTypeIndex, this.currentRowIndex);
      addMiniGamePane(string, this.currentTypeIndex, this.currentRowIndex, NOT_SET);
      this.currentTypeIndex++;
      this.currentWord.add(string);
    }
  }

  private void removeGridElement(final int column, final int row) {
    this.wordsGrid.getChildren().remove(getNodeByCoords(row, column));
  }

  @FXML
  public void cancelWord() {
    for (int i = 0; i < this.numCols; i++) {
      removeGridElement(i, this.currentRowIndex);
      addMiniGamePane("", i, this.currentRowIndex, NOT_SET);
    }
    this.currentTypeIndex = 0;
    this.currentWord.clear();
  }

  @Override
  public void nextScene(final ActionEvent event) throws IOException {
    if (currentTypeIndex == this.numCols) {
      var currentWordAsString = String.join("", currentWord);
      this.controller.guessWord(currentWordAsString);
      if (this.controller.getStateOfCurrentMinigame() == 0) {
        for (int i = 0; i < this.numCols; i++) {
          removeGridElement(i, this.currentRowIndex);
          var letterResult = this.controller.getLetterResultAtIndex(i);
          var letterChar = this.controller.getLetterCharAtIndex(i);
          if (letterResult == WRONG
              && !this.controller.isTheLetterCorrectInAnotherPlace(letterChar)) {
            bannedChars.add(letterChar);
          }
          addMiniGamePane(String.valueOf(letterChar), i, this.currentRowIndex, letterResult);
        }

        this.currentRowIndex++;
        this.disableKeyboardKeys();
        this.currentWord.clear();
        this.currentTypeIndex = 0;
      } else {
        this.stage.setUserData(this.controller);
        SceneSwitcher.<StatisticsMiniGameView>switchScene(
            event, new StatisticsMiniGameView(this.stage), FXMLFiles.MINI_GAME_STATS.getPath());
      }
    }
  }

  @Override
  protected void buildView() {
    this.populateLettersGrid();
    this.populateKeyboardRow(this.firstRowGrid, UPPER_ROW_CHARACTERS);
    this.populateKeyboardRow(this.secondRowGrid, MIDDLE_ROW_CHARACTERS);
    this.populateKeyboardRow(this.thirdRowGrid, LOWER_ROW_CHARACTERS);
    this.setGraphics();
    this.addKeyPressedListener();
    this.disableKeyboardKeys();
  }

  @FXML
  public void leaveGame(final ActionEvent event) throws IOException {
    if (this.currentRowIndex != 0) {
      try {
        this.controller.saveMiniGame();
      } catch (IOException e) {
        ErrorAlert.show();
      }
    }
    this.stage.setUserData(this.controller.getMainController());
    SceneSwitcher.<MainMenuView>switchScene(
        event, new MainMenuView(this.stage), FXMLFiles.MAIN_MENU.getPath());
  }

  @Override
  protected void setGraphics() {

    StringExpression paddingValue =
        Bindings.concat("-fx-padding: ", visualUnit.multiply(0.6).asString(), ";");
    StringExpression hgapValue =
        Bindings.concat("-fx-hgap: ", visualUnit.multiply(0.1).asString(), ";");

    firstRowGrid
        .maxWidthProperty()
        .bind(incave.widthProperty().multiply(UPPER_ROW_CHARACTERS.length()));
    sendWord
        .styleProperty()
        .bind(Bindings.concat("-fx-font-size: ", visualUnit.multiply(0.75).asString(), ";"));
    deleteButton.fontProperty().bind(sendWord.fontProperty());
    leaveButton.fontProperty().bind(sendWord.fontProperty());

    wordsGrid.styleProperty().bind(paddingValue);
    wordsGrid.hgapProperty().bind(visualUnit.multiply(0.2));
    wordsGrid.vgapProperty().bind(wordsGrid.hgapProperty());

    mainWrapper.spacingProperty().bind(visualUnit.multiply(0.5));
    buttonsWrapper.spacingProperty().bind(visualUnit);
    keyboardVbox.spacingProperty().bind(visualUnit.multiply(0.1));
    controlWrapper.spacingProperty().bind(visualUnit);

    keyboardVbox.styleProperty().bind(paddingValue);
    firstRowGrid.styleProperty().bind(hgapValue);
    secondRowGrid.styleProperty().bind(hgapValue);
    thirdRowGrid.styleProperty().bind(hgapValue);
  }
}
