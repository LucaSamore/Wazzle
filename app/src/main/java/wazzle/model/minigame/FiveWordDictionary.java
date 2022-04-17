package wazzle.model.minigame;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;

public final class FiveWordDictionary extends FilteredDictionary{

	public FiveWordDictionary(Dictionary dictionary) {
		super(dictionary);
	}

	@Override
	public Set<String> getWords() {
		return this.filterWords(super.getWords());
	}
	
	private Set<String> filterWords(Set<String> words) {
		return words.stream().filter(suitableWords -> suitableWords.length() == 5).collect(Collectors.toSet());
	}
}
