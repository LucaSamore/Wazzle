package wazzle.model.maingame;

import java.util.Set;
import java.util.stream.Collectors;


@FunctionalInterface
public interface Filters {
	
	boolean applyAll(final String word, final Set<Letter> letters);
	
	static boolean checkLetters(final String word, final Set<Letter> letters) {
		return word.chars()
				.mapToObj(c -> (char) c)
				.filter(c -> letters.stream()
						.map(Letter::getContent)
						.collect(Collectors.toList()).contains(c))
				.collect(Collectors.toList())
				.size() == word.length();
	}
	
	static boolean checkPath(final String word, final Set<Letter> letters) {
		var pathChecker = new WordPathChecker();
		return pathChecker.exist(letters.stream()
                .collect(Collectors.toMap(l -> l.getPosition(), l -> l.getContent())), word);
	}
}
