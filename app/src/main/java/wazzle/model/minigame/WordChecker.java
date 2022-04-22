package wazzle.model.minigame;

public interface WordChecker {

	/**
	 * Computes the attempt result.
	 *
	 * @param wordToCompute - the string containing the wrong word to compute.
	 * @return MiniGameWord - the computed attempt
	 */
	MiniGameWord computeAttemptResult(String wordToCompute);

	/**
	 * Checks if is correct word.
	 *
	 * @param guessedWord - the guessed word from the user.
	 * @return true, if is the correct word.
	 */
	boolean isCorrectWord(String guessedWord);

}
