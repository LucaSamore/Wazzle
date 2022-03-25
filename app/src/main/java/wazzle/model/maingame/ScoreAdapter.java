package wazzle.model.maingame;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public final class ScoreAdapter implements WeightedAlphabet {
	
	private final WeightedAlphabet adaptee;
	private final BinaryOperator<Double> mapper;
	
	public ScoreAdapter(final WeightedAlphabet weightedAlphabet, final BinaryOperator<Double> mapper) {
		this.adaptee = weightedAlphabet;
		this.mapper = mapper;
	}
	
	@Override
	public Map<Character, Double> getWeightedAlphabet() {
		return this.getUpdatedWeightedAlphabet(this.getMinimumWeight()).entrySet()
								  .stream()
								  .collect(Collectors.toMap(Map.Entry::getKey, 
										  	e -> this.mapper.apply(e.getValue(), this.getUpdatedMaxWeight()))
										  );
	}
	
	private double getMinimumWeight () {
		return this.adaptee
				   .getWeightedAlphabet()
				   .values()
				   .stream()
				   .min(Comparator.comparing(Double::valueOf))
				   .orElse(Double.NaN);
	}
	
	private Map<Character, Double> getUpdatedWeightedAlphabet(final double min) {
		Map<Character, Double> weightedAlphabetMap = new HashMap<>(this.adaptee.getWeightedAlphabet());
		return weightedAlphabetMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() - this.getMinimumWeight()));
	}
	
	private double getUpdatedMaxWeight() {
		return this.getUpdatedWeightedAlphabet(this.getMinimumWeight())
				   .values()
				   .stream()
				   .max(Comparator.comparing(Double::valueOf))
				   .orElse(Double.NaN);
	}

}
