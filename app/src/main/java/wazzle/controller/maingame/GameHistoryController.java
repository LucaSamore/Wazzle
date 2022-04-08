package wazzle.controller.maingame;

import java.util.List;

import wazzle.model.maingame.MainGame;

public interface GameHistoryController {
	
	List<MainGame> getGameHistory();
	
	MainGame getBestGame();
	
	List<MainGame> getSortedByDataGameHistory();
	
	List<MainGame> getSortedByScoreGameHistory();

}
