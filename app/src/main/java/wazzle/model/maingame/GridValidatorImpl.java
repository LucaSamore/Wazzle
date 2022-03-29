package wazzle.model.maingame;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import wazzle.model.common.Dictionary;

public final class GridValidatorImpl implements GridValidator{

	private final Dictionary dataset;
	private final Difficulty difficulty;
	private final Filters filters = (word, letters) -> Filters.checkLetters(word, letters) && 
													   Filters.checkPath(word, letters);
	
	public GridValidatorImpl(final Dictionary dataset, final Difficulty difficulty) {
		this.dataset = dataset;
		this.difficulty = difficulty;
	}
	
	
	public Dictionary getDataset() {
		return this.dataset;
	}


	public Difficulty getDifficulty() {
		return this.difficulty;
	}


	@Override
	public Optional<Set<String>> validate(Set<Letter> letters) {
		return this.rangify(this.dataset.getWords()
				.stream()
				.filter(w -> this.filters.applyAll(w, letters))
				.collect(Collectors.toSet()));
	}
	
	//TODO: implement this method
	private Optional<Set<String>> rangify(final Set<String> words){
		return Optional.empty();
	}

}
