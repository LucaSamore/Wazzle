package wazzle.controller.minigame;

import wazzle.controller.common.WazzleController;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGameImpl;
import wazzle.model.minigame.MiniGameWord;

public class MiniGameControllerImpl implements MiniGameController {

	private MiniGame currentMinigame;
	private WazzleController wazzleController;
	
	public MiniGameControllerImpl(WazzleController wazzleController){
		this.currentMinigame = new MiniGameImpl();	
		this.wazzleController = wazzleController;
//		if (wazzleController.getMiniGameSnapshot().isPresent()) {
//			this.loadMiniGame(wazzleController.getMiniGameSnapshot());			
//		}
	}
	
	@Override
	public void newMiniGame() {
	}



	@Override
	public void saveMiniGame() {
		 
		
	}
	
	private void loadMiniGame() {
//		this.currentMinigame.takeSnapshot(null, null, null, null); //TODO
//		this.currentMinigame.loadMiniGame();
	}
	
	@Override
	public boolean guessWord(String guessedWord) {
		return currentMinigame.isWordCorrect(guessedWord);
	}
	
	@Override
	public MiniGameWord computeDifferencies() {
		return currentMinigame.computeResult();
	}
	
}
