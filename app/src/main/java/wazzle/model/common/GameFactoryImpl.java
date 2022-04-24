package wazzle.model.common;

import javafx.util.Pair;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.maingame.grid.Difficulty;
import wazzle.model.maingame.grid.GridGeneratorImpl;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGameImpl;
import wazzle.model.minigame.word.WordsDispenser;


/**
 * This class is a concrete implementation of {@link AbstractGameFactory}
 */
public final class GameFactoryImpl implements AbstractGameFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MainGame createMainGame(final Dictionary dataset, final Pair<Integer, Integer> gridShape, final Difficulty difficulty) {
		final var gridGenerator = new GridGeneratorImpl(dataset, gridShape, difficulty);
		return new MainGameImpl(gridGenerator.generate(), difficulty.getTimeInMilliseconds());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MiniGame createMiniGame(final WordsDispenser wordsDispenser) {
		return new MiniGameImpl(wordsDispenser.extractWord());
	}
}
