package wazzle.model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.junit.Test;

import wazzle.model.maingame.AlphabetClassifier;
import wazzle.model.maingame.AlphabetClassifierImpl;
import wazzle.model.maingame.Range;


public class TestAlphabetClassifierImpl {
	
	@Test
	public void testClassifier() {
		WeightedAlphabet weightedAlphabet = new WeightedAlphabetImpl(Map.of("A", 29.60, 
				"F", 11.5, 
				"E", 19.40, 
				"C", 21, 
				"D", 18.5), 100);
		AlphabetClassifier classifier = new AlphabetClassifierImpl();
		assertEquals(classifier.classify(weightedAlphabet), new EnumMap<Range, WeightedAlphabet>(
				Map.of(Range.HIGH_FREQUENCY, new WeightedAlphabet(Map.of("A", 29.60, "E", 19.40)), 
						Range.MEDIUM_FREQUENCY, new WeightedAlphabet(Map.of("C", 21)), 
						Range.LOW_FREQUENCY, new WeightedAlphabet(Map.of("D", 18.5, "f", 11.5)))));
	}

}
