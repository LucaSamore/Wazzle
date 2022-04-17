package wazzle.model.minigame;

import java.util.Set;

import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;

public class FilteredDictionary implements Dictionary{
	private Dictionary dictionary;
	
	public FilteredDictionary(Dictionary words){
		this.dictionary = words;		
	}

	@Override
	public Set<String> getWords() {
		return dictionary.getWords();
	}
}