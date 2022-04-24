package wazzle.model.minigame;

import java.util.Random;


public final class WordsDispenserImpl implements WordsDispenser {

	private static final Random RANDOM = new Random();
	

	private final ExtractedWordManager availableWordManager;
	
	/**
	 * Instantiates a new words dispenser.
	 *
	 * @param availableWordManager the available word manager
	 */
	public WordsDispenserImpl(final ExtractedWordManager availableWordManager) {
		this.availableWordManager = availableWordManager;
	}
	


	@Override
	public String extractWord() {
		if (!this.availableWordManager.areThereAnyAvailableWords()) {
			this.availableWordManager.restoreAllAvailableWords();
		}
		var extraction = this.availableWordManager.getAvailableWords().toArray()[RANDOM.nextInt(this.availableWordManager.getAvailableWords().size())].toString();
		this.availableWordManager.removeFromAvailableWords(extraction);
		return extraction;
	}
}
