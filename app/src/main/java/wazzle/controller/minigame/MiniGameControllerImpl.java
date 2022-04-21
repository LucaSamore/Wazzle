package wazzle.controller.minigame;

import java.io.IOException;
import java.util.Optional;

import wazzle.controller.common.WazzleController;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.FiveLetterDictionary;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.MiniGameImpl;
import wazzle.model.minigame.MiniGameWord;
import wazzle.model.minigame.WordChecker;
import wazzle.model.minigame.WordCheckerImpl;

public class MiniGameControllerImpl implements MiniGameController {


	private Optional<MiniGame> currentMinigame;
	private WazzleController wazzleController;
	
	public MiniGameControllerImpl(final WazzleController wazzleController) throws IOException{
		this.currentMinigame = Optional.empty();	
		this.wazzleController = wazzleController;
	}
	
	
	
	@Override
	public void startGame(final Dictionary dictionary) throws IOException {
		if(this.loadMiniGame().isEmpty()) {
			this.currentMinigame = Optional.of(this.newMiniGame(dictionary));
		}else {
			final var loadedMinigame = this.loadMiniGame();
			loadedMinigame.get().setGameState(State.IN_PROGRESS);
			loadedMinigame.get().setWordChecker(new WordCheckerImpl(loadedMinigame.get().getTargetWord()));
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
	public void guessWord(String guessedWord) {
		if(currentMinigame.get().isWordCorrect(guessedWord)) {
			currentMinigame.get().setGameState(State.WON);
		} else if (currentMinigame.get().getCurrentAttemptsNumber() == currentMinigame.get().getMaxAttemptsNumber()) {
			currentMinigame.get().setGameState(State.FAILED);
		}
	}	
	
	@Override
	public MiniGameWord computeDifferencies(final String guessedWord) {
		return currentMinigame.get().computeResult(guessedWord);
	}
	
	@Override
	public int getCurrentAttemptsNumber() {
		return currentMinigame.get().getCurrentAttemptsNumber();
	}

	@Override
	public String getTargetWord() {
		return currentMinigame.get().getTargetWord();
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
	public WazzleController getMainController() {
		return this.wazzleController;
	}
}
