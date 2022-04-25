package wazzle.model.minigame.word;

public interface WordsDispenser {

  /**
   * Extract a random word from the dataset.
   *
   * @return the string containing the random extracted word from the dataset.
   */
  String extractWord();
}
