package wazzle.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import wazzle.controller.minigame.FiveLetterDictionary;
import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.minigame.ExtractedWordManager;
import wazzle.model.minigame.ExtractedWordManagerImpl;
import wazzle.model.minigame.WordsDispenser;
import wazzle.model.minigame.WordsDispenserImpl;

public class TestExtractedWordManager {

	@Test
	public void test() throws IOException {
		Set<String> dataset = new HashSet<>(TestFileUtils.readDataset("testDictionary.txt"));
		Dictionary filteredDictionary = new FiveLetterDictionary (new DictionaryImpl(dataset));
		ExtractedWordManager ewm = new ExtractedWordManagerImpl(filteredDictionary);
		WordsDispenser wordsDispenser = new WordsDispenserImpl(ewm);
		var targetWord = wordsDispenser.extractWord();

		assertEquals(5, targetWord.length()); //the target word must be long 5 character
		assertFalse(ewm.getAvailableWords().contains(targetWord)); //target word must not be present in the dictionary saved on file
		
	}

}
