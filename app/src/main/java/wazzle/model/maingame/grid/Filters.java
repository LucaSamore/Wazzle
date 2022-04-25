package wazzle.model.maingame.grid;

import java.util.Set;
import java.util.stream.Collectors;

import wazzle.model.maingame.letter.Letter;

/**
 * This {@link @FunctionalInterface} provides a set of static methods 
 * that can be used to perform filter operations on a {@code Set<Letter>} set. 
 * It also provides an applyFilters method that classes whose implement this interface can specify the behavior 
 * by calling, for example, all these static filters.
 * 
 * @see java.util.Set
 * @see wazzle.model.maingame.letter.Letter
 */
@FunctionalInterface
public interface Filters {
	
	/**
	 * Given a word and a {@code Set<Letter>}, performs a sequence of filtering operations on the set
	 * and returns a boolean describing the result of these operations.
	 * @param word a {@code String} describing a word.
	 * @param letters a {@code Set<Letter>} representing the set where operations need to be performed.
	 * @return {@code true} if the word passes all the filters called in this method, {@code false} otherwise.
	 * @see wazzle.model.maingame.letter.Letter
	 */
	boolean applyFilters(String word, Set<Letter> letters);
	
	
	/**
	 * Given a word and a {@code Set<Letter>}, checks whether all characters in the word are present in the letters set
	 * @param word a {@code String} describing a word
	 * @param letters a {@code Set<Letter>} representing the set where this operation needs to be performed.
	 * @return {@code true} if each character in word is present in letters.
	 * @see wazzle.model.maingame.letter.Letter
	 */
	static boolean checkLetters(final String word, final Set<Letter> letters) {
		return word.chars()
				.mapToObj(c -> (char) c)
				.filter(c -> letters.stream()
						.map(Letter::getContent)
						.collect(Collectors.toList()).contains(c))
				.collect(Collectors.toList())
				.size() == word.length();
	}
	
	/**
	 * Given a word and a {@code Set<Letter>}, checks whether the word is contained in the set of letters, by checking
	 * if a path of characters (that can form the beginning word) exists in the set. 
	 * This method uses an algorithm provided by {@link WordPathChecker} class.
	 * @param word a {@code String} describing a word
	 * @param letters a {@code Set<Letter>} representing the set where this operation needs to be performed.
	 * @return {@code true} if there is path inside the letters set, {@code false} otherwise.
	 * @see wazzle.model.maingame.letter.Letter
	 * @see wazzle.model.maingame.grid.WordPathChecker
	 */
	static boolean checkPath(final String word, final Set<Letter> letters) {
		final var pathChecker = new WordPathChecker();
		return pathChecker.exist(letters.stream()
                .collect(Collectors.toMap(Letter::getPosition, Letter::getContent)), word);
	}
}