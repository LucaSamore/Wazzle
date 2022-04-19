package wazzle.model.minigame;

import java.time.LocalDateTime;


public interface MiniGame {

	public boolean loadMiniGame();

	public LocalDateTime getGameStarTimeDate();

	public String getTargetWord();

//	public MiniGame createNewMiniGame() throws IOException;

	public boolean isWordCorrect(String guessedWord);

	public MiniGameWord computeResult();

	public int getCurrentAttemptsNumber();

//	Optional<SavedMiniGame> takeSnapshot(String targetWord, LinkedList<MiniGameWord> guessedWords, LocalDateTime gameStarTimeDate,
//			AttemptImpl currentAttempt);
}
