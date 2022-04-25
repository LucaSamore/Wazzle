package wazzle.model.maingame.grid;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.letter.Letter;

/**
 * This class is an implementation for {@link wazzle.model.maingame.grid.GridValidator}. It uses
 * {@link wazzle.model.maingame.grid.Filters} in order to perform a quality check on the provided
 * {@code Set<Letter>}.
 *
 * @see wazzle.model.maingame.grid.Filters
 * @see wazzle.model.maingame.letter.Letter
 */
public final class GridValidatorImpl implements GridValidator {

  private final Dictionary dataset;
  private final Difficulty difficulty;
  private final Filters filters =
      (word, letters) -> Filters.checkLetters(word, letters) && Filters.checkPath(word, letters);

  /**
   * Construct a new GridValidatorImpl object
   *
   * @param dataset a {@code Dictionary} object provided by {@code GridGeneratorImpl}.
   * @param difficulty a {@code Difficulty} object provided by {@code GridGeneratorImpl}.
   * @see wazzle.model.common.Dictionary
   * @see wazzle.model.maingame.grid.Difficulty
   */
  public GridValidatorImpl(final Dictionary dataset, final Difficulty difficulty) {
    Objects.requireNonNull(dataset);
    Objects.requireNonNull(difficulty);
    this.dataset = dataset;
    this.difficulty = difficulty;
  }

  /** {@inheritDoc} */
  @Override
  public Optional<Set<String>> validate(final Set<Letter> letters) {
    return this.qualityCheck(
        this.dataset.getWords().stream()
            .filter(w -> this.filters.applyFilters(w, letters))
            .collect(Collectors.toSet()));
  }

  /** {@inheritDoc} */
  @Override
  public Set<String> validateForTest(final Set<Letter> letters) {
    return this.dataset.getWords().stream()
        .filter(w -> this.filters.applyFilters(w, letters))
        .collect(Collectors.toSet());
  }

  private Optional<Set<String>> qualityCheck(final Set<String> words) {
    return Optional.of(words)
        .filter(
            w ->
                words.size() >= this.difficulty.getLowerBound()
                    && words.size() <= this.difficulty.getUpperBound());
  }
}
