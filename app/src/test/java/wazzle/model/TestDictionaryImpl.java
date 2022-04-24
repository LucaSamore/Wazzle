/**
 * 
 */
package wazzle.model;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;

import org.junit.Test;
import wazzle.model.common.*;

public class TestDictionaryImpl {

	/**
	 * 
	 * The private method "setDictionary" opens a stream while reading the file, the
	 * stream in then collected in to a Set. If the file is not present, or cannot
	 * be read, it throws an exception.
	 * 
	 * @param fileName The name or the relative path of the file you are trying to
	 *                 open. All the words in the file must be separated with a
	 *                 end-of-line character.
	 * @return
	 * 
	 * 
	 * 
	 */


	@Test
	public void testOpeningAnExitingFile() throws Exception {

		Set<String> testSet = new HashSet<>();
		testSet.add("AAAAA");
		testSet.add("BBBB");
		testSet.add("CCC");
		testSet.add("DD");
		testSet.add("E");
		testSet.add("(");

		Dictionary dict = new DictionaryImpl(TestFileUtils.readDataset("testDictionary.txt"));
		assertEquals(testSet, dict.getWords());
	};

	@Test(expected = IOException.class)
	public void testOpeningA_NOT_ExitingFile() throws Exception {
		Dictionary dict = new DictionaryImpl(TestFileUtils.readDataset("NotExistingDataset.txt"));
		System.out.println(dict.getWords());
	}
}
