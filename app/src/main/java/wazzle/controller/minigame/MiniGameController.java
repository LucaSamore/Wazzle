package wazzle.controller.minigame;

import wazzle.model.minigame.MiniGameWord;

public interface MiniGameController {
	public void newMiniGame();

	public void saveMiniGame();

	public boolean guessWord(String guessedWord);

	MiniGameWord computeDifferencies();

	int getCurrentAttemptsNumber();

	public String getTargetWord();

	int getWordLenght();

	int getMaxAttemptsNumber();

}
