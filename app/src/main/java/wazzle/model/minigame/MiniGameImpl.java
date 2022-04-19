package wazzle.model.minigame;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.gson.annotations.Expose;

public class MiniGameImpl implements MiniGame {

//	private int tentativesLeft;
	@Expose
	private String targetWord;
	
	@Expose
	private LinkedList<MiniGameWord> guessedWords;
	
	@Expose
	private LocalDateTime gameStarTimeDate;
	
	private WordChecker wordChecker;
	private AttemptImpl currentAttempt;
	private int currentAttemptNumber;
	
	public MiniGameImpl() {
		this.wordChecker = new WordCheckerImpl();
		this.guessedWords = new LinkedList<MiniGameWord>();
		this.currentAttemptNumber = 0;
		try {
			this.targetWord = new WordsDispenserImpl().getSuitableWord();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public boolean isWordCorrect(String guessedWord) {
		currentAttemptNumber++;
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

	@Override
	public int getCurrentAttemptsNumber() {
		//guessedWords.size();
		return currentAttemptNumber;
	}

}
