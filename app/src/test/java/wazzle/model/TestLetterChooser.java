package wazzle.model;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import javafx.util.Pair;
import wazzle.model.maingame.WeightedAlphabet;
import wazzle.model.maingame.WeightedAlphabetImpl;
import wazzle.model.maingame.LetterChooser;
import wazzle.model.maingame.LetterChooserImpl;
import wazzle.model.maingame.Range;

public class TestLetterChooser {
	
//	private static final double CONSTANCE_ROUND = 0.5;

	@Test
	public void testChooser() {
		EnumMap<Range, WeightedAlphabet> classifiedLetters = new EnumMap<Range, WeightedAlphabet>(Map.of(
				Range.HIGH_FREQUENCY, new WeightedAlphabetImpl (Map.of('A', 21.0,'E', 20.0)), 
				Range.MEDIUM_FREQUENCY, new WeightedAlphabetImpl (Map.of('C', 15.0, 'T', 11.0, 'R', 10.0)), 
				Range.LOW_FREQUENCY, new WeightedAlphabetImpl (Map.of('L', 9.0, 'M', 8.0, 'P', 6.0))));
		LetterChooser letterChooser = new LetterChooserImpl(classifiedLetters, new Pair<>(4, 4), null);
		EnumMap<Range, List<Pair<Character, Double>>> choosenLetters = letterChooser.chooseForTests();
		assertEquals((choosenLetters.get(Range.HIGH_FREQUENCY)).size(), 8);
		assertEquals((choosenLetters.get(Range.MEDIUM_FREQUENCY)).size(), 5);
		assertEquals((choosenLetters.get(Range.LOW_FREQUENCY)).size(), 3);
		
		
			
	}

}
