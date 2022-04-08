package wazzle.model.maingame;

public final class Difficulty {
	private final int lowerBound;
	private final int upperBound;
	private final long time;
	
	public Difficulty(final int lowerBound, final int upperBound, final long time) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.time = time;
	}
	
	public int getLowerBound() {
		return this.lowerBound;
	}
	
	public int getUpperBound() {
		return this.upperBound;
	}
	
	public long getTimeInMilliseconds() {
		return this.time;
	}
}
