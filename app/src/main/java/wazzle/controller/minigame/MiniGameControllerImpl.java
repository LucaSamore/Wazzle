package wazzle.controller.minigame;

import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGameImpl;

public class MiniGameControllerImpl implements MiniGameController {

	private MiniGame currentMinigame;
	
	@Override
	public void newMiniGame() {
		currentMinigame  = new MiniGameImpl();
		
	}

	@Override
	public void loadMiniGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMiniGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guessWord() {
		
		
	}
	
}
