package wazzle.controller.maingame;

import java.util.Optional;

import wazzle.model.maingame.MainGame;

public final class MainGameControllerImpl implements MainGameController {
	
	private Optional<MainGame> game = Optional.empty();
	
	@Override
	public Optional<MainGame> getGame() {
		return this.game;
	}

	@Override
	public void startNewGame() {
		// from wazzle controller
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attempt(String word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useScoreBonus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useTimeBonus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useWordBonus() {
		// TODO Auto-generated method stub
		
	}
}
