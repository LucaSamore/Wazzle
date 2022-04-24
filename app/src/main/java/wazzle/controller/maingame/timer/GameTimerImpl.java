package wazzle.controller.maingame.timer;

import java.util.Timer;

import java.util.TimerTask;

/**
 * This class is a concrete implementation for {@link GameTimer}
 * It provides methods for manage the timer.
 */
public final class GameTimerImpl implements GameTimer {
	/**
	 * The delay.
	 */
	private static final long DELAY = 1000L;
	/**
	 * The period.
	 */
	private static final long PERIOD = 1000L;
	private long timeRemaining;
	private TimerTask timer;

	/**
	 * Construct a new {@link GameTimer}.
	 * 
	 * @param timeRemaining the remaining time.
	 */
	public GameTimerImpl(final long timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {
		this.timer = new TimerTask() {
			@Override
	        public void run() {
				timeRemaining--;
	
				if (timeRemaining <= 0) {
					stop();
					return;
				}
			}
		};
		new Timer().scheduleAtFixedRate(this.timer, DELAY, PERIOD);
		}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop() {
		this.timer.cancel();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getRemainingTime() {
		return this.timeRemaining;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateTime(final long updatedTime) {
		this.timeRemaining = updatedTime;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (timeRemaining ^ (timeRemaining >>> 32));
		result = prime * result + ((timer == null) ? 0 : timer.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameTimerImpl other = (GameTimerImpl) obj;
		if (timeRemaining != other.timeRemaining)
			return false;
		if (timer == null) {
			if (other.timer != null)
				return false;
		} else if (!timer.equals(other.timer))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "GameTimerImpl [timeRemaining=" + timeRemaining + ", timer=" + timer + "]";
	}
}



