/**
 * 
 * This class uses a stream to read all words from a given file and saving it in its own private field, throwing an exception
 * if file is not present. 
 */

package wazzle.model.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

			
			public class DictionaryImpl implements Dictionary {
			
				private Set<String> listOfWords;
			
				public DictionaryImpl(String fileName) throws IOException {
					setDictionary(fileName);
				}
				
				/**
				 * 
				 * The private method "setDictionary" opens a stream while reading the file, the stream in then collected in to a Set.
				 * If the file is not present, or cannot be read, it throws an exception.
				  
				 * @param 	fileName 	The name or the relative path of the file you are trying to
				 *  					open.
				 * 						All the words in the file must be separated with a end-of-line character.
				 * 
	 */

	private void setDictionary(String fileName) throws IOException {
		try (Stream<String> words = Files.lines(Paths.get(fileName))) {
			this.listOfWords = words.collect(Collectors.toSet());

		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * @return the Set containing all the words collected.
	 */
	
	@Override
	public Set<String> getListOfWords() {
		
		return Collections.unmodifiableSet(listOfWords);
	}

}
