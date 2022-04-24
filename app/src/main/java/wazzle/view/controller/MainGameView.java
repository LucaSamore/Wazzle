package wazzle.view.controller;

import java.io.IOException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import wazzle.controller.maingame.MainGameController;
import wazzle.view.ErrorAlert;
import wazzle.view.FXMLFiles;
import wazzle.view.SceneSwitcher;

public final class MainGameView extends View<MainGameController> {
	@FXML private VBox mainVbox;
	@FXML private GridPane grid;
	@FXML private Button bonusScoreButton;
	@FXML private Button bonusTimeButton;
	@FXML private Button bonusWordButton;
	@FXML private Button leaveButton;
	@FXML private Label timerLabel;
	@FXML private Label timerValueLabel;
	@FXML private Label pointsLabel;
	@FXML private Label pointsValueLabel;
	@FXML private BorderPane bp;
	@FXML private Pane leftPanel;
	@FXML private VBox rightPanel;
	@FXML private VBox bottomPanel;
	@FXML private VBox topPanel;
	@FXML private Label titleLabel;
	@FXML private Label bonusLabel;
	@FXML private Label wordSuggestionLabel;
	@FXML private HBox wrapperRightPane;
	
	private StringExpression standardFontSize;
	private StringExpression letterFontSize;
	private StringExpression pointFontSize;
	private StringExpression titleFontSize;
	private String word;
	private Set<Pair<Integer, Integer>> alreadyVisitedCells;
	private Pair<Integer,Integer> lastVisitedPosition;
	private GameTimerView gameTimerView;
	private Set<String> suggestedWords;

	public MainGameView(final Stage stage) {
		this.stage = stage;
		this.controller = (MainGameController) stage.getUserData();
		this.alreadyVisitedCells = new HashSet<>();
		this.suggestedWords = new HashSet<>();
		this.visualUnit = new SimpleDoubleProperty();
		this.bindElements();
		this.gameTimerView = new GameTimerViewImpl();
	}
	
	@FXML
	public void leaveGame(final ActionEvent event) throws IOException {
		final var alert = new Alert(AlertType.NONE);
		final var confirm = new ButtonType("Conferma");
		final var cancel = new ButtonType("Annulla");
		
		alert.setContentText("Vuoi abbandonare la partita?");
		alert.getButtonTypes().setAll(confirm, cancel);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get().equals(confirm)) {
			this.gameTimerView.stopWithoutSave(event);
		}
	}

	@FXML
	public void gainScoreBonus(final ActionEvent event) {
		this.titleLabel.styleProperty().bind(this.titleFontSize.concat("-fx-text-fill: black; "));
		final var previousScore = Integer.parseInt(this.pointsValueLabel.getText());
		this.controller.useScoreBonus();
		this.pointsValueLabel.setText("" + this.controller.getGame().get().getCurrentScore());
		((Button) event.getSource()).setDisable(true);
		this.titleLabel.setText("Hai guadagnato " + (this.controller.getGame().get().getCurrentScore() - previousScore) + " punti!");
	}
	
	@FXML
	public void gainTimeBonus(final ActionEvent event) {
		this.gameTimerView.gainBonus(event);
	}
	
	@FXML
	public void gainWordBonus(final ActionEvent event) {
		this.suggestedWords = this.controller.useWordBonus();
		this.wordSuggestionLabel.setText("Un suggerimento per te " + System.lineSeparator() + String.join(" - ", this.suggestedWords));
		((Button) event.getSource()).setDisable(true);
	}

	@Override
	public void nextScene(ActionEvent event) throws IOException {
		this.gameTimerView.stopWithoutSave(event);		
	}

	@Override
	protected void buildView() {
		this.setGraphics();
		this.setEventHandler();
		var shape = this.controller.getMainController().getCurrentDifficulty().getGridShape();
		this.populateGrid(shape,shape);
		this.startViewTimer();
	}

	@Override
	protected void setGraphics() {
		this.setDisableBonusButtons();
		this.grid.setPadding(new Insets(15,15,15,15));
		this.leftPanel.styleProperty().bind(this.standardFontSize);
		this.leftPanel.styleProperty().bind(Bindings.concat("-fx-spacing: ", this.visualUnit.asString(), ";"));
		this.rightPanel.styleProperty().bind(Bindings.concat("-fx-spacing: ", this.visualUnit.asString(), ";"));		
		this.mainVbox.styleProperty().bind(this.standardFontSize);
		this.titleLabel.styleProperty().bind(this.titleFontSize);
		this.pointsLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		this.pointsValueLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		this.timerLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		this.timerValueLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		this.bonusScoreButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		this.bonusTimeButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		this.bonusWordButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	}
	
	public void update() {
		this.gameTimerView.setUpdatedController(this);
	}
	
	public MainGameController getMainGameController() {
		return this.controller;
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
	private void bindElements() {
		this.visualUnit.bind(Bindings.min(stage.heightProperty().multiply(0.045), stage.widthProperty().multiply(0.045)));
		this.standardFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.asString(), ";");
		this.letterFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(1.5).asString(), ";");
		this.pointFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(0.5).asString(), ";");
		this.titleFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(2).asString(), ";");
	}
	
	private void startViewTimer() {	
		this.gameTimerView.setLabel(this.timerValueLabel);
		this.gameTimerView.attach(this);
		this.gameTimerView.start();
	}
	
	private void setEventHandler() {
		this.mainVbox.setOnMouseDragReleased(mouseEvent -> submitAttempt());
		this.mainVbox.setOnMouseExited(mouseEvent -> this.reset());
		this.bonusScoreButton.setOnMouseDragReleased(mouseEvent -> this.reset());
        this.bonusTimeButton.setOnMouseDragReleased(mouseEvent -> this.reset());
        this.bonusWordButton.setOnMouseDragReleased(mouseEvent -> this.reset());
        this.leaveButton.setOnMouseDragReleased(mouseEvent -> this.reset());
	}
	
	private void populateGrid(final int numCols, final int numRows) {
		this.controller.getGame()
			.get()
			.getGrid()
			.getLetters()
			.forEach(l -> this.addMainGamePane(l.getPosition(), "" + 
					l.getContent(), "" + 
					l.getScore()));
	}
	
	private void addMainGamePane(final Pair<Integer,Integer> position, final String letter, final String points) {
		final var incave = new StackPane();
		final var draggablePane = new Pane();
		final var letterLabel = new Label(letter);
		final var labelPoints = new Label(points);
		
		GridPane.setMargin(incave, new Insets(6,6,6,6));
		letterLabel.getStyleClass().add("letters");
		letterLabel.styleProperty().bind(this.letterFontSize);
		labelPoints.getStyleClass().add("letters");
		labelPoints.styleProperty().bind(this.pointFontSize);
		labelPoints.setPadding(new Insets(0, 7, 3, 0));
		incave.getStyleClass().add("incave");
		incave.minHeightProperty().bind(incave.widthProperty());
		incave.minWidthProperty().bind(Bindings.min(this.stage.widthProperty()
				.divide(5)
				.multiply(0.5), this.stage.heightProperty()
					.divide(5)
					.multiply(0.5)));
		draggablePane.maxWidthProperty().bind(incave.widthProperty().multiply(0.5));
		draggablePane.maxHeightProperty().bind(draggablePane.widthProperty());
		draggablePane.setOnDragDetected(mouseEvent -> {
			incave.startFullDrag();
			this.startReading(position, letterLabel);
		});
		draggablePane.setOnMouseDragEntered(mouseEvent -> this.intermediateReading(position, letterLabel));
		StackPane.setAlignment(draggablePane, Pos.CENTER);
		StackPane.setAlignment(letterLabel, Pos.CENTER);
		StackPane.setAlignment(labelPoints, Pos.BOTTOM_RIGHT);
		incave.getChildren().addAll(letterLabel, draggablePane, labelPoints);
		this.grid.add(incave, position.getKey(), position.getValue());
	}
	
	private void startReading(final Pair<Integer,Integer> position, final Label letterLabel) {
		this.titleLabel.styleProperty().bind(this.titleFontSize.concat("-fx-text-fill: black; "));
		this.alreadyVisitedCells.clear();
		this.word = "";
		this.readLetter(position, letterLabel);
	}
	
	private void intermediateReading(final Pair<Integer,Integer> position, final Label letterLabel) {
		if (this.isNeighbour(position) && this.visitCell(position)) {
			this.readLetter(position, letterLabel);
		}
	}
	
	private void submitAttempt() {
		this.reset();
		if(this.controller.attempt(this.titleLabel.getText())) {
			this.handleWordFound();
		} else {
			this.handleWordNotValid();
		}
	}
	
	private void handleWordFound() {
		this.pointsValueLabel.setText("" + this.controller.getGame().get().getCurrentScore());
		this.titleLabel.styleProperty().bind(this.titleFontSize.concat("-fx-text-fill: green; "));
		this.suggestedWords.remove(this.titleLabel.getText());
		this.wordSuggestionLabel.setText("Un suggerimento per te " + System.lineSeparator() + String.join(" - ", this.suggestedWords));
		
		if(this.suggestedWords.isEmpty()) {
			this.wordSuggestionLabel.setText("");
		}
		
		if(this.controller.areWeDone()) {
			this.switchAfterWin();
		}
	}
	
	private void handleWordNotValid() {
		this.titleLabel.styleProperty().bind(this.titleFontSize.concat("-fx-text-fill: red; "));
		
		if(this.controller.needHelp() && !this.bonusWordButton.isDisable()) {
			this.wordSuggestionLabel.setText("Non riesci a trovare parole? Usa il Bonus Parole!");
		}
	}
	
	private void switchAfterWin() {
		try {
			this.stage.setUserData(this.controller);
			SceneSwitcher.<StatisticsMainGameView>switchScene(this.stage, new StatisticsMainGameView(this.stage), FXMLFiles.MAIN_GAME_STATS.getPath());
		} catch (IOException e) {
			ErrorAlert.show();
		}
	}
	
	private void reset() {
		this.grid.getChildren().forEach(incave -> {
			((StackPane) incave).getChildren().get(0).styleProperty().bind(this.letterFontSize);
		});
	}
	
	private boolean isNeighbour(final Pair<Integer,Integer> position) {
		return IntStream.rangeClosed(position.getKey() - 1, position.getKey() + 1)
				.boxed()
				.flatMap(kk -> IntStream.rangeClosed(position.getValue() - 1, position.getValue() + 1)
						.boxed()
						.map(vv -> new Pair<>(kk,vv)))
				.filter(p -> this.lastVisitedPosition.equals(p))
				.findAny()
				.isPresent();
	}

	private boolean visitCell(final Pair<Integer,Integer> position) {
		return this.alreadyVisitedCells.add(position);
	}
	
	private void readLetter(final Pair<Integer,Integer> position, final Label letterLabel) {
		letterLabel.styleProperty().bind(letterFontSize.concat("-fx-text-fill: white; "));
		this.lastVisitedPosition = position;
		this.word += letterLabel.getText();
		this.titleLabel.setText(this.word);
		this.visitCell(position);
	}
	
	private void setDisableBonusButtons() {
		this.bonusScoreButton.setDisable(this.controller.getMainController().getBonusManager().getScoreBonusQuantity() == 0);
		this.bonusTimeButton.setDisable(this.controller.getMainController().getBonusManager().getTimeBonusQuantity() == 0);
		this.bonusWordButton.setDisable(this.controller.getMainController().getBonusManager().getWordBonusQuantity() == 0);
	}
}
