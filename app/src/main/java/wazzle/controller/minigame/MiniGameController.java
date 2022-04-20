package wazzle.controller.minigame;

import java.io.IOException;

import wazzle.controller.common.WazzleController;
import wazzle.model.common.Dictionary;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.MiniGameWord;

public interface MiniGameController {

	void saveMiniGame() throws IOException;

	void guessWord(String guessedWord);

	int getCurrentAttemptsNumber();

	String getTargetWord();

	int getWordLenght();

	int getMaxAttemptsNumber();

	State getState();

	void startGame(Dictionary dictionary) throws IOException ;

	MiniGame newMiniGame(Dictionary dictionary);

	MiniGameWord computeDifferencies(String guessedWord);

	WazzleController getMainController();

}
