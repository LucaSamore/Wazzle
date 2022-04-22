package wazzle.model.minigame;

import java.util.List;

/**
 * This class represents a MinigameWord.
 */
public interface MiniGameWord {

	/**
	 * Gets the composite word.
	 * 
	 * @see wazzle.model.minigame.WordElement
	 * @return The stored composite word
	 */
	public List<WordElement> getCompositeWord();

	/**
	 * Sets the composite word.
	 * 
	 * @param wordToSet - The new composite word that must be set.
	 */
	public void setCompositeWord(List<WordElement> wordToSet);

}
