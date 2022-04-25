package wazzle.model.minigame;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import wazzle.model.TestFileUtils;
import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.minigame.dictionary.FiveLetterDictionary;
import wazzle.model.minigame.word.WordsDispenser;
import wazzle.model.minigame.word.WordsDispenserImpl;

public class TestExtractedWordManager {

  @Test
  public void test() throws IOException {
    Set<String> dataset = new HashSet<>(TestFileUtils.readDataset("testDictionary.txt"));
    Dictionary filteredDictionary = new FiveLetterDictionary(new DictionaryImpl(dataset));
    WordsDispenser wordsDispenser = new WordsDispenserImpl(filteredDictionary);
    var targetWord = wordsDispenser.extractWord();

    assertEquals(5, targetWord.length()); // the target word must be long 5 character
  }
}
