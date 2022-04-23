package wazzle.model.minigame;

import java.util.LinkedList;
import java.util.List;

public class MiniGameImpl implements MiniGame {

	private static final int MAX_ATTEMPTS_NUMBER = 6;
	private static final int WORDS_LENGHT = 5;

	
	private String targetWord;
	private List<MiniGameWordImpl> guessedWords;

	private WordChecker wordChecker;
	private State gameState;

	public MiniGameImpl(final String word) {
		this.guessedWords = new LinkedList<>();
		this.targetWord = word;
		this.wordChecker = new WordCheckerImpl(word);
		this.gameState = State.IN_PROGRESS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void loadMiniGame(final SavedMiniGame savedMinigame) {
		this.targetWord = savedMinigame.getSavedTargetWord();
		this.guessedWords = savedMinigame.getSavedMiniGameWordsSoFar();
		this.wordChecker = new WordCheckerImpl(this.getTargetWord());
		this.setGameState(State.IN_PROGRESS);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SavedMiniGame takeMiniGameSnapshot() {
		return new SavedMiniGame(this.targetWord, this.guessedWords);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTargetWord() {
		return this.targetWord;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MiniGameWord computeResult(final String guessedWord) {
		this.guessedWords.add((MiniGameWordImpl) this.wordChecker.computeAttemptResult(guessedWord));
		if (this.wordChecker.isCorrectWord(guessedWord)) {
			this.setGameState(State.WON);
		} else if (this.getCurrentAttemptsNumber() == this.getMaxAttemptsNumber()) {
			this.setGameState(State.FAILED);
		}
		return guessedWords.get(guessedWords.size() - 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWordLength() {
		return MiniGameImpl.WORDS_LENGHT;
	}

//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void setWordChecker(final WordChecker wordChecker) {
//		this.wordChecker = wordChecker;
//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setGameState(final State state) {
		this.gameState = state;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public State getGameState() {
		return this.gameState;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxAttemptsNumber() {
		return MiniGameImpl.MAX_ATTEMPTS_NUMBER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCurrentAttemptsNumber() {
		return guessedWords.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MiniGameWordImpl> getAllGuessedWords() {
		return List.copyOf(this.guessedWords);
	}
}
