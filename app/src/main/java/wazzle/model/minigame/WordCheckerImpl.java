package wazzle.model.minigame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class WordCheckerImpl implements WordChecker {

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
		word.setCompositeWord(this.replaceWrongStates(
				this.getToReplaceStateLetters(this.getCorrectLetterOccurences(result), 
				this.getLetterOccurrences()), result));
		return word;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCorrectWord(final String guessedWord) {
		return guessedWord.equals(this.targetWord);
	}

	// faccio una mappa con quante lettere ci sono per ogni carattere
	private Map<Character, Integer> getLetterOccurrences() {
		final var letterOccurences = new HashMap<Character, Integer>();
		this.targetWord.chars()
		.mapToObj(c -> (char)c)
		.forEach(c1 -> letterOccurences.putIfAbsent(c1, (int) this.targetWord.chars()
				.mapToObj(c2 -> (char)c2)
				.filter(c3 -> Objects.equals(c3, c1))
				.count()));
		return letterOccurences;
	}
	
	// faccio una mappa con le lettere e quante ce ne sono giuste nel posto sbagliato
	private Map<Character, Integer> getCorrectLetterOccurences(List<WordElement> result) {
		return result.stream()
				.filter(w -> w.getResult() == Result.CORRECT.getState() 
				|| w.getResult() == Result.CORRECT_WRONG_PLACE.getState())
				.collect(Collectors.groupingBy(w -> w.getCharacter()))
				.entrySet()
				.stream()
				.collect(Collectors.toMap(Entry::getKey, c -> c.getValue().size()));
	}
	
	//faccio una mappa che contiene le lettere superflue e quante ce ne sono di superflue
	private Map<Character, Integer> getToReplaceStateLetters(Map<Character, Integer> correctLetterOccurrences,
			Map<Character, Integer> letterOccurrences) {
		return correctLetterOccurrences.entrySet()
				.stream()
				.filter(e -> e.getValue() > letterOccurrences.get(e.getKey()))
				.collect(Collectors.toMap(Entry::getKey, c -> c.getValue() - letterOccurrences.get(c.getKey())));
	}
	
	//correggo quelle errate
	private List<WordElement> replaceWrongStates(Map<Character, Integer> toReplaceStateLetters, List<WordElement> result) {
		toReplaceStateLetters.entrySet()
			.forEach(e -> {IntStream.range(0, toReplaceStateLetters.get(e.getKey()))
				.boxed()
				.forEach(i -> {
					result.remove(new WordElement(e.getKey(), Result.CORRECT_WRONG_PLACE.getState()));
					result.add(new WordElement(e.getKey(), Result.WRONG.getState()));
					});
			});
		return result;
	}
	
}