package wazzle.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.minigame.Comparator;
import wazzle.model.minigame.ComparatorImpl;
import wazzle.model.minigame.FilteredDictionary;
import wazzle.model.minigame.FiveLetterDictionary;

public class TestComparator {

	@Test
	public void test() throws IOException {
		Set<String> filteredDataset = new HashSet<>(TestReader.readDataset("testDictionary.txt"));


		Dictionary filteredDictionary = new FiveLetterDictionary (new DictionaryImpl(filteredDataset));
		
		Set<String> savedOnFileDataset = new HashSet<>();
		savedOnFileDataset.add("AAAAA");
		savedOnFileDataset.add("BBBBB");

		Dictionary savedOnFileDictionary = new DictionaryImpl(savedOnFileDataset);
		
		Comparator comparator = new ComparatorImpl(savedOnFileDictionary);
		
		
		var targetWord = comparator.getSuitableWord();

		assertEquals(5, targetWord.length()); //the target word must be long 5 character
		assertTrue(savedOnFileDictionary.getWords().contains(targetWord)); //target word must not be present in the dictionary saved on file
//		assertTrue(filteredDictionary.getWords().contains(targetWord)); //i can add again the word and .add(targetWord);

		
		
		
	}

}
