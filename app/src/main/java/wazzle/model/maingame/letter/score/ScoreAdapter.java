package wazzle.model.maingame.letter.score;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import wazzle.model.maingame.alphabet.WeightedAlphabet;

/**
 * This class use the Design Pattern Adapter compute the conversion of the frequency, contained 
 * in a {@link WeightedAlphabet}, to a score.
 *
 */
public final class ScoreAdapter implements WeightedAlphabet {
	
	private final WeightedAlphabet adaptee;
	private final BinaryOperator<Double> mapper;
	
	/**
	 * Construct a new ScoreAdapter using a starting WeightedAlphabet and a function 
	 * 
	 * @param weightedAlphabet The WeightedAlphabet on which the score is computed.
	 * @param mapper The function which convert the frequency in score.
	 */
	public ScoreAdapter(final WeightedAlphabet weightedAlphabet, final BinaryOperator<Double> mapper) {
		this.adaptee = weightedAlphabet;
		this.mapper = mapper;
	}
	
	/**
	 * Compute the conversion of the WeightedAlphabet and returns it.
	 * 
	 * @return WeightedAphabet A WeightedAlphabet which contains all the Characters and their score.
	 */
	@Override
	public Map<Character, Double> getWeightedAlphabet() {
		return this.getUpdatedWeightedAlphabet(this.getMinimumWeight()).entrySet()
								  .stream()
								  .collect(Collectors.toMap(Map.Entry::getKey, 
										  	e -> this.mapper.apply(e.getValue(), this.getUpdatedMaxWeight()))
										  );
	}
	
	/**
	 * Compute the minimum weight contained in the starting WeightedAlphabet.
	 * 
	 * @return double which represents the minimum frequency in the starting WeightedAlphabet.
	 */
	private double getMinimumWeight () {
		return this.adaptee
				   .getWeightedAlphabet()
				   .values()
				   .stream()
				   .min(Comparator.comparing(Double::valueOf))
				   .orElse(Double.NaN);
	}
	
	/**
	 * Compute the conversion from the starting WeightedAlphabet, which contains the frequency,
	 * to an updated Map which contains the Characters and their frequency decremented by min.
	 * 
	 * @param min The minimum frequency of the WeightedAlphabet.
	 * @return Map<Character, Double> which contains the Characters and their frequency decremented by min.
	 */
	private Map<Character, Double> getUpdatedWeightedAlphabet(final double min) {
		Map<Character, Double> weightedAlphabetMap = new HashMap<>(this.adaptee.getWeightedAlphabet());
		return weightedAlphabetMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() - this.getMinimumWeight()));
	}
	
	/**
	 * Compute the maximum weight in the updated Map which represent the WeightedAlphabet.
	 * 
	 * @return double which represents the maximum weight in the updated Map.
	 */
	private double getUpdatedMaxWeight() {
		return this.getUpdatedWeightedAlphabet(this.getMinimumWeight())
				   .values()
				   .stream()
				   .max(Comparator.comparing(Double::valueOf))
				   .orElse(Double.NaN);
	}

}
