package wazzle.model.maingame.letter.score;

import java.util.Map;
import java.util.function.BinaryOperator;
import wazzle.model.maingame.alphabet.WeightedAlphabet;

/** This class provides an abstraction for {@link ScoreDecorator}. */
public abstract class AbstractScoreDecorator implements WeightedAlphabet {

  protected final WeightedAlphabet weightedAlphabet;
  protected final BinaryOperator<Double> mapper;

  /**
   * Construct a new ScoreAdapter using a starting WeightedAlphabet and a function
   *
   * @param weightedAlphabet The WeightedAlphabet on which the score is computed.
   * @param mapper The function which convert the frequency in score.
   */
  protected AbstractScoreDecorator(
      final WeightedAlphabet weightedAlphabet, final BinaryOperator<Double> mapper) {
    this.weightedAlphabet = weightedAlphabet;
    this.mapper = mapper;
  }

  /**
   * Gives the WeightedAlphabet.
   *
   * @return WeightedAphabet A WeightedAlphabet which contains all the Characters and their score.
   */
  @Override
  public Map<Character, Double> getWeightedAlphabet() {
    return this.weightedAlphabet.getWeightedAlphabet();
  }
}
