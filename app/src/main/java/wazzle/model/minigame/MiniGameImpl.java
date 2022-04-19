package wazzle.model.minigame;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Optional;

public class MiniGameImpl implements MiniGame {

//	private int tentativesLeft;
	private String targetWord;
	private LinkedList<MiniGameWord> guessedWords;
	private LocalDateTime gameStarTimeDate;
	private WordChecker wordChecker;
	private AttemptImpl currentAttempt;
//	private Optional<SavedMiniGame> savedMiniGame = Optional.empty();

//	/**
//	 * nested class for the memento pattern (Snapshot/memento)
//	 */
//	class SavedMiniGame {
//		private final String savedTargetWord;
//		private final LinkedList<MiniGameWord> savedGuessedWords;
//		private final LocalDateTime savedGameStarTimeDate;
//		private final AttemptImpl savedCurrentAttempt;
//
//		private SavedMiniGame(String targetWord, LinkedList<MiniGameWord> guessedWords, LocalDateTime gameStarTimeDate,
//				AttemptImpl currentAttempt) {
//			this.savedGameStarTimeDate = gameStarTimeDate;
//			this.savedGuessedWords = guessedWords;
//			this.savedTargetWord = targetWord;
//			this.savedCurrentAttempt = currentAttempt;
//		}
//
//		private void restorePreviousGame() {
//			MiniGameImpl.this.gameStarTimeDate = this.savedGameStarTimeDate;
//			MiniGameImpl.this.guessedWords = this.savedGuessedWords;
//			MiniGameImpl.this.targetWord = this.savedTargetWord;
//			MiniGameImpl.this.currentAttempt = this.savedCurrentAttempt;
//		}
//	}
//	
//		//END OF INNER CLASS//
	
	public MiniGameImpl() {
		this.wordChecker = new WordCheckerImpl();
		this.guessedWords = new LinkedList<MiniGameWord>();
		try {
			this.targetWord = new WordsDispenserImpl().getSuitableWord();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(targetWord);
	}

//	@Override
//	public Optional<SavedMiniGame> takeSnapshot(String targetWord, LinkedList<MiniGameWord> guessedWords, LocalDateTime gameStarTimeDate,
//			AttemptImpl currentAttempt) {
//		return Optional.of(new SavedMiniGame(targetWord, guessedWords, gameStarTimeDate, currentAttempt));
//	}

	@Override
	public boolean loadMiniGame() {
//		if (savedMiniGame.isPresent()) {
//			this.savedMiniGame.ifPresent(SavedMiniGame::restorePreviousGame);
//			return true;
//		}
		return false;
	}

//	@Override
//	public MiniGameImpl createNewMiniGame() throws IOException {
////		this.targetWord = new WordsDispenserImpl().getSuitableWord();
////		this.gameStarTimeDate = LocalDateTime.now();
////		this.guessedWords = new LinkedList<MiniGameWord>();
////		this.savedMiniGame = Optional.empty();
//		return this;
//	}

	@Override
	public String getTargetWord() {
		return this.targetWord;
	}

	@Override
	public boolean isWordCorrect(String guessedWord) {
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
		return guessedWords.getLast();
	}

}
