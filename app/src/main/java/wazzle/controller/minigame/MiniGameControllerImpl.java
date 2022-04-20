package wazzle.controller.minigame;

import java.io.IOException;

import wazzle.controller.common.WazzleController;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.MiniGameImpl;
import wazzle.model.minigame.MiniGameWord;

public class MiniGameControllerImpl implements MiniGameController {

	private MiniGame currentMinigame;
	private WazzleController wazzleController;
	
	public MiniGameControllerImpl(WazzleController wazzleController) throws IOException{
		this.currentMinigame = new MiniGameImpl();	
		this.wazzleController = wazzleController;
	}
	
	@Override
	public void newMiniGame() {
	}

	@Override
	public void saveMiniGame() {
	}
	
	private void loadMiniGame() {
	}
	
	@Override
	public void guessWord(String guessedWord) {
		if(currentMinigame.isWordCorrect(guessedWord)) {
			currentMinigame.setGameState(State.WON);
		} else if (currentMinigame.getCurrentAttemptsNumber() == currentMinigame.getMaxAttemptsNumber()) {
			currentMinigame.setGameState(State.FAILED);
		}
	}
	
	@Override
	public MiniGameWord computeDifferencies() {
		return currentMinigame.computeResult();
	}
	
	@Override
	public int getCurrentAttemptsNumber() {
		return currentMinigame.getCurrentAttemptsNumber();
	}

	@Override
	public String getTargetWord() {
		return currentMinigame.getTargetWord();
	}
	
	@Override
	public int getWordLenght() {
		return currentMinigame.getWordLenght();
	}
	
	@Override
	public int getMaxAttemptsNumber() {
		return currentMinigame.getMaxAttemptsNumber();
	}
	
	@Override
	public State getState() {
		return currentMinigame.getGameState();
	}
}
