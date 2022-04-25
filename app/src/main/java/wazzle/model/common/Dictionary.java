package wazzle.model.common;

import java.util.Set;

/** Act as container for all the words in a Set of Strings. */
public interface Dictionary {

  /**
   * Returns a {@code Set<String>} containing the words inside the dictionary.
   *
   * @return {@code Set<String>} a set containing all the words.
   * @see java.util.Set
   */
  public Set<String> getWords();
}
