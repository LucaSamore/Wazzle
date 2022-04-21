/**
 * 
 * This class uses a stream to read all words from a given file and saving it in its own private field, throwing an exception
 * if file is not present. 
 */

package wazzle.model.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class DictionaryImpl implements Dictionary {

	private final Set<String> words;

	public DictionaryImpl(final Set<String> words) {
		this.words = new HashSet<>(words);
	}

	/**
	 * @return The Set containing all the words collected.
	 */
	@Override
	public Set<String> getWords() {
		return Collections.unmodifiableSet(this.words);
	}

}
