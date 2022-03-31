package wazzle.model.maingame;

public enum Difficulty {
	
	EASY(250,300),
	MEDIUM(151,250),
	HARD(100,150);
	
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
