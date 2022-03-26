/**
 * 
 */
package wazzle.model;

import static org.junit.Assert.*;

import java.nio.file.NoSuchFileException;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;

import org.junit.Test;
import wazzle.model.common.*;

public class TestDictionaryImpl {


	@Test
	public void testOpeningAnExitingFile() throws Exception {
		
		Set<String> testSet = new HashSet<>();
		testSet.add("AAAAA");		
		testSet.add("BBBB");		
		testSet.add("CCC");		
		testSet.add("DD");
		testSet.add("E");		
		testSet.add("(");	
		
		Dictionary dict = new DictionaryImpl(".\\src\\test\\res\\testDictionary.txt");
		assertEquals(testSet, dict.getWords());
	};

    @Test(expected = NoSuchFileException.class)
    public void testOpeningA_NOT_ExitingFile() throws Exception {
        Dictionary dict = new DictionaryImpl("notExistingFile");
        System.out.println(dict.getWords());
    }
}