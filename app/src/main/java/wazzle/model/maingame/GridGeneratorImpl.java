package wazzle.model.maingame;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javafx.util.Pair;
import wazzle.model.common.Dictionary;

public final class GridGeneratorImpl implements GridGenerator {
	
	private final Mediator mediator;
	private final GridValidator validator;
	
	public GridGeneratorImpl(final Dictionary dataset, final Pair<Integer,Integer> gridShape, final Difficulty difficulty) {
		Objects.requireNonNull(dataset);
		Objects.requireNonNull(gridShape);
		Objects.requireNonNull(difficulty);
		this.validator = new GridValidatorImpl(dataset, difficulty);
		this.mediator = new Mediator(dataset, gridShape);
	}
	
	@Override
	public Grid generate() {
		Optional<Pair<Set<Letter>,Set<String>>> newGrid = Optional.empty();
		
		while (newGrid.isEmpty()) {
			newGrid = this.tryGenerate(() -> this.mediator.computeLetters(), words -> words.isPresent() && !words.get().isEmpty());
		}
		
		return new GridImpl(newGrid.get().getKey(), newGrid.get().getValue());
	}
	
	private Optional<Pair<Set<Letter>,Set<String>>> tryGenerate(final Supplier<Optional<Set<Letter>>> getComputedLetters, final Predicate<Optional<Set<String>>> wordsAreValid) {
		final var letters = getComputedLetters.get();
		final var validationResult = this.validator.validate(letters.isPresent() ? letters.get() : Collections.emptySet());
		
		if (wordsAreValid.test(validationResult)) {
			return Optional.of(new Pair<>(letters.get(), validationResult.get()));
		}
		
		return Optional.empty();
	}

}
