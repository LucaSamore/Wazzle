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

package wazzle.model.maingame;

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
	 * 
	 * This method transform a Set of Strings into a Map, having as Key values all
	 * the letters present in the Set and as Values the number of occurrences of
	 * the associated key divided by the total number of letters (Relative Frequency),
	 * then it returns a new Object that stores it.
	 *
	 *
	 * @return WeightedAlphabetImpl An Object storing a Map with the letters present in the
	 *         						given set as keys and the relative number of occurrences
	 *         						as values.
	 * @see WeightedAlphabetImpl
	 * 
	 */

	public WeightedAlphabet computeFrequency() {

		Double numberOfLetters;
		Map<Character, Double> frequencyMap;

		frequencyMap = this.dataset.getListOfWords().stream().flatMap(k -> k.chars().mapToObj(v -> (char) v))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0D, e -> 1D, Double::sum)));

		numberOfLetters = frequencyMap.values().stream().mapToDouble(Double::doubleValue).sum();

		frequencyMap.entrySet().forEach(e -> e.setValue(e.getValue() / numberOfLetters));
		return new WeightedAlphabetImpl(frequencyMap);
	}
}
