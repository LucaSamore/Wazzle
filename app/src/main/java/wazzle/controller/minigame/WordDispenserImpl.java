package wazzle.controller.minigame;

import wazzle.controller.common.WazzleController;
import wazzle.model.minigame.Comparator;
import wazzle.model.minigame.ComparatorImpl;

public class WordDispenserImpl implements WordDispenser {
	@Override
	public String getSuitableWord(final WazzleController mainController) {
		Comparator comparator = new ComparatorImpl(/*gli passo il dataset preso da file (JSON)*/);
		if (comparator.isAvailableWordsEmpty()) {
			comparator.refreshDataSet(mainController.getFileController().getDataset("dataset.txt"));
		}
		String suitableWord = comparator.getSuitableWord();
		comparator.removeFromSavedDataset(suitableWord);
		comparator.saveDataSet(/*gli passo il dataset da aggiornare (JSON)*/);
		return suitableWord;
	}
}
