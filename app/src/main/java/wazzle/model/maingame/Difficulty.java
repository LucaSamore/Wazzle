package wazzle.model.maingame;

public enum Difficulty {
	
	// silly but not so silly values for now --> we need a formula :)
	EASY4X4(126, 250, 240000L),
	HARD4X4(50, 125, 180000L),
	EASY5X5(201, 325, 240000L),
	HARD5X5(125, 200, 180000L),
	EASY6X6(351, 500, 240000L),
	HARD6X6(250, 350, 180000L);
	
	private int lowerBound;
	private int upperBound;
	private long time;
	
	Difficulty(final int lowerBound, final int upperBound, final long time) {
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
	
	public long getTime() {
		return this.time;
	}
}
