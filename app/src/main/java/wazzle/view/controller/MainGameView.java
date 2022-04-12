package wazzle.view.controller;

import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

	private final Stage stage;

	private final StringExpression standardFontSize;
	@SuppressWarnings("unused")
	private final StringExpression letterFontSize;
	@SuppressWarnings("unused")
	private final StringExpression pointFontSize;
	private final StringExpression titleFontSize;
	private final DoubleProperty visualUnit;

	public MainGameView(final Stage stage) {
		this.stage = stage;
		this.stage.setWidth(stage.widthProperty().get());
		this.stage.setHeight(stage.heightProperty().get());
		this.visualUnit = new SimpleDoubleProperty();
		this.visualUnit.bind(Bindings.min(stage.heightProperty().multiply(0.05), stage.widthProperty().multiply(0.05)));
		this.standardFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.asString(), ";");
		this.letterFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(2).asString(), ";");
		this.pointFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(0.8).asString(), ";");
		this.titleFontSize = Bindings.concat("-fx-font-size: ", this.visualUnit.multiply(2).asString(), ";");
		
	}

	public void initialize() {
		this.setGraphic();
	}

	private void setGraphic() {
		this.grid.styleProperty().bind(Bindings.concat("-fx-padding: ", this.visualUnit.asString(), ";"));
		this.rightPanel.styleProperty().bind(Bindings.concat("-fx-spacing: ", this.visualUnit.asString(), ";"));		
		this.mainVbox.styleProperty().bind(this.standardFontSize);
		this.titleLabel.styleProperty().bind(this.titleFontSize);
		this.leftPanel.minWidthProperty().bind(this.pointsLabel.widthProperty());		
		
		this.pointsLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		this.pointsValueLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		
		this.timerLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		this.timerValueLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		
		this.bonusScoreButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		this.bonusTimeButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		this.bonusWordButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	}

	public void goToScene(final ActionEvent event) throws IOException {

	}

	public void getBonus(final ActionEvent event) {
		
	}
}
