package wazzle.model.maingame;

public enum Difficulty {
	
	// silly but not so silly values for now --> we need a formula :)
	EASY(201,350),
	HARD(75,200);
	
	private int lowerBound;
	private int upperBound;
	
	Difficulty(final int lowerBound, final int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public int getLowerBound() {
		return this.lowerBound;
	}
	
	public int getUpperBound() {
		return this.upperBound;
	}
}
