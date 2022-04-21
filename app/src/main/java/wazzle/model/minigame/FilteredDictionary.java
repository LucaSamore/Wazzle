package wazzle.model.minigame;

import java.util.Set;

import wazzle.model.common.Dictionary;

public abstract class FilteredDictionary implements Dictionary{
	private Dictionary dictionary;
	
	protected FilteredDictionary(final Dictionary words){
		this.dictionary = words;		
	}

	@Override
	public Set<String> getWords() {
		return dictionary.getWords();
	}
}