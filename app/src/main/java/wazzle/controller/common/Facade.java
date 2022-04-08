package wazzle.controller.common;

import javafx.util.Pair;
import wazzle.model.common.AbstractGameFactory;
import wazzle.model.common.Dictionary;
import wazzle.model.common.GameFactoryImpl;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGame;

public final class Facade {
	private final AbstractGameFactory gameFactory = new GameFactoryImpl();

	public MainGame startNewMainGame(final Dictionary dataset, final Pair<Integer,Integer> gridShape, final Difficulty difficulty) {
		return this.gameFactory.createMainGame(dataset, gridShape, difficulty);
	}
	
//	public MiniGame startNewMiniGame() {
//		
//	}
}
