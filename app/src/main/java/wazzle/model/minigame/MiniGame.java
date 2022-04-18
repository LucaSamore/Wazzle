package wazzle.model.minigame;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javafx.util.Pair;

public interface MiniGame {

	public void saveMiniGame();

	public void loadMiniGame();
	
//	public MiniGameWordAttempt getLastGuessedWord();

//	public List<MiniGameWordAttempt> getAllGuessedWords();

	public LocalDateTime getGameStarTimeDate();

	public String getTargetWord();

	public MiniGame createNewMiniGame() throws IOException;

	public boolean isWordCorrect(String guessedWord);

	public MiniGameWord guess(String guessedWord);
}
