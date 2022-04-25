package wazzle.model.maingame.letter.score;

import java.util.function.BinaryOperator;
import wazzle.model.maingame.alphabet.WeightedAlphabet;
import wazzle.model.maingame.alphabet.WeightedAlphabetImpl;

/**
 * This class is a concrete implementation for {@link ScoreConverter}. It provides a method for
 * converting the {@link WeightedAlphabet} into another {@link WeightedAlphabet} which contains the
 * score.
 */
public final class ScoreConverterImpl implements ScoreConverter {

  /** The maximum score which a Letter shall have. */
  private static final double MAX_SCORE = 6.0;

  private static final double MIN_SCORE = 1.0;
  private static final double ERROR = 0.1;
  private final WeightedAlphabet weightedAlphabet;
  private final ScoreDecorator adapter;
  private final BinaryOperator<Double> mapper =
      (value, max) -> MAX_SCORE - MAX_SCORE * value / max + MIN_SCORE + ERROR;

  /**
   * Construct a new ScoreConverter using a starting WeightedAlphabet and a ScoreAdapter which adapt
   * the object WeightedAlphabet.
   *
   * @param weightedAlphabet The WeightedAlphabet which have to be converted.
   */
  public ScoreConverterImpl(final WeightedAlphabet weightedAlphabet) {
    this.weightedAlphabet = weightedAlphabet;
    this.adapter = new ScoreDecorator(this.weightedAlphabet, this.mapper);
  }

  /** {@inheritDoc} */
  public WeightedAlphabet convert() {
    return new WeightedAlphabetImpl(this.adapter.getWeightedAlphabet());
  }
}
