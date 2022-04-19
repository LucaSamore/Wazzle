package wazzle.controller.maingame;

public interface GameTimer {
	
	/**
	 * Starts the timer.
	 */
	 void start();

	 /**
	  * Stops the timer.
	  */
	 void stop();

	 /**
	  * Gives the the remaining time.
	  * 
	  * @return long the remaining time.
	  */
	 long getRemainingTime();
	 
	 /**
	  * Increments the time remaining.
	  * 
	  * @return long the updated remaining time.
	  */
	 void updateTime(final long updatedTime);
	
}

