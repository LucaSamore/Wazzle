package wazzle.model.minigame;

import java.util.Random;

public final class WordsDispenserImpl implements WordsDispenser {
	
	private static final Random RANDOM = new Random();
	private final ExtractedWordManager availableWordManager;
	
	public WordsDispenserImpl(final ExtractedWordManager availableWordManager) {
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
	}
}
