package wazzle.model.common;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface BonusStrategies {
	
	static BinaryOperator<Double> scoreBonus() {
		return (current, bonus) -> current + bonus;
	}
	
	static UnaryOperator<Long> timeBonus() {
		return i -> i+60000;
	}
	
	static UnaryOperator<Set<String>> wordBonus() {
		return s -> extractWords(s);							
	}
	
	private static Set<String> extractWords(final Set<String> words) {
		var random = new Random();
		Set<String> extractedWords = new HashSet<>();
		IntStream.rangeClosed(1, 3)
		  .boxed()
		  .forEach(i -> extractedWords.add(words.stream()
				  				 .collect(Collectors.toList())
				  				 .get(random.nextInt(i))));
		return extractedWords;
	}
}
