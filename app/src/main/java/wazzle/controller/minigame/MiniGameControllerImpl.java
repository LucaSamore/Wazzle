package wazzle.controller.minigame;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import wazzle.controller.common.WazzleController;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.MiniGameImpl;
import wazzle.model.minigame.MiniGameWord;
import wazzle.model.minigame.WordCheckerImpl;
import wazzle.model.minigame.WordsDispenserImpl;

public class MiniGameControllerImpl implements MiniGameController {

	private Optional<MiniGame> currentMinigame;
	private WazzleController wazzleController;

	public MiniGameControllerImpl(final WazzleController wazzleController) {
		this.currentMinigame = Optional.empty();
		this.wazzleController = wazzleController;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startGame() throws IOException {
		if (this.loadMiniGame().isEmpty()) {
			this.currentMinigame = Optional.of(this.newMiniGame());
		} else {
			final var loadedMinigame = this.loadMiniGame();
			loadedMinigame.get().setWordChecker(new WordCheckerImpl(loadedMinigame.get().getTargetWord()));
			loadedMinigame.get().setGameState(State.IN_PROGRESS);
			this.currentMinigame = Optional.of(loadedMinigame.get());
			this.wazzleController.deleteEndedMiniGame();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveMiniGame() throws IOException {
		this.wazzleController.saveMiniGame(currentMinigame.get());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MiniGameWord guessWord(String guessedWord) {
		return this.currentMinigame.get().computeResult(guessedWord);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<String> obtainedBonus() throws IOException {
		if (this.currentMinigame.get().getGameState() == State.WON) {
			var currentBonusName = this.wazzleController.gainBonus();
			this.wazzleController.saveBonuses();
			return Optional.of(currentBonusName);
		}
		return Optional.empty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCurrentAttemptsNumber() {
		return currentMinigame.get().getCurrentAttemptsNumber();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MiniGameWord> getGuessedMinigameWordsSoFar() {
		return List.copyOf(currentMinigame.get().getAllGuessedWords());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWordLength() {
		return currentMinigame.get().getWordLength();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxAttemptsNumber() {
		return currentMinigame.get().getMaxAttemptsNumber();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public State getState() {
		return currentMinigame.get().getGameState();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTargetWord() {
		return this.currentMinigame.get().getTargetWord();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public WazzleController getMainController() {
		return this.wazzleController;
	}
	
	/**
	 * Starts a new minigame.
	 */
	private MiniGame newMiniGame() {
		return this.wazzleController.getFacade().startNewMiniGame(new WordsDispenserImpl(this.wazzleController.getExtractedWordManager()));
	}
	
	
	/**
	 * Loads a minigame.
	 */
	private Optional<MiniGameImpl> loadMiniGame() throws IOException {
		return this.wazzleController.getLastMinigame();
	}

}
