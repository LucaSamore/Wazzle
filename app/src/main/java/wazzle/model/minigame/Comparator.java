package wazzle.model.minigame;

import java.util.Set;

import wazzle.model.common.Dictionary;

public interface Comparator {
	
//	public String getSuitableWord();

	Set<String> getAvailableWords();
	
	boolean areThereAnyAvailableWords();
	
	void restoreAvailableWords();

	void notAvailableAnymore(String word);

//	public void removeFromSavedDataset(String suitableWord);
//
//	public void saveDataSet();
//
//	public void refreshDataSet(Dictionary dictionary);

}
