package wazzle.model.minigame;

public class AttemptImpl implements Attempt {

	private String targetWord;
	private String guessedWord;

	public AttemptImpl(String targetWord, String guessedWord) {
		this.targetWord = new String(targetWord);
		this.guessedWord = new String(guessedWord);
	}

	@Override
	public String getTargetWord() {
		return this.targetWord;
	}

	@Override
	public String getGuessedWord() {
		return this.guessedWord;
	}

}
