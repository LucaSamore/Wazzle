package wazzle.model.common;

import java.util.function.BinaryOperator;

public class ScoreBonus extends AbstractBonus {

	private final BinaryOperator<Double> applier;
	
	/**
	 * Construct a new ScoreBonus.
	 * 
	 * @param applier the operation that must be done when the ScoreBonus is used by the user.
	 */
	public ScoreBonus(final BinaryOperator<Double> applier) {
		this.applier = applier;
	}
	
	/**
	 * Apply the implications of the ScoreBonus.
	 * 
	 * @param currentScore the current score obtained by the user.
	 * @param gridTotalScore the sum of all letter scores in the grid.
	 * @return long which represent the updated score.
	 */
	public double apply(final double currentScore, final double gridTotalScore) {
		return this.applier.apply(currentScore, gridTotalScore);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applier == null) ? 0 : applier.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreBonus other = (ScoreBonus) obj;
		if (applier == null) {
			if (other.applier != null)
				return false;
		} else if (!applier.equals(other.applier))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ScoreBonus [applier=" + applier + "]";
	}

}
