/**
*
*	This class is the implementation of Frequency Interface,
*	it counts all the letters and its number of 
*	occurrences in the given Dictionary, then it returns
*	an Object of class WeightedAlphabet with a Map storing
*	all the letters and their associated relative frequency.
*	
*  	@param dataset A Dictionary class object storing all the words.
*  
*/

package wazzle.model.maingame.alphabet;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import wazzle.model.common.Dictionary;

public final class FrequencyImpl implements Frequency {

	private Dictionary dataset;

	public FrequencyImpl(final Dictionary dataset) {
		this.dataset = dataset;
	}


	/**
	 * {@inheritDoc}
	 * 
	 */

	public WeightedAlphabet computeFrequency() {

		double numberOfLetters;
		Map<Character, Double> frequencyMap;

		frequencyMap = this.dataset.getWords().stream()
				.flatMap(k -> k.chars().mapToObj(v -> (char) v))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0D, e -> 1D, Double::sum)));

		numberOfLetters = frequencyMap.values().stream()
				.mapToDouble(Double::doubleValue)
				.sum();

		frequencyMap.entrySet().forEach(e -> e.setValue(e.getValue() / numberOfLetters));
		
		frequencyMap = Collections.unmodifiableMap(frequencyMap);
		
		return new WeightedAlphabetImpl(Collections.unmodifiableMap(frequencyMap));
	}
}
