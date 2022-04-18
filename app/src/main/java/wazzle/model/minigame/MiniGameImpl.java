package wazzle.model.minigame;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import wazzle.controller.common.WazzleController;
import wazzle.controller.common.WazzleControllerImpl;

public class MiniGameImpl implements MiniGame {

//	private int tentativesLeft;
	private String targetWord;
	private List<MiniGameWord> guessedWords;
	private LocalDateTime gameStarTimeDate;
	private WordChecker wordChecker;

	public MiniGameImpl() {
		this.wordChecker = new WordCheckerImpl();
	}

	@Override
	public void saveMiniGame() {
		// TODO crea il memento
	}

	@Override
	public void loadMiniGame() {
		// TODO setta questa classe uguale al memento
	}

	@Override
	public MiniGameImpl createNewMiniGame() throws IOException {
		this.targetWord = new WordsDispenserImpl().getSuitableWord();
		this.gameStarTimeDate = LocalDateTime.now();
		return this;
	}

	@Override
	public String getTargetWord() {
		return this.targetWord;
	}

	@Override
	public boolean isWordCorrect(String guessedWord) {
		return this.targetWord.equals(guessedWord);
	}

	private void addGuessedWord(MiniGameWord wrongWord) {
		this.guessedWords.add(wrongWord);
	}
	
//
//	@Override
//	public List<MiniGameWordAttempt> getAllGuessedWords() {
//		return this.guessedWords;
//	}

	@Override
	public LocalDateTime getGameStarTimeDate() {
		return gameStarTimeDate;
	}

//	@Override
//	public MiniGameWordAttempt getLastGuessedWord() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public MiniGameWord guess(String guessedWord) {
		Attempt attempt= new AttemptImpl(guessedWord, this.targetWord);
		return this.wordChecker.computeAttemptResult(attempt);
	}

}
