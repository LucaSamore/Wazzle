package wazzle.model.minigame;

import java.time.LocalDateTime;

public interface MiniGame {
	enum State {
		IN_PROGRESS(0), FAILED(1), WON(2);

		private final int currentState;

		State(int state) {
			this.currentState = state;
		}

		public int getState() {
			return currentState;
		}
	}

	boolean loadMiniGame();

	String getTargetWord();

	boolean isWordCorrect(String guessedWord);

	int getWordLenght();

	int getMaxAttemptsNumber();

	void setGameState(State state);

	State getGameState();

	int getCurrentAttemptsNumber();

	MiniGameWord computeResult(String guessedWord);
	

}
