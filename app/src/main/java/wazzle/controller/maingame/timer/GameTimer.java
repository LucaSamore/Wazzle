package wazzle.controller.maingame.timer;

/**
 * This interface represents the controller for the game timer. It provides methods for start, stop
 * and update time.
 */
public interface GameTimer {

  /** Starts the {@link GameTimer}. */
  void start();

  /** Stops the {@link GameTimer}. */
  void stop();

  /**
   * Gives the the remaining time of the {@link GameTimer}.
   *
   * @return long the remaining time.
   */
  long getRemainingTime();

  /**
   * Increments the time remaining of the {@link GameTimer}.
   *
   * @return long the updated remaining time.
   */
  void updateTime(long updatedTime);
}
