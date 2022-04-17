package wazzle.model.minigame;

import wazzle.model.common.Dictionary;

public interface Comparator {
	
	public String getSuitableWord();

	public boolean isAvailableWordsEmpty();

	public void removeFromSavedDataset(String suitableWord);

	public void saveDataSet();

	public void refreshDataSet(Dictionary dictionary);

}
