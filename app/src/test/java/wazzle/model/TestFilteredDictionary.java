package wazzle.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import wazzle.controller.minigame.FiveLetterDictionary;
import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;

public class TestFilteredDictionary {
	
	@Test
	public void testFilteredDictionary() throws IOException {



		Set<String> targetSet = new HashSet<>();
		targetSet.add("AAAAA");
		
		Dictionary filteredDictionary = new FiveLetterDictionary(new DictionaryImpl(TestFileUtils.readDataset("testDictionary.txt")));
		assertEquals(filteredDictionary.getWords(), targetSet);
	};
}
