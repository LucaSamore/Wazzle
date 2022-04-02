package wazzle.model.common;

import java.util.Optional;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGame;

public final class Facade {
	private final AbstractGameFactory gameFactory;
	private Optional<MainGame> currentMainGame;
	
	public Facade() {
		this.gameFactory = new GameFactoryImpl();
		this.currentMainGame = Optional.empty();
	}
	
	public void startNewMainGame(final Dictionary dataset, final Pair<Integer,Integer> gridShape, final Difficulty difficulty) {
		this.currentMainGame = Optional.of(this.gameFactory.createMainGame(dataset, gridShape, difficulty));
	}
	
	public void endMainGame() {
		this.currentMainGame = Optional.empty();
	}
	
	public Optional<MainGame> getCurrentMainGame() {
		return this.currentMainGame;
	}
}
