package wazzle.model.maingame;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * This interface is an API for managing the Wazzle main game flow.
 * It provides methods for gaining information about the state of the game and
 * operations for changing the state as well.
 */
public interface MainGame {
	
	/**
	 * Given a word, checks if it's in {@link MainGame#wordsToBeFound()} and adds it to {@link MainGame#wordsFound()}
	 * @return {@code true} if the given word was added to {@link MainGame#wordsFound()}, {@code false} otherwise.
	 * @see java.util.Set
	 */
	boolean tryWord(final String word);
	
	/**
	 * Returns a {@code Set} containing the words you have already found inside the {@code Grid}.
	 * @return a {@code Set<String>}
	 * @see java.util.Set
	 */
	Set<String> wordsFound();
	
	/**
	 * Returns a {@code Set} containing the words you still need to find inside the {@code Grid}.
	 * @return a {@code Set<String>}
	 * @see java.util.Set
	 */
	Set<String> wordsToBeFound();
	
	/**
	 * Returns a {@code Set} containing the words you can find inside the {@code Grid}.
	 * @return a {@code Set<String>}.
	 * @see java.util.Set
	 */
	Set<String> wordsCanBeFound();
	
	/**
	 * Returns a {@code Set} containing the letters inside the {@code Grid}.
	 * @return a {@code Set<Letter>}.
	 * @see Letter
	 * @see java.util.Set
	 */
	Set<Letter> lettersInGrid();
	
	/**
	 * Returns the {@link Grid} used for this game.
	 * @return a {@code Grid} object.
	 */
	Grid getGrid();
	
	/**
	 * Returns when the game started as a {@code LocalDateTime} object.
	 * @return a {@code LocalDateTime} object.
	 */
	LocalDateTime getDateTime();
	
	/**
	 * Returns the number of failed attempts.
	 * @return an {@code int} describing the failed attempts.
	 */
	int getFailedAttempts();
	
	/**
	 * Sets the current score.
	 * @param newScore an {@code int} representing the new score.
	 * @return void.
	 */
	void setCurrentScore(final int newScore);
	
	/**
	 * Returns the current score.
	 * @return an {@code int} describing the current score.
	 */
	int getCurrentScore();
	
	/**
	 * Given a word it returns his score.
	 * @param word a {@code String} representing the given word.
	 * @return an {@code int} describing the score of the given word.
	 */
	int getScoreFromWord(final String word);
}
