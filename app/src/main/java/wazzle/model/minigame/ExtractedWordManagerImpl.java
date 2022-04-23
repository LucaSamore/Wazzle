package wazzle.model.minigame;

import java.util.HashSet;
import java.util.Set;

import wazzle.model.common.Dictionary;

/**
 * this class is used to compare differents datasets and pick a random word.
 * 
 * @param dictionary - that contains all the word of five letters. 
 * 
 * @see FiveLetterDictionary
 * 
 */
public class ExtractedWordManagerImpl implements ExtractedWordManager {


	private Set<String> availableWords;
	private Dictionary dictionary;

	public ExtractedWordManagerImpl(final Dictionary dictionary) {
		this.dictionary = dictionary;
		this.restoreAllAvailableWords();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getAvailableWords() {
		return Set.copyOf(this.availableWords);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean areThereAnyAvailableWords() {
		return !this.availableWords.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void restoreAllAvailableWords() {
		this.availableWords = new HashSet<>(this.dictionary.getWords());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeFromAvailableWords(final String word) {
		this.availableWords.remove(word);
	}
}
