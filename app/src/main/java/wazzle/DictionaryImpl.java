/**
 * 
 */
package wazzle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author A. Barbanti
 *
 */
public class DictionaryImpl implements Dictionary {

	private Set<String> listOfWords;

	public DictionaryImpl(String fileName) throws IOException {
		setDictionary(fileName);
	}

	private void setDictionary(String fileName) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			this.listOfWords = stream.collect(Collectors.toSet());

		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public Set<String> getListOfWords() {
		return this.listOfWords;
	}

}
