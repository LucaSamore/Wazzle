package wazzle.model.maingame;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import wazzle.model.common.Dictionary;

public final class GridValidatorImpl implements GridValidator{

	private final Dictionary dataset;
	private final Difficulty difficulty;
	private final Filters filters = (word, letters) -> Filters.checkLetters(word, letters) && Filters.checkPath(word, letters);
	
	public GridValidatorImpl(final Dictionary dataset, final Difficulty difficulty) {
		Objects.requireNonNull(dataset);
		Objects.requireNonNull(difficulty);
		this.dataset = dataset;
		this.difficulty = difficulty;
	}

	@Override
	public Optional<Set<String>> validate(final Set<Letter> letters) {
		return this.inDifficultyRange(this.dataset.getWords()
				.stream()
				.filter(w -> this.filters.applyAll(w, letters))
				.collect(Collectors.toSet()));
	}
	
	@Override
	public Set<String> validateForTest(final Set<Letter> letters) {
		return this.dataset.getWords()
				.stream()
				.filter(w -> this.filters.applyAll(w, letters))
				.collect(Collectors.toSet());
	}
	
	private Optional<Set<String>> inDifficultyRange(final Set<String> words) {
		return Optional.of(words).filter(w -> words.size() >= this.difficulty.getLowerBound() && 
				words.size() <= this.difficulty.getUpperBound());
	}

}
