package wazzle.model.minigame;

public enum Result {
	
	CORRECT(0),

	CORRECT_WRONG_PLACE(1),
	
	WRONG(2);
	
	private int state;
	
	private Result(int state) {
		this.state = state;
	}

	public int getColor() {
		return state;
	}
}
