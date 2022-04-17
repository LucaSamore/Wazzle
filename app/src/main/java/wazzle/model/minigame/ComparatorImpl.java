package wazzle.model.minigame;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import wazzle.model.common.Dictionary;

public class ComparatorImpl implements Comparator {
	
	private static final Random RANDOM = new Random();
	
	private Set<String> availableWords;

	public ComparatorImpl(final Dictionary dictionary) {
		this.availableWords = new HashSet<>(dictionary.getWords());
	}

	private String pickRandomWord() {
		return this.availableWords.toArray()[RANDOM.nextInt(availableWords.size())].toString();
	}
	
	@Override
	public boolean isAvailableWordsEmpty() {
		return this.availableWords.isEmpty();
	}
	
	@Override
	public String getSuitableWord() {
		return pickRandomWord();

	}

	@Override
	public void refreshDataSet(final Dictionary dictionary) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveDataSet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromSavedDataset(String suitableWord) {
		// TODO Auto-generated method stub
		
	}


}
