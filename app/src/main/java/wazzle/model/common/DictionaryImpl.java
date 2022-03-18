/**
 * 
 * This class uses a stream to read all words from a given file and saving it in its own private field, throwing an exception
 * if file is not present. 
 */

package wazzle.model.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionaryImpl implements Dictionary {

	private Set<String> words;

	public DictionaryImpl(String fileName) throws IOException {
		setListOfWords(fileName);
	}

	/**
	 * 
	 * The private method "setDictionary" opens a stream while reading the file, the
	 * stream in then collected in to a Set. If the file is not present, or cannot
	 * be read, it throws an exception.
	 * 
	 * @param fileName The name or the relative path of the file you are trying to
	 *                 open. All the words in the file must be separated with a
	 *                 end-of-line character.
	 * 
	 * @debug this method will be removed TODO
	 */

	private void setListOfWords(String fileName) {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			this.words = stream.collect(Collectors.toSet());
		}
		catch(IOException e){
			System.err.println("File non trovato");
			e.printStackTrace();
		}
	}



	/**
	 * @return The Set containing all the words collected.
	 */

	public Set<String> getWords() {
		return Collections.unmodifiableSet(this.words);
	}

}
