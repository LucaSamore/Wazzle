package wazzle.model.minigame;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class MiniGameImpl implements MiniGame {

	private static final int MAX_ATTEMPTS_NUMBER = 6;
	private static final int WORDS_LENGHT = 5;

	@Expose
	private String targetWord;

	@Expose
	private List<MiniGameWord> guessedWords;

	private WordChecker wordChecker;
	private State gameState;

	public MiniGameImpl(final String word) {
		this.guessedWords = new LinkedList<MiniGameWord>();
		this.targetWord = word;
		this.wordChecker = new WordCheckerImpl(word);
		this.gameState = State.IN_PROGRESS;
		System.out.println(targetWord);
	}

	@Override
	public boolean loadMiniGame() {
		return false;
	}

	@Override
	public String getTargetWord() {
		return this.targetWord;
	}

	@Override
	public int getWordLenght() {
		return MiniGameImpl.WORDS_LENGHT;
	}

	@Override
	public boolean isWordCorrect(String guessedWord) {
		return this.wordChecker.isCorrectWord(guessedWord);
	}

	private void addGuessedWord(MiniGameWord word) {
		this.guessedWords.add(word);
	}

	@Override
	public MiniGameWord computeResult(final String guessedWord) {
		this.addGuessedWord(this.wordChecker.computeAttemptResult(guessedWord));
		return guessedWords.get(guessedWords.size() - 1);
	}

	@Override
	public void setGameState(final State state) {
		this.gameState = state;
	}

	@Override
	public State getGameState() {
		return this.gameState;
	}

	@Override
	public int getMaxAttemptsNumber() {
		return MiniGameImpl.MAX_ATTEMPTS_NUMBER;
	}

	public void setWordChecker(final WordChecker wordChecker) {
		this.wordChecker = wordChecker;
	}

	public int getCurrentAttemptsNumber() {
		return guessedWords.size();
	}
}
