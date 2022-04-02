package wazzle.model.common;

import java.util.Set;
import java.util.function.UnaryOperator;

public class WordBonus extends AbstractBonus {
	
	private final UnaryOperator<Set<String>> applier;
	
	/**
	 * Construct a new WordBonus.
	 * 
	 * @param applier the operation that must be done when the WordBonus is used by the user.
	 */
	public WordBonus(final UnaryOperator<Set<String>> applier) {
		this.applier = applier;
	}
	
	/**
	 * Apply the implications of the WordBonus.
	 * 
	 * @param toFoundWords the words that the user hasn't already found.
	 * @return Set<String> the extracted words that will be suggested to the user.
	 */
	public Set<String> apply(final Set<String> toFoundWords) {
		return this.applier.apply(toFoundWords);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordBonus other = (WordBonus) obj;
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
		return "WordBonus [applier=" + applier + "]";
	}

}
