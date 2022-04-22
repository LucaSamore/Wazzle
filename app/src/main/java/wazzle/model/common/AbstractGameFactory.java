package wazzle.model.common;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGame;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.WordsDispenser;

/**
 * Represents an abstract factory that is responsible of the creation of {@code MainGame} and {@code MiniGame} objects.
 * More formally, it provides a set of methods for creating different game objects.
 * 
 * @see wazzle.model.maingame.MainGame
 * @see wazzle.model.maingame.MiniGame
 */
public interface AbstractGameFactory {
	
	/**
	 * This method allows the user to create a concrete implementation of {@code MainGame}
	 * @param dataset a {@code Dictionary} object that represents what dataset the game is going to use
	 * @param gridShape a {@code Pair<Integer,Integer>} object that represents the shape of the grid
	 * @param difficulty the {@code Difficulty} selected for this game
	 * @return a {@code MainGame} describing the game newly created
	 * @see wazzle.model.common.Dictionary
	 * @see javafx.util.Pair
	 * @see wazzle.model.maingame.Difficulty
	 */
	MainGame createMainGame(final Dictionary dataset, final Pair<Integer,Integer> gridShape, final Difficulty difficulty);
	
	/**
	 * This method allows the user to create a concrete implementation of {@code MiniGame}
	 * @param wordsDispenser a {@code WordsDispenser} object
	 * @return a {@code MiniGame} describing the game newly created
	 * @see wazzle.model.minigame.WordsDispenser
	 */
	MiniGame createMiniGame(final WordsDispenser wordsDispenser);
}