package wazzle.model.minigame.word;

import java.util.Set;

public interface ExtractedWordManager {

  /**
   * return a set containing all the available words.
   *
   * @return String the list of available words.
   */
  Set<String> getAvailableWords();

  /**
   * Check if there are still extractable words in the dataset.
   *
   * @return true, if there are still extractable words in the dataset, false otherwise.
   */
  boolean areThereAnyAvailableWords();

  /** If all words are removed from the current Dataset, this method replenishes it. */
  void restoreAllAvailableWords();

  /**
   * Remove the word from the set to avoid a duplicated extraction.
   *
   * @param word the word
   */
  void removeFromAvailableWords(String word);
}
