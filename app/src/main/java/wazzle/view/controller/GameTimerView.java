package wazzle.view.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

/** This interface provides methods for managing the animation timer. */
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
   * @param event The exit button click event.
   * @throws IOException
   */
  void stopWithoutSave(ActionEvent event) throws IOException;

  /**
   * Set the {@link MainGameView} controller.
   *
   * @param mainGameView The {@link MainGameView} controller that has to be attached.
   */
  void attach(MainGameView mainGameView);

  /** Notifies to {@link MainGame} that the time is over. */
  void notifyToMainGame();

  /**
   * Sets the updated {@link MainGameView} controller.
   *
   * @param mainGameView the updated {@link MainGameView} controller.
   */
  void setUpdatedController(MainGameView mainGameView);

  /**
   * Gains the {@link TimeBonus}.
   *
   * @param event The request of obtaining a {@link TimeBonus}.
   */
  void gainBonus(ActionEvent event);
}
