package wazzle.model.common;

import java.util.function.BinaryOperator;

public class ScoreBonus extends AbstractBonus {

	private final BinaryOperator<Integer> applier;
	
	/**
	 * Construct a new ScoreBonus.
	 * 
	 * @param applier the operation that must be done when the ScoreBonus is used by the user.
	 */
	public ScoreBonus(final BinaryOperator<Integer> applier) {
		this.applier = applier;
	}
	
	/**
	 * Apply the implications of the ScoreBonus.
	 * 
	 * @param currentScore the current score obtained by the user.
	 * @param gridTotalScore the sum of all letter scores in the grid.
	 * @return long which represent the updated score.
	 */
	public int apply(final int currentScore, final int gridTotalScore) {
		return this.applier.apply(currentScore, gridTotalScore);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((applier == null) ? 0 : applier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
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

	@Override
	public String toString() {
		return "ScoreBonus [applier=" + applier + "]";
	}

}
