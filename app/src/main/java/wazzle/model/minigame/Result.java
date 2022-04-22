package wazzle.model.minigame;

public enum Result {
	
	CORRECT(0), CORRECT_WRONG_PLACE(1), WRONG(2);

	private final int state;

	private Result(final int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
}
