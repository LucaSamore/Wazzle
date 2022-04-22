package wazzle.view.controller;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import wazzle.view.FXMLFiles;
import wazzle.view.Loader;
import wazzle.view.SceneSwitcher;

public final class GameTimerViewImpl implements GameTimerView {
	
	private MainGameView mainGameView;
	private AnimationTimer animationTimer;
	private Label timerValueLabel;
	
	/**
	 * {@inheritDoc}
	 */
	public void setLabel(final Label timerValueLabel) {
		this.timerValueLabel = timerValueLabel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {
		this.mainGameView.getMainGameController().getTimer().start();
		this.animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				Platform.runLater(new Runnable(){
					@Override
					public void run() {
						final var timeRemaining = mainGameView.getMainGameController().getTimer().getRemainingTime();
						timerValueLabel.setText("" + timeRemaining);
						if(timeRemaining <= 0) {
							try {
								notifyToMainGame();
								stopAll();
							} catch (IOException e) {
								e.printStackTrace();
								Alert alert = new Alert(AlertType.NONE);
								alert.setContentText("Non so che ca**o sia successo. RIPROVA GRAZIE :)");
								ButtonType exitButton = new ButtonType("Ok riprovo ma non urlare");
								alert.getButtonTypes().setAll(exitButton);
								alert.showAndWait();
							}
						}
					}
				});
			}
		};
		this.animationTimer.start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopAll() throws IOException {
		this.stopTimers();
		this.saveGame();
		if (this.areThereAnyFoundedWord()) {
			this.goToStatistics();
		} else {
			this.goToMainMenu();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopWithoutSave(final ActionEvent event) throws IOException {
		this.stopTimers();
		this.mainGameView.getMainGameController().getMainController().saveBonuses();
		this.switchToMainMenu(event);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void attach(final MainGameView mainGameView) {
		this.mainGameView = mainGameView;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyToMainGame() {
		this.mainGameView.update();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUpdatedController(final MainGameView mainGameView) {
		this.mainGameView = mainGameView;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void gainBonus(final ActionEvent event) {
		this.mainGameView.getMainGameController().useTimeBonus();
		timerValueLabel.setText("" + this.mainGameView.getMainGameController().getTimer().getRemainingTime());
		((Button) event.getSource()).setDisable(true);
	}
	
	/**
	 * Saves all game data.
	 * 
	 * @throws IOException
	 */
	private void saveGame() throws IOException {
		this.mainGameView.getMainGameController().getMainController().addMainGametoHistory(this.mainGameView.getMainGameController().getGame().get());
		this.mainGameView.getMainGameController().getMainController().saveGameHistory();
		this.mainGameView.getMainGameController().getMainController().saveBonuses();
	}
	
	/**
	 * Switch scene to main menu after a button click.
	 * 
	 * @throws IOException 
	 */
	private void switchToMainMenu(final ActionEvent event) throws IOException {
		this.mainGameView.getStage().setUserData(this.mainGameView.getMainGameController().getMainController());
		SceneSwitcher.<MainMenuView>switchScene(event, new MainMenuView(this.mainGameView.getStage()), FXMLFiles.MAIN_MENU.getPath());
	}
	
	/**
	 * Switch scene to main menu.
	 * 
	 * @throws IOException 
	 */
	private void goToMainMenu() throws IOException {
		this.mainGameView.getStage().setUserData(this.mainGameView.getMainGameController().getMainController());
		DoubleProperty visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(this.mainGameView.getStage().widthProperty(),this.mainGameView.getStage().heightProperty()));
		MainMenuView controller = new MainMenuView(this.mainGameView.getStage());
		final Scene scene;
		scene = new Scene(Loader.<MainMenuView, Parent>loadFXMLElement(controller, FXMLFiles.MAIN_MENU.getPath()), 
							this.mainGameView.getStage().getWidth(), this.mainGameView.getStage().getHeight());
		this.mainGameView.getStage().setScene(scene);
		this.mainGameView.getStage().show();
	}
	
	/**
	 * Switch scene to main game statistics.
	 * 
	 * @throws IOException 
	 */
	private void goToStatistics() throws IOException {
		this.mainGameView.getStage().setUserData(this.mainGameView.getMainGameController());
		DoubleProperty visualUnit = new SimpleDoubleProperty();
		visualUnit.bind(Bindings.min(this.mainGameView.getStage().widthProperty(),this.mainGameView.getStage().heightProperty()));
		StatisticsMainGameView controller = new StatisticsMainGameView(this.mainGameView.getStage());
		final Scene scene;
		scene = new Scene(Loader.<StatisticsMainGameView, Parent>loadFXMLElement(controller, FXMLFiles.MAIN_GAME_STATS.getPath()), 
							this.mainGameView.getStage().getWidth(), this.mainGameView.getStage().getHeight());
		this.mainGameView.getStage().setScene(scene);
		this.mainGameView.getStage().show();
	}
	
	/**
	 * Stops all timers.
	 */
	private void stopTimers() {
		this.mainGameView.getMainGameController().getTimer().stop();
		this.animationTimer.stop();
	}
	
	/**
	 * Returns if there are any words founded.
	 */
	private boolean areThereAnyFoundedWord() {
		return this.mainGameView.getMainGameController().getGame().get().wordsFound().size() > 0;
	}
}
