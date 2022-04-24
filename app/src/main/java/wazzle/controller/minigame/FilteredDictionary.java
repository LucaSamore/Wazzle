package wazzle.controller.minigame;

import java.util.Set;

import wazzle.model.common.Dictionary;

/**
 * Base abstract decorator, all others decorator must extend this class.
 * 
 * @see wazzle.controller.minigame.FiveLetterDictionary
 */
public abstract class FilteredDictionary implements Dictionary {
	private Dictionary dictionary;

	protected FilteredDictionary(final Dictionary words) {
		this.dictionary = words;
	}

	@Override
	public Set<String> getWords() {
		return dictionary.getWords();
	}
}