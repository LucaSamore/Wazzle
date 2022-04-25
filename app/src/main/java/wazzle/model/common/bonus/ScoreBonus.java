package wazzle.model.common.bonus;

import java.util.function.BinaryOperator;
import wazzle.model.common.BonusManager;

/**
 * This class is a concrete implementation for {@link BonusManager}. It provides methods for apply a
 * {@link ScoreBonus}.
 */
public final class ScoreBonus extends AbstractBonus {

  private final BinaryOperator<Integer> applier;

  /**
   * Construct a new {@link ScoreBonus}.
   *
   * @param applier the operation that must be done when the {@link ScoreBonus} is used by the user.
   */
  public ScoreBonus(final BinaryOperator<Integer> applier, String name) {
    this.applier = applier;
    this.name = name;
  }

  /**
   * Apply the implications of the {@link ScoreBonus}.
   *
   * @param currentScore The current score obtained by the user.
   * @param gridTotalScore The sum of all letter scores in the grid.
   * @return long which represent the updated score.
   */
  public int apply(final int currentScore, final int gridTotalScore) {
    return this.applier.apply(currentScore, gridTotalScore);
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((applier == null) ? 0 : applier.hashCode());
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    ScoreBonus other = (ScoreBonus) obj;
    if (applier == null) {
      if (other.applier != null) return false;
    } else if (!applier.equals(other.applier)) return false;
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "ScoreBonus [applier=" + applier + "]";
  }
}
