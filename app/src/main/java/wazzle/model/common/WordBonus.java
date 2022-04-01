package wazzle.model.common;

import java.util.Set;
import java.util.function.UnaryOperator;

public class WordBonus extends AbstractBonus {
	
	private final UnaryOperator<Set<String>> applier;
	
	public WordBonus(final UnaryOperator<Set<String>> applier) {
		this.applier = applier;
	}
	
	public Set<String> apply(final Set<String> toFoundWords) {
		return this.applier.apply(toFoundWords);
	}

}
