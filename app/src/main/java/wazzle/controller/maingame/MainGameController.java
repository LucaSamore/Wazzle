package wazzle.controller.maingame;

import java.util.Optional;
import java.util.Set;

import wazzle.model.maingame.MainGame;

public interface MainGameController {
	Optional<MainGame> getGame();
	
	void startNewGame();
	
	void endGame();
	
	boolean attempt(final String word);
	
	boolean needHelp();
	
	boolean areWeDone();
	
	void useScoreBonus();
	
	void useTimeBonus();
	
	Set<String> useWordBonus();
}
