package wazzle.model.common;

import java.util.function.BinaryOperator;

public class ScoreBonus extends AbstractBonus {

	private final BinaryOperator<Double> applier;
	
	public ScoreBonus(final BinaryOperator<Double> applier) {
		this.applier = applier;
	}
	
	public double apply(final double currentScore, final double gridTotalScore) {
		return this.applier.apply(currentScore, gridTotalScore);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applier == null) ? 0 : applier.hashCode());
		return result;
	}

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

	@Override
	public String toString() {
		return "ScoreBonus [applier=" + applier + "]";
	}

}
