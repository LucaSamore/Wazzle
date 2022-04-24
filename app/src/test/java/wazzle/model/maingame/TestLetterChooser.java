package wazzle.model.maingame;

import static org.junit.Assert.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import javafx.util.Pair;
import wazzle.model.maingame.alphabet.WeightedAlphabet;
import wazzle.model.maingame.alphabet.WeightedAlphabetImpl;
import wazzle.model.maingame.letter.LetterChooser;
import wazzle.model.maingame.letter.LetterChooserImpl;
import wazzle.model.maingame.letter.Range;

public class TestLetterChooser {

	@Test
	public void testChooser4x4() {
		EnumMap<Range, WeightedAlphabet> classifiedLetters = new EnumMap<Range, WeightedAlphabet>(Map.of(
				Range.HIGH_FREQUENCY, new WeightedAlphabetImpl (Map.of('A', 21.0,'E', 20.0)), 
				Range.MEDIUM_FREQUENCY, new WeightedAlphabetImpl (Map.of('C', 15.0, 'T', 11.0, 'R', 10.0)), 
				Range.LOW_FREQUENCY, new WeightedAlphabetImpl (Map.of('L', 9.0, 'M', 8.0, 'P', 6.0))));
		LetterChooser letterChooser = new LetterChooserImpl(classifiedLetters, new Pair<>(4, 4), null);
		//EnumMap<Range, List<Pair<Character, Double>>> choosenLetters = letterChooser.chooseForTests();
		//Mediator mediator = new Mediator(dataset, new Pair<>(4,4));
		EnumMap<Range, List<Pair<Character, Double>>> choosenLetters = letterChooser.chooseForTests();
		assertEquals((choosenLetters.get(Range.HIGH_FREQUENCY)).size(), 8);
		assertEquals((choosenLetters.get(Range.MEDIUM_FREQUENCY)).size(), 5);
		assertEquals((choosenLetters.get(Range.LOW_FREQUENCY)).size(), 3);		
	}
	
	@Test
	public void testChooser5x5() {
		EnumMap<Range, WeightedAlphabet> classifiedLetters = new EnumMap<Range, WeightedAlphabet>(Map.of(
				Range.HIGH_FREQUENCY, new WeightedAlphabetImpl (Map.of('A', 21.0,'E', 20.0)), 
				Range.MEDIUM_FREQUENCY, new WeightedAlphabetImpl (Map.of('C', 15.0, 'T', 11.0, 'R', 10.0)), 
				Range.LOW_FREQUENCY, new WeightedAlphabetImpl (Map.of('L', 9.0, 'M', 8.0, 'P', 6.0))));
		LetterChooser letterChooser = new LetterChooserImpl(classifiedLetters, new Pair<>(5, 5), null);
		EnumMap<Range, List<Pair<Character, Double>>> choosenLetters = letterChooser.chooseForTests();
		assertEquals((choosenLetters.get(Range.HIGH_FREQUENCY)).size(), 13);
		assertEquals((choosenLetters.get(Range.MEDIUM_FREQUENCY)).size(), 8);
		assertEquals((choosenLetters.get(Range.LOW_FREQUENCY)).size(), 4);		
	}
	
	@Test
	public void testChooser6x6() {
		EnumMap<Range, WeightedAlphabet> classifiedLetters = new EnumMap<Range, WeightedAlphabet>(Map.of(
				Range.HIGH_FREQUENCY, new WeightedAlphabetImpl (Map.of('A', 21.0,'E', 20.0)), 
				Range.MEDIUM_FREQUENCY, new WeightedAlphabetImpl (Map.of('C', 15.0, 'T', 11.0, 'R', 10.0)), 
				Range.LOW_FREQUENCY, new WeightedAlphabetImpl (Map.of('L', 9.0, 'M', 8.0, 'P', 6.0))));
		LetterChooser letterChooser = new LetterChooserImpl(classifiedLetters, new Pair<>(6, 6), null);
		EnumMap<Range, List<Pair<Character, Double>>> choosenLetters = letterChooser.chooseForTests();
		assertEquals((choosenLetters.get(Range.HIGH_FREQUENCY)).size(), 18);
		assertEquals((choosenLetters.get(Range.MEDIUM_FREQUENCY)).size(), 12);
		assertEquals((choosenLetters.get(Range.LOW_FREQUENCY)).size(), 6);		
	}

}