package wazzle.model;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import wazzle.model.common.Dictionary;
import wazzle.model.minigame.FilteredDictionaryImpl;

public class TestFilteredWords {
	
	@Test
	public void testOpeningAnExitingFile() {

		Set<String> testSet = new HashSet<>();
		testSet.add("AAAAA");
		testSet.add("BBBBB");
		testSet.add("CCC");
		testSet.add("DD");
		testSet.add("E");
		testSet.add("(");

		Set<String> targetSet = new HashSet<>();
		targetSet.add("AAAAA");
		targetSet.add("BBBBB");
		
		Dictionary fd = new FilteredDictionaryImpl(testSet);
		assertEquals(fd.getWords(), targetSet);
	};
}
