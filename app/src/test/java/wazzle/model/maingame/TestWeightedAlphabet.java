/**
 * 
 */
package wazzle.model.maingame;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import wazzle.model.TestFileUtils;
import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.maingame.alphabet.Frequency;
import wazzle.model.maingame.alphabet.FrequencyImpl;
import wazzle.model.maingame.alphabet.WeightedAlphabet;
import wazzle.model.maingame.alphabet.WeightedAlphabetImpl;


public class TestWeightedAlphabet {
	Map<Character, Double> expectedTestMap;
	
	@Before
	public void buildExpectedMap() {
		this.expectedTestMap = new HashMap<Character, Double>();
		expectedTestMap.put('A', 0.3125);
		expectedTestMap.put('B', 0.25);
		expectedTestMap.put('C', 0.1875);
		expectedTestMap.put('D', 0.125);
		expectedTestMap.put('E', 0.0625);
		expectedTestMap.put('(', 0.0625);
	};

	@Test
	public void test() throws Exception {

		Dictionary dict = new DictionaryImpl(TestFileUtils.readDataset("testDictionary.txt"));
		Frequency freq = new FrequencyImpl(dict);
		WeightedAlphabet wa = new WeightedAlphabetImpl(freq.computeFrequency().getWeightedAlphabet());
		assertEquals(expectedTestMap, wa.getWeightedAlphabet());

	}

}
