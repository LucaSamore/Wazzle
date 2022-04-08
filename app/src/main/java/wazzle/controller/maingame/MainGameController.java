package wazzle.controller.maingame;

import java.util.Optional;

import wazzle.model.maingame.MainGame;

public interface MainGameController {
	Optional<MainGame> getGame();
	void startNewGame();
	void endGame();
	void saveGame();
	void attempt(final String word);
	void useScoreBonus();
	void useTimeBonus();
	void useWordBonus();
}
