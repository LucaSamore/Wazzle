package wazzle.model;

import static org.junit.Assert.*;


import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import javafx.util.Pair;
import wazzle.model.maingame.WeightedAlphabet;
import wazzle.model.maingame.WeightedAlphabetImpl;
import wazzle.model.maingame.LetterChooser;
import wazzle.model.maingame.LetterChooserImpl;
import wazzle.model.maingame.Range;

public class TestLetterChooser {
	
	private static final double CONSTANCE_ROUND = 0.5;

	@Test
	public void testChooser() {
		Map<Range, WeightedAlphabet> classifiedLetters = new EnumMap<Range, WeightedAlphabet>(Map.of(
				Range.HIGH_FREQUENCY, new WeightedAlphabet (Map.of("A", 21,"E", 20)), 
				Range.MEDIUM_FREQUENCY, new WeightedAlphabet (Map.of("C", 15, "T", 11, "R", 10)), 
				Range.LOW_FREQUENCY, new WeightedAlphabet (Map.of("L", 9, "M", 8, "P", 6))));
		LetterChooser letterChooser = new LetterChooserImpl(classifiedLetters, new Pair<>(4, 4));
		Set<Range> ranges = Stream.of(Range.values()).collect(Collectors.toSet());
		int totalWeight = ranges.stream().map(Range::getWeight).reduce(0, (x, y) -> x + y);
		EnumMap<Range, List<Pair<Character, Double>>> choosenLetters = letterChooser.choose();
		assertEquals((choosenLetters.get(Range.HIGH_FREQUENCY)).size(), (int)((float) gridShape.getKey()*gridShape.getValue()/totalWeight + CONSTANCE_ROUND));
		assertEquals((choosenLetters.get(Range.MEDIUM_FREQUENCY)).size(), (int)((float) gridShape.getKey()*gridShape.getValue()/totalWeight + CONSTANCE_ROUND));
		assertEquals((choosenLetters.get(Range.LOW_FREQUENCY)).size(), (int)((float) gridShape.getKey()*gridShape.getValue()/totalWeight + CONSTANCE_ROUND));
		
		
			
	}

}
