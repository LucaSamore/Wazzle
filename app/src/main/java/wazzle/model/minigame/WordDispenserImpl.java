package wazzle.model.minigame;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import wazzle.model.common.Dictionary;

public class WordDispenserImpl implements WordDispenser {
	private static final Random RANDOM = new Random();
	private Set<String> availableWords;

	/**
	 * @param
	 */
	public WordDispenserImpl(final Dictionary savedInJsonDictionary) {

		this.availableWords = new HashSet<>(savedInJsonDictionary.getWords());
	}

	public void refreshDataset(final Dictionary originalDictionary) {
//		scrivi su file = originalDictionary;
	}

	public boolean isAvailableWordsEmpty() {
		return this.availableWords.isEmpty();
	}

	public String getSuitableWord() {
		return pickRandomWord();

	}

	private String pickRandomWord() {
		String targetWord = this.availableWords.toArray()[RANDOM.nextInt(availableWords.size())].toString();
		availableWords.remove(targetWord);
		// Scrivi su file availableWords
		return targetWord;
	}
}
