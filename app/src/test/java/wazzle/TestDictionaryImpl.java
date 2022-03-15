/**
 * 
 */
package wazzle;

import static org.junit.Assert.*;

import java.nio.file.NoSuchFileException;
import java.util.HashSet;
import java.util.Set;
import java.io.File;  // Import the File class
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author A. Barbanti
 *
 */
public class TestDictionaryImpl {


	@Test
	public void testOpeningAnExitingFile() throws IOException {
		
		Set<String> testSet = new HashSet<>();
		testSet.add("AAAAA");		
		testSet.add("BBBB");		
		testSet.add("CCC");		
		testSet.add("DD");		
		testSet.add("E");		
		testSet.add("(");	
		
		Dictionary dict = new DictionaryImpl(".\\src\\test\\res\\testDictionary.txt");
		assertEquals(testSet, dict.getListOfWords());
	};

    @Test(expected = NoSuchFileException.class)
    public void testOpeningA_NOT_ExitingFile() throws IOException {
        Dictionary dict = new DictionaryImpl("notExistingFile");
//        assertThrows(null, null);
//       assertThrows(DictionaryImpl, NullPointerException.class);
    }
}
