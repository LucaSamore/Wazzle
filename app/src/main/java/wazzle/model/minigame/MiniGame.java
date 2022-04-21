package wazzle.model.minigame;

import java.util.List;

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

	MiniGameWord computeResult(String guessedWord);

	int getWordLenght();

	int getMaxAttemptsNumber();

	State getGameState();

	int getCurrentAttemptsNumber();

	String getTargetWord();

	List<MiniGameWordImpl> getAllGuessedWords();

	void setWordChecker(WordChecker wordChecker);

	void setGameState(State state);

}
