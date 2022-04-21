package wazzle.model.common;

import java.util.HashSet;

import java.util.Random;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * 
 * BonusStrategies implements all the strategies that the bonuses have to apply when their are used.
 *
 */
public interface BonusStrategies {
	
	/**
	 * Implements the application strategy for ScoreBonus. 
	 * 
	 * @return BinaryOperator<Double> which represent the operation that must be done when the ScoreBonus is used.
	 */
	static BinaryOperator<Integer> scoreBonus() {
		return (current, bonus) -> current + bonus;
	}
	
	/**
	 * Implements the application strategy for TimeBonus. 
	 * 
	 * @return BinaryOperator<Double> which represent the operation that must be done when the TimeBonus is used.
	 */
	static UnaryOperator<Long> timeBonus() {
		return i -> i + 30;
	}
	
	/**
	 * Implements the application strategy for WordBonus. 
	 * 
	 * @return BinaryOperator<Double> which represent the operation that must be done when the WordBonus is used.
	 */
	static UnaryOperator<Set<String>> wordBonus() {
		return s -> extractWords(s);							
	}
	
	/**
	 * Extract the words that have to been suggested to the user.
	 * 
	 * @param words the words that the user hasn't already found.
	 * @return Set<String> which contains the extracted words that will be suggested to the user.
	 */
	private static Set<String> extractWords(final Set<String> words) {
		var random = new Random();
		Set<String> extractedWords = new HashSet<>();
		while (extractedWords.size() != 3) {
			extractedWords.add(words.stream()
	  				 				.collect(Collectors.toList())
	  				 				.get(random.nextInt(words.size()-1)));
		}
		return extractedWords;
	}
}
