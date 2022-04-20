package wazzle.view.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public interface GameTimerView {
	
	/**
	 * Set the timeValueLabel.
	 * 
	 * @param timerValueLabel the label which contains the remaining time in the main game view.
	 */
	void setLabel(Label timerValueLabel);
	
	/**
	 * Starts the animation timer.
	 * 
	 * @param timerLabel the timer label that have to be updated.
	 */
	 void start();

	 /**
	  * Stops the animation timer.
	  * 
	  * @throws IOException 
	  */
	 void stopAll() throws IOException;
	 
	 /**
	  * Stops the animation timer without saving the game.
	  * 
	  * @param event the event.
	  * @throws IOException 
	  */
	 void stopWithoutSave(ActionEvent event) throws IOException;
	 
	 /**
	  * Set the MainGameView.
	  * 
	  * @param mainGameView the main game controller that has to be attached.
	  */
	 void attach(MainGameView mainGameView);

	 /**
	  * Notifies to MainGame that the time is over.
	  */
	 void notifyToMainGame();
		
	 /**
	  * Sets the updated controller.
	  * 
	  * @param mainGameView the main game controller updated.
	  */
	 void setUpdatedController(MainGameView mainGameView);
	 
	 /**
	  * Gains the time bonus.
	  * 
	  * @param event the request of obtaining a time bonus.
	  */
	 void gainBonus(final ActionEvent event);
}
