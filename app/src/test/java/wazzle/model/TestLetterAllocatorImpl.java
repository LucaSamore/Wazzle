package wazzle.model;

import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

import org.junit.Test;

import javafx.util.Pair;
import wazzle.model.maingame.Letter;
import wazzle.model.maingame.LetterImpl;
import wazzle.model.maingame.LetterAllocator;
import wazzle.model.maingame.LetterAllocatorImpl;
import wazzle.model.maingame.LetterImpl;
import wazzle.model.maingame.Range;
import wazzle.model.maingame.WeightedAlphabet;
import wazzle.model.maingame.WeightedAlphabetImpl;

public class TestLetterAllocatorImpl {

	@Test
	public void testAllocator() {
		EnumMap<Range, List<Pair<Character, Double>>> choosenLetters = new EnumMap<Range, List<Pair<Character, Double>>> 
				(Map.of(Range.HIGH_FREQUENCY, List.of(new Pair<>('A', 10.5), new Pair<>('E', 8.9), 
						new Pair<>('I', 10.0), new Pair<>('0', 9.0), 
						new Pair<>('A', 10.5), new Pair<>('E', 8.9), 
						new Pair<>('I', 10.0), new Pair<>('0', 9.0)), 
				Range.MEDIUM_FREQUENCY, List.of(new Pair<>('C', 8.5), new Pair<>('T', 8.8), 
						new Pair<>('R', 8.2), new Pair<>('R', 7.0), 
						new Pair<>('S', 8.4)), 
				Range.LOW_FREQUENCY, List.of(new Pair<>('N', 3.0), new Pair<>('X', 0.5), 
						new Pair<>('Z', 1.0))));
		
		LetterAllocator letterAllocator = new LetterAllocatorImpl(choosenLetters);
		Set<Letter> allocatedLetters = letterAllocator.alloc();
		
//		List<Pair<Character, Double>> allocatedLettersTotal = new LinkedList<>();
//		allocatedLetters.forEach(v -> allocatedLettersTotal.add(new Pair<Character, Double>(v.getContent(), v.getPosition())));
//		List<Pair<Character, Double>> choosenLettersTotal = new LinkedList<>();
//		choosenLetters.forEach((k,v) -> choosenLettersTotal.addAll(v));
//		assertEquals(choosenLettersTotal, allocatedLettersTotal);
		
//		Set<Letter> allocatedLetters = Set.of(new LetterImpl('I', new Pair<>(0,0)), new LetterImpl('O', new Pair<>(0,1)), new LetterImpl('R', new Pair<>(0,2)), new LetterImpl('R', new Pair<>(0,3)), 
//				new LetterImpl('I', new Pair<>(1,0)), new LetterImpl('I', new Pair<>(1,1)), new LetterImpl('I', new Pair<>(1,2)), new LetterImpl('I', new Pair<>(1,3)), 
//				new LetterImpl('I', new Pair<>(2,0)), new LetterImpl('I', new Pair<>(2,1)), new LetterImpl('I', new Pair<>(2,2)), new LetterImpl('I', new Pair<>(2,3)),
//				new LetterImpl('I', new Pair<>(3,0)), new LetterImpl('I', new Pair<>(3,1)), new LetterImpl('I', new Pair<>(3,2)), new LetterImpl('I', new Pair<>(3,3)));
		fail("Not yet implemented");
	}

}
