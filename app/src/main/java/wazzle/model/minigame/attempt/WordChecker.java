package wazzle.model.minigame.attempt;

/**
 * Computes the attempt result returning a {@link MiniGameWord} containing the result.
 *
 * @param wordToCompute - the string containing the wrong word to compute.
 * @return MiniGameWord - the computed attempt
 */
public interface WordChecker {

  MiniGameWord computeAttemptResult(String wordToCompute);

  /**
   * Checks if is correct word.
   *
   * @param guessedWord - the guessed word from the user.
   * @return true, if is the correct word.
   */
  boolean isCorrectWord(String guessedWord);
}
