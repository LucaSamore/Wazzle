package wazzle.controller.maingame;

import java.util.List;

import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

public interface GameHistoryController {
	
	List<MainGameImpl> getGameHistory();
	
	MainGame getBestGame();
	
	List<MainGameImpl> getSortedByDataGameHistory();
	
	List<MainGameImpl> getSortedByScoreGameHistory();

	void addNewGame(MainGameImpl maingame);
}