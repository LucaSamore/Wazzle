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
public class ComparatorImpl implements Comparator {


	private Set<String> availableWords;
	private Dictionary dictionary;

	public ComparatorImpl(final Dictionary dictionary) {
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

//	private String pickRandomWord() {
//		return this.availableWords.toArray()[RANDOM.nextInt(availableWords.size())].toString();
//	}
//
//	@Override
//	public boolean isDatasetEmpty() {
//		return this.availableWords.isEmpty();
//	}
//
//	@Override
//	public String getSuitableWord() {
//		return pickRandomWord();
//
//	}
//
//	@Override
//	public void refreshDataSet(final Dictionary dictionary) {
//		this.availableWords = dictionary.getWords();
//	}
//
//	@Override
//	public void saveDataSet() {
//		// scrivi su file this.availableWords
//	}
//
//	@Override
//	public void removeFromSavedDataset(String suitableWord) {
//		this.availableWords.remove(suitableWord);
//	}

}
