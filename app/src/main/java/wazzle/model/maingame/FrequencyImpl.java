/**
*
*	This class is the implementation of Frequency Interface,
*	it counts all the letters and its number of 
*	occurrences in the given Set.
*	
*  	@param words A Set containing all the words in a given file.
*  
*/

package wazzle.model.maingame;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;

public class FrequencyImpl implements Frequency {

	private Map<Character, Double> frequencyMap;
	private Dictionary dict;
	public FrequencyImpl() {
		try {
			this.frequencyMap = new HashMap<>();
			this.dict = new DictionaryImpl(".\\src\\test\\res\\testDictionary.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setFrequency(dict.getListOfWords());
	}

	/**
	 * 
	 * This method takes a Set of Strings as a parameter, transforms it into a Map,
	 * having as Key values all the letters present in the Set and as Values the
	 * number of occurrences of the associated key divided by the total number of
	 * letters (Relative Frequency).
	 *
	 * @param words A Set of String containing all the Words found in the
	 *              dictionary.
	 *
	 * 
	 */

	private void setFrequency(Set<String> words) {

		Double numberOfLetters;

		this.frequencyMap = words.stream().flatMap(k -> k.chars().mapToObj(v -> (char) v))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0D, e -> 1D, Double::sum)));

		numberOfLetters = frequencyMap.values().stream().mapToDouble(Double::doubleValue).sum();

		this.frequencyMap.entrySet().forEach(e -> e.setValue(e.getValue() / numberOfLetters));

	}

	/**
	 * This method returns
	 * 
	 * @return frequencyMap An unmodifiable Map with the letters present in the
	 *         given set as keys and the relative number of occurrences as values.
	 * 
	 */
	public Map<Character, Double> getMappedWeightedAlphabet() {
		return Collections.unmodifiableMap(this.frequencyMap);

	}
}
