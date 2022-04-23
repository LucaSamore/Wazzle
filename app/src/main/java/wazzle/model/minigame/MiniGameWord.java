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
}
