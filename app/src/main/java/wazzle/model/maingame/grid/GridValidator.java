package wazzle.model.maingame.grid;

import java.util.Optional;
import java.util.Set;

import wazzle.model.maingame.letter.Letter;

/**
 * This interface provides a method for validating the quality of a given {@code Set<Letter>}
 * If the provided set is good, it returns an {@code Optional} describing a {@code Set<String>} containing
 * all the words that can be formed using the letters inside the previous set.
 * A validation method for test is provided as well.
 * 
 * @see wazzle.model.maingame.letter.Letter
 * @see java.util.Set
 */
public interface GridValidator {
	
	/**
	 * Given a {@code Set<Letter>}, returns an {@code Optional} containing the result of the validation process.
	 * @param letters a {@code Set<Letter>} representing the letters to be validated
	 * @return an {@code Optional.empty()} if the given set is not valid, otherwise an {@code Optional<Set<String>>} 
	 * containing all the words that can be formed using the given set.
	 */
	Optional<Set<String>> validate(final Set<Letter> letters);
	
	/**
	 * <pre>This method must be used for tests only!</pre>
	 * Given a {@code Set<Letter>}, returns an {@code Set<String>} containing the result of the validation process.
	 * @param letters a {@code Set<Letter>} representing the letters to be validated
	 * @return a {@code Set<String>} that contains all the the words that can be formed using the given set.
	 */
	Set<String> validateForTest(final Set<Letter> letters);
}