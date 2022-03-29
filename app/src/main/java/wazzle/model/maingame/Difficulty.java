package wazzle.model.maingame;

public enum Difficulty {
	
	// silly values for now
	
	EASY(200,250),
	MEDIUM(100,199),
	HARD(50,99);
	
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
