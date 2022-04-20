package wazzle.model.minigame;

public interface WordChecker {

	MiniGameWord computeAttemptResult(String attempt);

	boolean isCorrectWord(String guessedWord);

}
