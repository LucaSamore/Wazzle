package wazzle.model.common;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.Grid;
import wazzle.model.maingame.GridGeneratorImpl;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.ComparatorImpl;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGameImpl;
import wazzle.model.minigame.WordsDispenserImpl;

public class GameFactoryImpl implements AbstractGameFactory {
	
	@Override
	public MainGame createMainGame(final Dictionary dataset, final Pair<Integer, Integer> gridShape, final Difficulty difficulty) {
		var gridGenerator = new GridGeneratorImpl(dataset, gridShape, difficulty);
		return new MainGameImpl(gridGenerator.generate(), difficulty.getTimeInMilliseconds());
	}
	
	@Override
	public MiniGame createMiniGame(final Dictionary dataset) {
		var wordsDispenser = new WordsDispenserImpl(new ComparatorImpl(dataset));
		return new MiniGameImpl(wordsDispenser.extractWord());
	}
}
