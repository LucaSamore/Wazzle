package wazzle.model.minigame;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import wazzle.model.common.Dictionary;

/**
 * this class is used to compare differents datasets and pick a random word.
 * 
 * @param the file txt containing all words of length five
 * 
 * @see FiveLetterDictionary
 * 
 */
public class ExtractedWordManagerImpl implements ExtractedWordManager {


	private Set<String> availableWords;
	private Dictionary dictionary;

	public ExtractedWordManagerImpl(final Dictionary dictionary) {
		this.dictionary = dictionary;
		this.restoreAvailableWords();
	}

	@Override
	public Set<String> getAvailableWords() {
		return Set.copyOf(this.availableWords);
	}

	@Override
	public boolean areThereAnyAvailableWords() {
		return this.availableWords.isEmpty();
	}

	@Override
	public void restoreAvailableWords() {
		this.availableWords = new HashSet<>(this.dictionary.getWords());
	}

	@Override
	public void notAvailableAnymore(final String word) {
		this.availableWords.remove(word);
	}
}
