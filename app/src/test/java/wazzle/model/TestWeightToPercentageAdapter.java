/**
 * 
 */
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
import wazzle.model.maingame.WeightToPercentageAdapter;
import wazzle.model.maingame.WeightToPercentageAdapterImpl;


public class TestWeightToPercentageAdapter {

	Map<Character, Double> expectedTestMap = new HashMap<Character, Double>();

	@Test
	public void test() throws IOException {
		

		Dictionary dict = new DictionaryImpl(".\\src\\test\\res\\testDictionary.txt");
		Frequency freq = new FrequencyImpl(dict.getListOfWords());	
		WeightToPercentageAdapter wai = new WeightToPercentageAdapterImpl(freq.getFrequency(), freq.getTotalLetters());
		assertEquals(expectedTestMap, wai.getWeightedAlphabet());
			
	}
	
	@Before
	public void buildExpectedMap() {
		expectedTestMap.put('A', 31.25);
		expectedTestMap.put('B', 25.00);
		expectedTestMap.put('C', 18.75);
		expectedTestMap.put('D', 12.5);
		expectedTestMap.put('E', 6.25);
		expectedTestMap.put('(', 6.25);
	}

}
