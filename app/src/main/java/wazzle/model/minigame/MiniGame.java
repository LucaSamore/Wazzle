package wazzle.model.minigame;

import java.time.LocalDateTime;

public interface MiniGame {
	enum State {
		IN_PROGRESS(0),
		FAILED(1),
		WON(2);

		private final int currentState;

		State(int state) {
			this.currentState = state;
		}

		public int getState() {
			return currentState;
		}
	}

	public boolean loadMiniGame();

	public LocalDateTime getGameStarTimeDate();

	public String getTargetWord();

	public boolean isWordCorrect(String guessedWord);

	public MiniGameWord computeResult();

	public int getCurrentAttemptsNumber();

	public int getWordLenght();

	public int getMaxAttemptsNumber();

	public void setGameState(State state);

	public State getGameState();

}
