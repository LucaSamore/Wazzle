/*
 * 
 * 	This Class calculates the relative frequency of each letter present in the initial Map storing it in a new Map.
 * 
 * 	@param frequencyLetterMap	A Map (Character,Double) which stores the letter as keys and it's relative number of occurrences.
 * 	@param totalLetters			A Long number with the sum of all values in the map passed as first parameter.
 * 
 * */

package wazzle.model.maingame;

import java.util.HashMap;
import java.util.Map;

public class WeightedAlphabetImpl implements WeightedAlphabet {

	private Map<Character, Double> weightedLetterMap;

	public WeightedAlphabetImpl(Map<Character, Long> frequencyLetterMap, Long totalLetters) {

		this.weightedLetterMap = new HashMap<Character, Double>();
		this.setWeightedLetterMap(frequencyLetterMap, totalLetters);
	}

	/*
	 * This method returns the field weightedLetterMap.
	 * 
	 * @return 	weightedLetterMap 	A Map holding all the letter present in the input
	 * 								Map as keys and the relative value of all letters present in the given map
	 * 								compared to the total number of letters present as values.
	 * 
	 */

	@Override
	public Map<Character, Double> getWeightedAlphabet() {
		return weightedLetterMap;
	}

	/*
	 * This method calculates the relative value of all letters present in the given
	 * map compared to the total number of letters present.
	 * 
	 */

	private void setWeightedLetterMap(Map<Character, Long> frequencyLetterMap, Long totalLetters) {
		frequencyLetterMap.entrySet().stream()
				.forEach(e -> this.weightedLetterMap.put(e.getKey(), (e.getValue().doubleValue() / totalLetters)));
	}

}
