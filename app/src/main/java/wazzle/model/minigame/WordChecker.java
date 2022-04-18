package wazzle.model.minigame;

public interface WordChecker {
	

	public MiniGameWord computeAttemptResult(Attempt attempt);

	boolean isCorrectWord(Attempt attempt);

}
