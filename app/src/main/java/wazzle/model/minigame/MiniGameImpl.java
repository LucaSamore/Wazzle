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

	@Expose
	private LocalDateTime gameStarTimeDate;

	@Expose
	private int nuberOfAttemptsSoFar;
	
	private WordChecker wordChecker;
	private AttemptImpl currentAttempt;
	private State gameState;

	public MiniGameImpl() throws IOException {
		this.wordChecker = new WordCheckerImpl();
		this.guessedWords = new LinkedList<MiniGameWord>();
		this.targetWord = new WordsDispenserImpl().getSuitableWord();
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
		this.nuberOfAttemptsSoFar++;
		this.currentAttempt = new AttemptImpl(this.getTargetWord(), guessedWord);
		return this.wordChecker.isCorrectWord(this.currentAttempt);
	}

	private void addGuessedWord(MiniGameWord wrongWord) {
		this.guessedWords.add(wrongWord);
	}

	@Override
	public LocalDateTime getGameStarTimeDate() {
		return gameStarTimeDate;
	}

	@Override
	public MiniGameWord computeResult() {
		this.addGuessedWord(this.wordChecker.computeAttemptResult(this.currentAttempt));
		return guessedWords.get(guessedWords.size() - 1);
	}

	@Override
	public int getCurrentAttemptsNumber() {
		return this.nuberOfAttemptsSoFar;
	}

	@Override
	public void setGameState(State state) {
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
}
