package wazzle.model.minigame;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;

//rimosso final da dictionaryImpl
public final class FilteredDictionaryImpl extends DictionaryImpl implements Dictionary {

	private final Set<String> filteredwords;

	public FilteredDictionaryImpl(Set<String> words) {
		super(words);
		this.filteredwords = this.filterWords(words);
	}

	private Set<String> filterWords(Set<String> words) {
		return words.stream().filter(suitableWords -> suitableWords.length() == 5).collect(Collectors.toSet());
	}

	@Override
	public Set<String> getWords() {
		System.out.println(this.filteredwords);
		return Collections.unmodifiableSet(this.filteredwords);
	}

}
