package wazzle.model.common;

import java.util.function.UnaryOperator;

public class TimeBonus extends AbstractBonus {

	private final UnaryOperator<Long> applier;

	/**
	 * Construct a new {@link TimeBonus}.
	 * 
	 * @param applier		The operation that must be done when the {@link TimeBonus} is used by the user.
	 */
	public TimeBonus(final UnaryOperator<Long> applier, String name) {
		this.applier = applier;
		this.name = name;
	}
	
	/**
	 * Apply the implications of the {@link TimeBonus}.
	 * 
	 * @param currentTime		The current time remaining to the user to found other words.
	 * @return long which represent the incremented time.
	 */
	public long apply(final long currentTime) {
		return this.applier.apply(currentTime);
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
		TimeBonus other = (TimeBonus) obj;
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
		return "TimeBonus [applier=" + applier + "]";
	}

	
}
