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
	 * Given a string containing the word inserted by the user, returns the corresponding
	 * computed word.
	 *
	 * @param guessedWord the guessed word
	 * @see {@link WordChecker}, {@link MiniGameWord}
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
	 * Gets the all guessed words of the current minigame.
	 *
	 * @return all the guessed words
	 */
	List<MiniGameWordImpl> getAllGuessedWords();

    /**
     * Returns the current state of the relevant fields of the Minigame.
     * @see {@link SavedMiniGame}, {@link MiniGame}
     * @return a new Snapshot representing the current state of Minigame.
     */
	SavedMiniGame takeMiniGameSnapshot();

    /**
     * Restore a previous state of a Minigame;
     * 
     * @param savedMinigame - the previous saved state of a Minigame.
     * @see {@link SavedMiniGame}, {@link MiniGame},
     */
    void loadMiniGame(SavedMiniGame savedMinigame);

}
