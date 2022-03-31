package wazzle.model.maingame;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import wazzle.model.common.Dictionary;

public final class GridValidatorImpl implements GridValidator{

	private final Dictionary dataset;
	private final Difficulty difficulty;
	private final Filters filters = (word, letters) -> Filters.checkLetters(word, letters) && Filters.checkPath(word, letters);
	
	public GridValidatorImpl(final Dictionary dataset, final Difficulty difficulty) {
		this.dataset = dataset;
		this.difficulty = difficulty;
	}

	@Override
	public Optional<Set<String>> validate(final Set<Letter> letters) {
		return this.rangify(this.dataset.getWords()
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
	
	//TODO: implement this method
	private Optional<Set<String>> rangify(final Set<String> words){
		return Optional.empty();
	}

}
