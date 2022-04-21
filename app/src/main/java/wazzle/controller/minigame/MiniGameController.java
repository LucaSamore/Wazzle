package wazzle.controller.minigame;

import java.io.IOException;
import java.util.List;

import wazzle.model.common.Dictionary;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.MiniGameWord;

public interface MiniGameController {

	void saveMiniGame() throws IOException;
	
	void startGame() throws IOException ;

	MiniGameWord guessWord(String guessedWord);
	
	MiniGame newMiniGame(Dictionary dictionary);

	String obtainedBonus();
	
	String getTargetWord();
	
	int getCurrentAttemptsNumber();

	int getWordLenght();

	int getMaxAttemptsNumber();

	State getState();

	List<MiniGameWord> getGuessedMiniGameWordsSoFar();


}
