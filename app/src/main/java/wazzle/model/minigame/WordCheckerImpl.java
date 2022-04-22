package wazzle.model.minigame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordCheckerImpl implements WordChecker {

	private String targetWord;

	public WordCheckerImpl(final String word) {
		this.targetWord = word;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MiniGameWord computeAttemptResult(final String attempt) {
		MiniGameWord word = new MiniGameWordImpl();

		List<WordElement> result = new ArrayList<>();

		IntStream.range(0, attempt.length()).boxed().forEach(i -> {
			if (attempt.charAt(i) == this.targetWord.charAt(i)) {
				result.add(new WordElement(attempt.charAt(i), Result.CORRECT.getState()));
			} else if (this.targetWord.chars().boxed().collect(Collectors.toList()).contains(attempt.codePointAt(i))) {
				result.add(new WordElement(attempt.charAt(i), Result.CORRECT_WRONG_PLACE.getState()));
			} else {
				result.add(new WordElement(attempt.charAt(i), Result.WRONG.getState()));
			}
		});
		word.setCompositeWord(result);
		return word;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCorrectWord(final String guessedWord) {
		return guessedWord.equals(this.targetWord);
	}


	
}