/*
*
*	This class is the implementation of Frequency Interface,
*	is used to count all the letters and its number of 
*	occurrences in the given Set.
	
*   @param words A Set containing all the words in a given file.
*  
*/

package wazzle.model.maingame;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyImpl implements Frequency {

	private Map<Character, Long> frequencyMap;
	private Long numberOfLetters;

	public FrequencyImpl(Set<String> words) {
		this.setFrequency(words);
		this.setNumberOfLetters();
	}

	/*
	 * 
	 * This method takes a Set of Strings as a parameter, transforms it into a Map,
	 * having as key values ​​all the letters present in the Set and as values
	 * ​​the number of occurrences of the associated key.
	 *
	 * @param words A Set of String containing all the Words found in the
	 * dictionary.
	 *
	 * 
	 */

	private void setFrequency(Set<String> words) {

		this.frequencyMap = words.stream().flatMap(k -> k.chars().mapToObj(v -> (char) v))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	/*
	 * 
	 * This method calculates the sum of all the values in the frequencyMap field
	 * and stores it in the numberOfLetters field.
	 * 
	 */

	private void setNumberOfLetters() {
		this.numberOfLetters = this.frequencyMap.values().stream().mapToLong(Long::valueOf).sum();

	}

	/*
	 * This method returns
	 * 
	 * @return frequencyMap A Map with the letters present in the given set as keys,
	 * the relative number of occurrences as values.
	 * 
	 */
	public Map<Character, Long> getFrequency() {
		return this.frequencyMap;

	}

	@Override
	public Long getTotalLetters() {
		return this.numberOfLetters;
	}

}
