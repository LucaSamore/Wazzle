package wazzle.model.maingame.grid;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javafx.util.Pair;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.Mediator;
import wazzle.model.maingame.letter.Letter;

/**
 * This class is an implementation for {@link wazzle.model.maingame.grid.GridGenerator}.
 * It uses a {@link wazzle.model.maingame.Mediator} object and a {@link wazzle.model.maingame.grid.GridValidator} object
 * internally for creating a new {@code Grid}. The mediator is used for retrieving a {@code Set<Letter>} that will be passed
 * to the validator. The validator checks if the set can form a grid of a certain quality. If not, the set of letters will be 
 * generated again, until we get a good quality one.
 * 
 * @see wazzle.model.maingame.Mediator
 * @see wazzle.model.maingame.grid.GridValidator
 * @see wazzle.model.maingame.letter.Letter
 */
public final class GridGeneratorImpl implements GridGenerator {
	
	private final Mediator mediator;
	private final GridValidator validator;
	
	/**
	 * Construct a new GridGeneratorImpl object
	 * @param dataset a {@code Dictionary} object used for this grid.
	 * @param gridShape a {@code Pair<Integer,Integer>} representing the shape of this grid.
	 * @param difficulty a {@code Difficulty} object chosen for this grid.
	 * 
	 * @see wazzle.model.common.Dictionary
	 * @see javafx.util.Pair
	 * @see wazzle.model.maingame.grid.Difficulty
	 */
	public GridGeneratorImpl(final Dictionary dataset, final Pair<Integer,Integer> gridShape, final Difficulty difficulty) {
		Objects.requireNonNull(dataset);
		Objects.requireNonNull(gridShape);
		Objects.requireNonNull(difficulty);
		this.validator = new GridValidatorImpl(dataset, difficulty);
		this.mediator = new Mediator(dataset, gridShape);
	}
	
	/**
	 * {@inheritDoc}
	 */
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