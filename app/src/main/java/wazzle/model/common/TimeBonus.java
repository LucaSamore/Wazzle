package wazzle.model.common;

import java.util.function.UnaryOperator;

public class TimeBonus extends AbstractBonus {

	private final UnaryOperator<Long> applier;

	public TimeBonus(final UnaryOperator<Long> applier) {
		this.applier = applier;
	}
	
	public long apply(final long currentTime) {
		return this.applier.apply(currentTime);
	}

}
