package wazzle.model.minigame;

import java.io.IOException;

import wazzle.controller.common.FileController;
import wazzle.controller.common.FileControllerImpl;
import wazzle.controller.common.WazzleController;

public class WordsDispenserImpl implements WordsDispenser {

	@Override
	public String getSuitableWord() throws IOException {
		FileController fileController = new FileControllerImpl();
		Comparator comparator = new ComparatorImpl(
				fileController.getDataset("dataset.txt")); /* gli passo il dataset preso da file (JSON) */
		if (comparator.isAvailableWordsEmpty()) {
			comparator.refreshDataSet(fileController.getDataset("dataset.txt"));
		}
		String suitableWord = comparator.getSuitableWord();
		comparator.removeFromSavedDataset(suitableWord);
		comparator.saveDataSet();/* gli passo il dataset da aggiornare (JSON) */
		return suitableWord;
	}
}
