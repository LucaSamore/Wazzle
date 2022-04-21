package wazzle.model.minigame;

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
		this.setGameState(State.IN_PROGRESS);
		this.wordChecker = new WordCheckerImpl(this.getTargetWord());
		return false;
	}

	@Override
	public String getTargetWord() {
		return this.targetWord;
	}

	@Override
	public MiniGameWord computeResult(final String guessedWord) {
		this.guessedWords.add(this.wordChecker.computeAttemptResult(guessedWord));
		if (this.wordChecker.isCorrectWord(guessedWord)) {
			this.setGameState(State.WON);
		} else if (this.getCurrentAttemptsNumber() == this.getMaxAttemptsNumber()) {
			this.setGameState(State.FAILED);
		}
		return guessedWords.get(guessedWords.size() - 1);
	}
	
	@Override
	public int getWordLenght() {
		return MiniGameImpl.WORDS_LENGHT;
	}

	private void setGameState(final State state) {
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

	public int getCurrentAttemptsNumber() {
		return guessedWords.size();
	}
}
