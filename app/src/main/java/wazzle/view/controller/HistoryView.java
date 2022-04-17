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
import wazzle.view.SceneSwitcher;

public class HistoryView {

	@FXML
	public GridPane mainGrid;

	@FXML
	public Label upperLabel;

	@FXML
	private Button leaveButton;

	@FXML
	private VBox mainVbox;

	private Stage stage;
	private SimpleDoubleProperty visualUnit;
	private final StringExpression standardFontSize;
	private final StringExpression headerFontSize;
	private final StringExpression titleFontSize;
	private final WazzleController wazzleController;

	private static final String MAIN_MENU_PATH = "layouts/mainMenu.fxml";
	
	public HistoryView(Stage stage) {
		this.stage = stage;
		this.wazzleController = (WazzleController) stage.getUserData();
		this.visualUnit = new SimpleDoubleProperty();
		this.visualUnit.bind(Bindings.min(stage.heightProperty().multiply(0.03), stage.widthProperty().multiply(0.03)));
		this.standardFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.asString(), ";");
		this.headerFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(1.4).asString(), ";");
		this.titleFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(1.8).asString(), ";");
	}

	public void initialize() {
		setGraphic();
		createColumnHeader();
		populateGrid();
	}
	
	private void addCell(final String title, final int indexRow, final int indexColumn, final StringExpression fontSize, final Insets paddingInsets) {
		var incave = new StackPane();
		var letterLabel = new Label(title);
		letterLabel.getStyleClass().add("letters");
		incave.getStyleClass().add("longIncave");
		letterLabel.styleProperty().bind(fontSize);
		letterLabel.setPadding(paddingInsets);
		StackPane.setAlignment(letterLabel, Pos.CENTER);
		incave.getChildren().add(letterLabel);
		this.mainGrid.add(incave, indexColumn, indexRow);
	}

	private void createColumnHeader() {
		var indexRow = 0;
		var indexColumn = 0;
		var paddingInsets = new Insets(5, 8, 5, 10);
		this.addCell("DATA", indexRow, indexColumn, headerFontSize, paddingInsets);
		indexColumn++;
		this.addCell("PAROLE TROVATE", indexRow, indexColumn, headerFontSize, paddingInsets);
		indexColumn++;
//		this.addCell("PAROLE TOTALI", indexRow, indexColumn);
//		indexColumn++;
		this.addCell("PUNTEGGIO", indexRow, indexColumn, headerFontSize, paddingInsets);
	}

	private void populateGrid() {
		var indexRow = 1;
		var paddingInsets = new Insets(5, 5, 5, 8);
		for (MainGame mainGame : wazzleController.getGameHistory()) {
			var indexColumn = 0;
			indexRow++;
			this.addCell(String.valueOf(mainGame.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), indexRow, indexColumn, standardFontSize, paddingInsets);
			indexColumn++;
			this.addCell(String.valueOf(mainGame.wordsFound().size()), indexRow, indexColumn, standardFontSize, paddingInsets);
			indexColumn++;
			this.addCell(String.valueOf((int) mainGame.getCurrentScore()), indexRow, indexColumn, standardFontSize, paddingInsets);
		}
	}
	
	private void setGraphic() {
		this.leaveButton.styleProperty().bind(standardFontSize);
		this.upperLabel.styleProperty().bind(titleFontSize);
		this.upperLabel.getStyleClass().add("letters");
		this.mainGrid.hgapProperty().bind(visualUnit.divide(5));
		this.mainGrid.vgapProperty().bind(visualUnit.divide(4));
		this.mainGrid.setPadding(new Insets(20, 20, 20, 20));
		this.mainVbox.spacingProperty().bind(visualUnit);
	}

	public void goToScene(final ActionEvent event) throws IOException {
		this.stage.setUserData(this.wazzleController);
		SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.stage), MAIN_MENU_PATH);
	}

}
