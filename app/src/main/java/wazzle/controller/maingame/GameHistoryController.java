package wazzle.controller.maingame;

import java.util.List;

import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

public interface GameHistoryController {
	
	/**
	 * Gives the game history.
	 * 
	 * @return List<MainGameImpl> 	The list of the last game history.
	 */
	List<MainGameImpl> getGameHistory();
	
	/**
	 * Gives the best game.
	 * 
	 * @return MainGame 	The best main game.
	 */
	MainGame getBestGame();
	
	/**
	 * Sort the game history by data.
	 */
	void sortGameHistoryByData();
	
	/**
	 * Gives the game history sorted by score.
	 * 
	 * @return List<MainGameImpl> 	The game history sorted by score.
	 */
	List<MainGameImpl> getSortedByScoreGameHistory();

	/**
	 * Add a game to game history.
	 * 
	 * @param MainGameImpl	 The main game which have to be inserted to history.
	 */
	void addNewGame(MainGameImpl maingame);
}
