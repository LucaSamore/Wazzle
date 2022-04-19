package wazzle.view.controller;

import java.io.IOException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import wazzle.view.FXMLFiles;
import wazzle.view.Loader;
import wazzle.view.SceneSwitcher;

public final class MainGameView {
	@FXML
	private VBox mainVbox;

	@FXML
	private GridPane grid;

	@FXML
	private Button bonusScoreButton;

	@FXML
	private Button bonusTimeButton;

	@FXML
	private Button bonusWordButton;

	@FXML
	private Button leaveButton;

	@FXML
	private Label timerLabel;

	@FXML
	private Label timerValueLabel;

	@FXML
	private Label pointsLabel;

	@FXML
	private Label pointsValueLabel;

	@FXML
	private BorderPane bp;

	@FXML
	private Pane leftPanel;

	@FXML
	private VBox rightPanel;

	@FXML
	private VBox bottomPanel;

	@FXML
	private VBox topPanel;

	@FXML
	private Label titleLabel;

	@FXML
	private Label bonusLabel;
	
	@FXML
	private Label wordSuggestionLabel;
	
	@FXML
	private HBox wrapperRightPane;
	
	private final Stage stage;
	private final StringExpression standardFontSize;
	private final StringExpression letterFontSize;
	private final StringExpression pointFontSize;
	private final StringExpression titleFontSize;
	private final DoubleProperty visualUnit;
	
	private String word;
	private Set<Pair<Integer, Integer>> alreadyVisitedCells;
	private Pair<Integer,Integer> lastVisitedPosition;
	private final MainGameController controller;
	private AnimationTimer animationTimer;
	
	private Set<String> suggestedWords;

	public MainGameView(final Stage stage) {
		this.stage = stage;
		this.controller = (MainGameController)stage.getUserData();
		this.alreadyVisitedCells = new HashSet<>();
		this.suggestedWords = new HashSet<>();
		this.visualUnit = new SimpleDoubleProperty();
		this.visualUnit.bind(Bindings.min(stage.heightProperty().multiply(0.045), stage.widthProperty().multiply(0.045)));
		this.standardFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.asString(), ";");
		this.letterFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(1.5).asString(), ";");
		this.pointFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(0.5).asString(), ";");
		this.titleFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(2).asString(), ";");
	}

	public void initialize() {
		this.setGraphics();
		this.setEventHandler();
		var shape = this.controller.getMainController().getSettings().getCurrentGridShape();
		this.populateGrid(shape,shape);
		this.controller.startTimer();
		this.startTimer();
	}
	
	private void startTimer() {		
		this.animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				Platform.runLater(new Runnable(){
					@Override
					public void run() {
						final var timeRemaining = controller.getRemainingTime();
						timerValueLabel.setText("" + timeRemaining);
						
						if(timeRemaining <= 0) {
							animationTimer.stop();
							saveGame();
							stage.setUserData(controller);
							DoubleProperty visualUnit = new SimpleDoubleProperty();
							visualUnit.bind(Bindings.min(stage.widthProperty(),stage.heightProperty()));
							StatisticsMainGameView controller = new StatisticsMainGameView(stage);
							final Scene scene;
							try {
								scene = new Scene(Loader.<StatisticsMainGameView, Parent>loadFXMLElement(controller, FXMLFiles.MAIN_GAME_STATS.getPath()), 
												stage.getWidth()*0.75, stage.getHeight()*0.75);
								stage.setScene(scene);
								stage.show();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				});
			}
		};
		this.animationTimer.start();
	}
	
	public void leaveGame(final ActionEvent event) throws IOException {
		final var alert = new Alert(AlertType.NONE);
		final var confirm = new ButtonType("Conferma");
		final var cancel = new ButtonType("Annulla");
		
		alert.setContentText("Vuoi abbandonare la partita?");
		alert.getButtonTypes().setAll(confirm, cancel);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get().equals(confirm)) {
			this.animationTimer.stop();
			this.controller.stopTimer();
			this.controller.getMainController().saveBonuses();
			this.stage.setUserData(this.controller.getMainController());
			SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), FXMLFiles.MAIN_MENU.getPath());
		}
	}

	public void gainScoreBonus(final ActionEvent event) {
		this.controller.useScoreBonus();
		this.pointsValueLabel.setText(""+ (int)this.controller.getGame().get().getCurrentScore());
		((Button) event.getSource()).setDisable(true);
	}
	
	public void gainTimeBonus(final ActionEvent event) {
		this.controller.useTimeBonus();
		timerValueLabel.setText("" + this.controller.getRemainingTime());
		((Button) event.getSource()).setDisable(true);
	}
	
	public void gainWordBonus(final ActionEvent event) {
		this.suggestedWords = this.controller.useWordBonus();
		this.wordSuggestionLabel.setText("Un suggerimento per te " + System.lineSeparator() + String.join(" - ", this.suggestedWords));
		((Button) event.getSource()).setDisable(true);
	}
	
	private void setEventHandler() {
		this.mainVbox.setOnMouseDragReleased(mouseEvent -> submitWord());
		this.mainVbox.setOnMouseExited(mouseEvent -> this.finishInvalidReading());
		
		this.bonusScoreButton.setOnMouseDragReleased(mouseEvent -> this.finishInvalidReading());
        this.bonusTimeButton.setOnMouseDragReleased(mouseEvent -> this.finishInvalidReading());
        this.bonusWordButton.setOnMouseDragReleased(mouseEvent -> this.finishInvalidReading());
        this.leaveButton.setOnMouseDragReleased(mouseEvent -> this.finishInvalidReading());
	}
	
	private void setGraphics() {
		this.bonusScoreButton.setDisable(this.controller.getMainController().getBonusManager().getScoreBonusQuantity() == 0);
		this.bonusTimeButton.setDisable(this.controller.getMainController().getBonusManager().getTimeBonusQuantity() == 0);
		this.bonusWordButton.setDisable(this.controller.getMainController().getBonusManager().getWordBonusQuantity() == 0);
		
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
	
	private void populateGrid(final int numCols, final int numRows) {
		this.controller.getGame()
			.get()
			.getGrid()
			.getLetters()
			.forEach(l -> this.addMainGamePane(l.getPosition(), "" + l.getContent(), "" + "" + (int)(l.getScore()))); //TODO: fix 
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
	
	private void submitWord() {
		this.grid.getChildren().forEach(gridPane -> {
			((StackPane) gridPane).getChildren().get(0).styleProperty().bind(this.letterFontSize);
		});
		
		//TODO: add switch to stats if areWeDone is true 
		if(this.controller.attempt(this.titleLabel.getText())) {
			this.pointsValueLabel.setText(""+ (int)this.controller.getGame().get().getCurrentScore());
			this.titleLabel.styleProperty().bind(this.titleFontSize.concat("-fx-text-fill: green; "));
			
			this.suggestedWords.remove(this.titleLabel.getText());
			this.wordSuggestionLabel.setText("Un suggerimento per te " + System.lineSeparator() + String.join(" - ", this.suggestedWords));
			
			if(this.suggestedWords.isEmpty()) {
				this.wordSuggestionLabel.setText("");
			}

			
		} else {
			this.titleLabel.styleProperty().bind(this.titleFontSize.concat("-fx-text-fill: red; "));
			if(this.controller.needHelp() && !this.bonusWordButton.isDisable()) {
				this.wordSuggestionLabel.setText("Se stai facendo schifo al gioco usa il bonus delle parole :)");
			}
		}
	}
	
	private void finishInvalidReading() {
		this.grid.getChildren().forEach(incave -> {
			((StackPane) incave).getChildren().get(0).styleProperty().bind(this.letterFontSize);
		});
	}

	//TODO: Fix this method
	private boolean isNeighbour(final Pair<Integer,Integer> position) {
		//hard coded for now, don't sue me please :)
		return ((position.getKey() == this.lastVisitedPosition.getKey() - 1 && position.getValue() == this.lastVisitedPosition.getValue() - 1)
				|| (position.getKey() == this.lastVisitedPosition.getKey() - 1 && position.getValue() == this.lastVisitedPosition.getValue())
				|| (position.getKey() == this.lastVisitedPosition.getKey() - 1 && position.getValue() == this.lastVisitedPosition.getValue() + 1)
				|| (position.getKey() == this.lastVisitedPosition.getKey() && position.getValue() == this.lastVisitedPosition.getValue() - 1)
				|| (position.getKey() == this.lastVisitedPosition.getKey() && position.getValue() == this.lastVisitedPosition.getValue() + 1)
				|| (position.getKey() == this.lastVisitedPosition.getKey() + 1 && position.getValue() == this.lastVisitedPosition.getValue() - 1)
				|| (position.getKey() == this.lastVisitedPosition.getKey() + 1 && position.getValue() == this.lastVisitedPosition.getValue())
				|| (position.getKey() == this.lastVisitedPosition.getKey() + 1 && position.getValue() == this.lastVisitedPosition.getValue() + 1));
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
	
	private void saveGame() {
		this.controller.getMainController().addMainGametoHistory(this.controller.getGame().get());
		try {
			this.controller.getMainController().saveGameHistory();
			this.controller.getMainController().saveBonuses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
