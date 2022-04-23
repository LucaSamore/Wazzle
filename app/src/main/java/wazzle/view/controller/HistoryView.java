package wazzle.view.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wazzle.controller.common.WazzleController;
import wazzle.model.maingame.MainGame;
import wazzle.view.FXMLFiles;
import wazzle.view.SceneSwitcher;

public final class HistoryView extends View<WazzleController>{

	@FXML
	public GridPane mainGrid;

	@FXML
	public Label upperLabel;

	@FXML
	private Button leaveButton;

	@FXML
	private VBox mainVbox;

	private final StringExpression standardFontSize;
	private final StringExpression headerFontSize;
	private final StringExpression titleFontSize;
	
	/**
	 * Construct a new HistoryView controller. 
	 * 
	 * @param stage the main menu stage.
	 */
	public HistoryView(final Stage stage) {
		this.stage = stage;
		this.controller = (WazzleController) stage.getUserData();
		this.visualUnit = new SimpleDoubleProperty();
		this.visualUnit.bind(Bindings.min(stage.heightProperty().multiply(0.03), stage.widthProperty().multiply(0.03)));
		this.standardFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.asString(), ";");
		this.headerFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(1.4).asString(), ";");
		this.titleFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(1.8).asString(), ";");
	}

	/**
	 * Switch scene from game history to main menu.
	 */
	@Override
	public void nextScene(ActionEvent event) throws IOException {
		SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), FXMLFiles.MAIN_MENU.getPath());
	}

	/**
	 * Build the game history view with all its graphics and all its information.
	 */
	@Override
	protected void buildView() {
		this.setGraphics();
		this.createColumnHeader();
		this.populateGrid();
	}

	/**
	 * Set the graphics of the game history view.
	 */
	@Override
	protected void setGraphics() {
		this.leaveButton.styleProperty().bind(standardFontSize);
		this.upperLabel.styleProperty().bind(titleFontSize);
		this.upperLabel.getStyleClass().add("letters");
		this.mainGrid.hgapProperty().bind(visualUnit.divide(5));
		this.mainGrid.vgapProperty().bind(visualUnit.divide(4));
		this.mainGrid.setPadding(new Insets(20, 20, 20, 20));
		this.mainVbox.spacingProperty().bind(visualUnit);
	}
	
	/**
	 * Populate the game history table with all the informations about the last ten games.
	 */
	private void populateGrid() {
		var indexRow = 1;
		var paddingInsets = new Insets(5, 5, 5, 8);
		for (MainGame mainGame : controller.getGameHistory()) {
			var indexColumn = 0;
			indexRow++;
			this.addField(String.valueOf(mainGame.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), indexRow, indexColumn, standardFontSize, paddingInsets);
			indexColumn++;
			this.addField(String.valueOf(mainGame.wordsFound().size()), indexRow, indexColumn, standardFontSize, paddingInsets);
			indexColumn++;
			this.addField(String.valueOf((int) mainGame.getCurrentScore()), indexRow, indexColumn, standardFontSize, paddingInsets);
		}
	}
	
	/**
	 * Add a field to game history table.
	 * 
	 * @param content the content of the field which has to be inserted.
	 * @param indexRow the row index of the field.
	 * @param indexColumn the column index of the field.
	 * @param fontSize the font size of the field.
	 * @param paddingInsets the padding of the field.
	 */
	private void addField(final String content, final int indexRow, final int indexColumn, final StringExpression fontSize, final Insets paddingInsets) {
		var incave = new StackPane();
		var letterLabel = new Label(content);
		letterLabel.getStyleClass().add("letters");
		incave.getStyleClass().add("longIncave");
		letterLabel.styleProperty().bind(fontSize);
		letterLabel.setPadding(paddingInsets);
		StackPane.setAlignment(letterLabel, Pos.CENTER);
		incave.getChildren().add(letterLabel);
		this.mainGrid.add(incave, indexColumn, indexRow);
	}

	/**
	 * Creates game history table header.
	 */
	private void createColumnHeader() {
		var indexRow = 0;
		var indexColumn = 0;
		var paddingInsets = new Insets(5, 8, 5, 10);
		this.addField("DATA", indexRow, indexColumn, headerFontSize, paddingInsets);
		indexColumn++;
		this.addField("PAROLE TROVATE", indexRow, indexColumn, headerFontSize, paddingInsets);
		indexColumn++;
		this.addField("PUNTEGGIO", indexRow, indexColumn, headerFontSize, paddingInsets);
	}
}
