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
import wazzle.model.maingame.WeightedAlphabet;
import wazzle.model.maingame.WeightedAlphabetImpl;


public class TestAlphabetClassifierImpl {
	
	@Test
	public void testClassifier() {
		WeightedAlphabet weightedAlphabet = new WeightedAlphabetImpl(Map.of('A', 29.60,
				'F', 11.5, 
				'E', 19.40, 
				'C', 21.0, 
				'D', 18.5));
		AlphabetClassifier classifier = new AlphabetClassifierImpl(weightedAlphabet);
		assertEquals(classifier.classify(), new EnumMap<Range, WeightedAlphabet>(
				Map.of(Range.HIGH_FREQUENCY, new WeightedAlphabetImpl(Map.of('A', 29.60, 'E', 19.40)), 
						Range.MEDIUM_FREQUENCY, new WeightedAlphabetImpl(Map.of('C', 21.00)), 
						Range.LOW_FREQUENCY, new WeightedAlphabetImpl(Map.of('D', 18.50, 'F', 11.50)))));
	}

}
