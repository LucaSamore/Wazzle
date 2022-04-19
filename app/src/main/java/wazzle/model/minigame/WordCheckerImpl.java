package wazzle.model.minigame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.util.Pair;

public class WordCheckerImpl implements WordChecker {

	public WordCheckerImpl() {
	}

	@Override
	public MiniGameWord computeAttemptResult(final Attempt attempt) {
		MiniGameWord word = new MiniGameWordImpl();

		List<WordElement> result = new ArrayList<>();

		IntStream.range(0, attempt.getTargetWord().length()).boxed().forEach(i -> {
			if (attempt.getGuessedWord().charAt(i) == attempt.getTargetWord().charAt(i)) {
				result.add(new WordElement(attempt.getGuessedWord().charAt(i), Result.CORRECT.getState()));
			} else if (attempt.getTargetWord().chars().boxed().collect(Collectors.toList())
					.contains(attempt.getGuessedWord().codePointAt(i))) {
				result.add(new WordElement(attempt.getGuessedWord().charAt(i), Result.CORRECT_WRONG_PLACE.getState()));
			} else {
				result.add(new WordElement(attempt.getGuessedWord().charAt(i), Result.WRONG.getState()));
			}
		});
		word.setCompositeWord(result);
		return word;
	}

	@Override
	public boolean isCorrectWord(Attempt attempt) {
		return attempt.getGuessedWord().equals(attempt.getTargetWord());
	}
}