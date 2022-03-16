package wazzle.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.maingame.Frequency;
import wazzle.model.maingame.FrequencyImpl;

public class TestFrequency {
	private Map<Character, Long> expectedTestMap = new HashMap<Character, Long>();

	@Before
	public void buildExpectedMap() {
		expectedTestMap.put('A', 5L);
		expectedTestMap.put('B', 4L);
		expectedTestMap.put('C', 3L);
		expectedTestMap.put('D', 2L);
		expectedTestMap.put('E', 1L);
		expectedTestMap.put('(', 1L);
	};

	@Test
	public void test() throws IOException {
	Dictionary dict = new DictionaryImpl(".\\src\\test\\res\\testDictionary.txt");	
	Frequency freq = new FrequencyImpl(dict.getListOfWords());


		assertEquals(freq.getFrequency(), expectedTestMap);
	}

}
