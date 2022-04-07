package wazzle.model;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Test;

import wazzle.model.maingame.AlphabetClassifier;
import wazzle.model.maingame.AlphabetClassifierImpl;
import wazzle.model.maingame.Range;
import wazzle.model.maingame.WeightedAlphabet;
import wazzle.model.maingame.WeightedAlphabetImpl;


public class TestAlphabetClassifierImpl {
	
	@Test
	public void testClassifier() {
		WeightedAlphabet weightedAlphabet = new WeightedAlphabetImpl(Map.of('A', 29.60,
				'F', 11.5, 
				'E', 19.40, 
				'L', 21.0, 
				'D', 18.5));
		double rangeQuote = weightedAlphabet.getWeightedAlphabet().values().stream().reduce((double) 0, Double::sum)/Range.values().length;
		AlphabetClassifier classifier = new AlphabetClassifierImpl(weightedAlphabet);
		EnumMap <Range, WeightedAlphabet> testEnumMap = new EnumMap<Range, WeightedAlphabet>(
				Map.of(Range.HIGH_FREQUENCY, new WeightedAlphabetImpl(Map.of('A', 29.60, 'L', 21.00)), 
						Range.MEDIUM_FREQUENCY, new WeightedAlphabetImpl(Map.of('E', 19.40, 'D', 18.50)), 
						Range.LOW_FREQUENCY, new WeightedAlphabetImpl(Map.of('F', 11.50))));
		assertTrue(testEnumMap.equals(classifier.classify()));
		
		double sumHigh = testEnumMap.get(Range.HIGH_FREQUENCY).getWeightedAlphabet().values().stream().reduce((double) 0, Double::sum);
		double sumMedium = testEnumMap.get(Range.MEDIUM_FREQUENCY).getWeightedAlphabet().values().stream().reduce((double) 0, Double::sum);
		double sumLow = testEnumMap.get(Range.LOW_FREQUENCY).getWeightedAlphabet().values().stream().reduce((double) 0, Double::sum);
		assertTrue(sumHigh >= rangeQuote);
		assertTrue(sumMedium >= rangeQuote);
		assertTrue(sumLow <= rangeQuote);
	
	}

}
