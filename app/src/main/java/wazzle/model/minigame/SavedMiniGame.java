package wazzle.model.minigame;

import com.google.gson.annotations.Expose;
import java.util.List;
import wazzle.model.minigame.attempt.MiniGameWordImpl;

/**
 * This class works as a memento of the current minigame, it stores all the relevant information
 * about the current minigame.
 */
public final class SavedMiniGame {

  @Expose private List<MiniGameWordImpl> guessedWords;

  @Expose private String targetWord;

  public SavedMiniGame(final String targetWord, final List<MiniGameWordImpl> guessedWordsSoFar) {
    this.guessedWords = guessedWordsSoFar;
    this.targetWord = targetWord;
  }

  public String getSavedTargetWord() {
    return this.targetWord;
  }

  public List<MiniGameWordImpl> getSavedMiniGameWordsSoFar() {
    return this.guessedWords;
  }
}
