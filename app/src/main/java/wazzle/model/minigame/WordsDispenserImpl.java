package wazzle.model.minigame;

import java.io.IOException;
import java.util.Random;

import wazzle.controller.common.FileController;
import wazzle.controller.common.FileControllerImpl;
import wazzle.controller.common.WazzleController;

public final class WordsDispenserImpl implements WordsDispenser {
	
	private static final Random RANDOM = new Random();

	private final Comparator availableWordManager;
	
	public WordsDispenserImpl(final Comparator availableWordManager) {
		this.availableWordManager = availableWordManager;
	}
	

	@Override
	public String extractWord() {

		if (!this.availableWordManager.areThereAnyAvailableWords()) {
			this.availableWordManager.restoreAvailableWords();
		}
		var extraction = this.availableWordManager.getAvailableWords().toArray()[RANDOM.nextInt(this.availableWordManager.getAvailableWords().size()-1)].toString();
		this.availableWordManager.notAvailableAnymore(extraction);
		return extraction;

//		this.availableWordManager.getAvailableWords().stream().findAny().ifPresentOrElse(w -> word = w , () -> this.availableWordManager.restoreAvailableWords());
//		return this.availableWords.toArray()[RANDOM.nextInt(availableWords.size())].toString();
		
//		FileController fileController = new FileControllerImpl();
//		
//		Comparator comparator = new ComparatorImpl(fileController.getDataset("minigameDataset.txt"));
//		/**
//		 * check if the dataset containing all the words is empty, and in case it does, update it.
//		 * 
//		 */
//		if (comparator.isDatasetEmpty()) {
//			comparator.refreshDataSet(new FiveLetterDictionary(fileController.getDataset("dataset.txt")));
//		}
//		
//		String suitableWord = comparator.getSuitableWord();
//		comparator.removeFromSavedDataset(suitableWord);
//		comparator.saveDataSet();/* gli passo il dataset da aggiornare (JSON) */
//		return suitableWord;
	}
}
