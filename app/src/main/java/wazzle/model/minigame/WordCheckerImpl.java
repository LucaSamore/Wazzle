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

		List<Pair<Character, Result>> result = new ArrayList<>();

		IntStream.range(0, attempt.getTargetWord().length()).boxed().forEach(i -> {
			if (attempt.getGuessedWord().charAt(i) == attempt.getTargetWord().charAt(i)) {
				result.add(new Pair<>(attempt.getGuessedWord().charAt(i), Result.CORRECT));
			} else if (attempt.getTargetWord().chars().boxed().collect(Collectors.toList())
					.contains(attempt.getGuessedWord().codePointAt(i))) {
				result.add(new Pair<>(attempt.getGuessedWord().charAt(i), Result.CORRECT_WRONG_PLACE));
			} else {
				result.add(new Pair<>(attempt.getGuessedWord().charAt(i), Result.WRONG));
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