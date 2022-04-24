package wazzle.controller.common;

import javafx.util.Pair;
import wazzle.model.common.AbstractGameFactory;
import wazzle.model.common.Dictionary;
import wazzle.model.common.GameFactoryImpl;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.grid.Difficulty;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.word.WordsDispenser;

/**
 * The purpose of this class is to group all games this application supports.
 * It contains an {@link AbstractGameFactory} in order to create instances of these games. 
 * 
 * @see MainGame
 * @see MiniGame
 */
public final class Facade {
	private final AbstractGameFactory gameFactory = new GameFactoryImpl();

	/**
	 * This method allows the user to retrieve a {@code MainGame} object.
	 * @param dataset a {@code Dictionary} object that represents what dataset the game is going to use.
	 * @param gridShape a {@code Pair<Integer,Integer>} object that represents the shape of the grid.
	 * @param difficulty the {@code Difficulty} selected for this game.
	 * @return a {@code MainGame} describing the game newly created
	 * @see wazzle.model.common.Dictionary
	 * @see javafx.util.Pair
	 * @see wazzle.model.maingame.grid.Difficulty
	 */
	public MainGame startNewMainGame(final Dictionary dataset, final Pair<Integer,Integer> gridShape, final Difficulty difficulty) {
		return this.gameFactory.createMainGame(dataset, gridShape, difficulty);
	}

	/**
	 * This method allows the user to retrieve a {@code MiniGame} object.
	 * @param wordsDispenser a {@code WordsDispenser} object.
	 * @return a {@code MiniGame} describing the game newly created
	 * @see wazzle.model.minigame.word.WordsDispenser
	 */
	public MiniGame startNewMiniGame(final WordsDispenser wordsDispenser) {
		return this.gameFactory.createMiniGame(wordsDispenser);
	}
}
