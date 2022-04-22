package wazzle.model.minigame;

import java.util.List;

public interface MiniGame {

	/**
	 * Enum representing the State of the game.
	 */
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

	/**
	 * Load a previous mini game.
	 *
	 */
	void loadMiniGame();

	/**
	 * Compute result.
	 *
	 * @param guessedWord the guessed word
	 * @see wazzle.model.minigame.WordChecker
	 * @return the mini game word
	 */
	MiniGameWord computeResult(String guessedWord);

	/**
	 * return the length of the current word.
	 *
	 * @return the word length
	 */
	int getWordLength();

	/**
	 * Gets the max attempts number for this game.
	 *
	 * @return the max attempts number
	 */
	int getMaxAttemptsNumber();

	/**
	 * Gets the state of the current game State.
	 *
	 * @return the game state
	 */
	State getGameState();

	/**
	 * Gets the attempts number of the current minigame.
	 *
	 * @return the current attempts number
	 */
	int getCurrentAttemptsNumber();

	/**
	 * Gets the target word of the current minigame.
	 *
	 * @return the target word
	 */
	String getTargetWord();

	/**
	 * Gets the all guessed words of the current minigame..
	 *
	 * @return all the guessed words
	 */
	List<MiniGameWordImpl> getAllGuessedWords();


	void setWordChecker(WordChecker wordChecker);

	/**
	 * Sets the current game state.
	 *
	 * @param state the new game state
	 */
	void setGameState(State state);

}
