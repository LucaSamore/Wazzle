package wazzle.controller.minigame;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import wazzle.controller.common.WazzleController;
import wazzle.model.common.Dictionary;
import wazzle.model.minigame.FiveLetterDictionary;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.MiniGameImpl;
import wazzle.model.minigame.MiniGameWord;

public class MiniGameControllerImpl implements MiniGameController {

	private Optional<MiniGame> currentMinigame;
	private WazzleController wazzleController;

	public MiniGameControllerImpl(final WazzleController wazzleController) {
		this.currentMinigame = Optional.empty();
		this.wazzleController = wazzleController;
	}

	@Override
	public void startGame() throws IOException {

		if (this.loadMiniGame().isEmpty()) {
			this.currentMinigame = Optional.of(this.newMiniGame(this.wazzleController.getDataset()));
		} else {
			final var loadedMinigame = this.loadMiniGame();
			this.currentMinigame = Optional.of(loadedMinigame.get());
		}
	}

	@Override
	public MiniGame newMiniGame(final Dictionary dictionary) {
		return this.wazzleController.getFacade().startNewMiniGame(new FiveLetterDictionary(dictionary));
	}

	@Override
	public void saveMiniGame() throws IOException {
		this.wazzleController.saveMiniGame(currentMinigame.get());
	}

	private Optional<MiniGameImpl> loadMiniGame() throws IOException {
		return this.wazzleController.getLastMinigame();
	}

	@Override
	public MiniGameWord guessWord(String guessedWord) {
		return this.currentMinigame.get().computeResult(guessedWord);
	}

	@Override
	public String obtainedBonus() {
		if (this.currentMinigame.get().getGameState() == State.WON) {
			this.wazzleController.gainBonus();
			return "Bonus Ottenuto";
		}
		return "";
	}
	
	@Override
	public int getCurrentAttemptsNumber() {
		return currentMinigame.get().getCurrentAttemptsNumber();
	}

	@Override
	public List<MiniGameWord> getGuessedMiniGameWordsSoFar() {
		return List.copyOf(currentMinigame.get().getAllGuessedWords());
	}
	@Override
	public int getWordLenght() {
		return currentMinigame.get().getWordLenght();
	}

	@Override
	public int getMaxAttemptsNumber() {
		return currentMinigame.get().getMaxAttemptsNumber();
	}

	@Override
	public State getState() {
		return currentMinigame.get().getGameState();
	}

	@Override
	public String getTargetWord() {
		return this.currentMinigame.get().getTargetWord();
	}
}
