package wazzle.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

import org.junit.Test;

import javafx.util.Pair;
import wazzle.model.maingame.Letter;
import wazzle.model.maingame.LetterAllocator;
import wazzle.model.maingame.LetterAllocatorImpl;
import wazzle.model.maingame.LetterImpl;
import wazzle.model.maingame.Range;

public class TestLetterAllocatorImpl {

    @Test
    public void testGridChecker() {
        EnumMap<Range, List<Pair<Character, Double>>> choosenLetters16 = new EnumMap<Range, List<Pair<Character, Double>>>(
                Map.of(Range.HIGH_FREQUENCY,
                        List.of(new Pair<>('A', 10.5), new Pair<>('E', 8.9), new Pair<>('I', 10.0),
                                new Pair<>('O', 9.0), new Pair<>('A', 10.5), new Pair<>('E', 8.9),
                                new Pair<>('I', 10.0), new Pair<>('O', 9.0)),
                        Range.MEDIUM_FREQUENCY,
                        List.of(new Pair<>('C', 8.5), new Pair<>('T', 8.8), new Pair<>('R', 8.2), new Pair<>('R', 7.0),
                                new Pair<>('S', 8.4)),
                        Range.LOW_FREQUENCY,
                        List.of(new Pair<>('N', 3.0), new Pair<>('X', 0.5), new Pair<>('Z', 1.0))));
        LetterAllocator letterAllocator16 = new LetterAllocatorImpl(choosenLetters16, null);
        Set<Letter> allocatedLettersCorrectly = new HashSet<>(
                Set.of(new LetterImpl(new Pair<>(0, 0), 'A', 2), new LetterImpl(new Pair<>(0, 1), 'E', 2),
                        new LetterImpl(new Pair<>(0, 2), 'C', 2), new LetterImpl(new Pair<>(0, 3), 'N', 2),
                        new LetterImpl(new Pair<>(1, 0), 'I', 2), new LetterImpl(new Pair<>(1, 1), 'T', 2),
                        new LetterImpl(new Pair<>(1, 2), 'O', 2), new LetterImpl(new Pair<>(1, 3), 'S', 2),
                        new LetterImpl(new Pair<>(2, 0), 'X', 2), new LetterImpl(new Pair<>(2, 1), 'I', 2),
                        new LetterImpl(new Pair<>(2, 2), 'R', 2), new LetterImpl(new Pair<>(2, 3), 'O', 2),
                        new LetterImpl(new Pair<>(3, 0), 'R', 2), new LetterImpl(new Pair<>(3, 1), 'Z', 2),
                        new LetterImpl(new Pair<>(3, 2), 'E', 2), new LetterImpl(new Pair<>(3, 3), 'A', 2)));
        assertTrue(letterAllocator16.checkGridForTests(allocatedLettersCorrectly));

        Set<Letter> allocatedLettersWrongly = new HashSet<>(
                Set.of(new LetterImpl(new Pair<>(0, 0), 'A', 2), new LetterImpl(new Pair<>(0, 1), 'E', 2),
                        new LetterImpl(new Pair<>(0, 2), 'I', 2), new LetterImpl(new Pair<>(0, 3), 'O', 2),
                        new LetterImpl(new Pair<>(1, 0), 'I', 2), new LetterImpl(new Pair<>(1, 1), 'T', 2),
                        new LetterImpl(new Pair<>(1, 2), 'C', 2), new LetterImpl(new Pair<>(1, 3), 'S', 2),
                        new LetterImpl(new Pair<>(2, 0), 'X', 2), new LetterImpl(new Pair<>(2, 1), 'N', 2),
                        new LetterImpl(new Pair<>(2, 2), 'R', 2), new LetterImpl(new Pair<>(2, 3), 'O', 2),
                        new LetterImpl(new Pair<>(3, 0), 'R', 2), new LetterImpl(new Pair<>(3, 1), 'Z', 2),
                        new LetterImpl(new Pair<>(3, 2), 'E', 2), new LetterImpl(new Pair<>(3, 3), 'A', 2)));
        assertFalse(letterAllocator16.checkGridForTests(allocatedLettersWrongly));
    }

    @Test
    public void testAllocator4x4() {
        EnumMap<Range, List<Pair<Character, Double>>> choosenLetters16 = new EnumMap<Range, List<Pair<Character, Double>>>(
                Map.of(Range.HIGH_FREQUENCY,
                        List.of(new Pair<>('A', 10.5), new Pair<>('E', 8.9), new Pair<>('I', 10.0),
                                new Pair<>('O', 9.0), new Pair<>('A', 10.5), new Pair<>('E', 8.9),
                                new Pair<>('I', 10.0), new Pair<>('O', 9.0)),
                        Range.MEDIUM_FREQUENCY,
                        List.of(new Pair<>('C', 8.5), new Pair<>('T', 8.8), new Pair<>('R', 8.2), new Pair<>('R', 7.0),
                                new Pair<>('S', 8.4)),
                        Range.LOW_FREQUENCY,
                        List.of(new Pair<>('N', 3.0), new Pair<>('X', 0.5), new Pair<>('Z', 1.0))));
        List<Character> startingLetters16 = new ArrayList<>();
        choosenLetters16.values().stream()
                .forEach(c -> startingLetters16.addAll(c.stream().map(l -> l.getKey()).collect(Collectors.toList())));
        LetterAllocator letterAllocator16 = new LetterAllocatorImpl(choosenLetters16, null);
        Set<Letter> allocatedLetters16 = letterAllocator16.allocateForTests();
        List<Character> finalLetters16 = new ArrayList<>();
        allocatedLetters16.forEach(l -> finalLetters16.add(l.getContent()));
        assertTrue(startingLetters16.containsAll(finalLetters16));
    }

    @Test
    public void testAllocator5x5() {

        EnumMap<Range, List<Pair<Character, Double>>> choosenLetters25 = new EnumMap<Range, List<Pair<Character, Double>>>(
                Map.of(Range.HIGH_FREQUENCY, List.of(new Pair<>('A', 10.5), new Pair<>('E', 8.9), new Pair<>('I', 10.0),
                        new Pair<>('O', 9.0), new Pair<>('A', 10.5), new Pair<>('E', 8.9), new Pair<>('I', 10.0),
                        new Pair<>('O', 9.0), new Pair<>('E', 8.9), new Pair<>('A', 10.5), new Pair<>('E', 8.9),
                        new Pair<>('I', 10.0), new Pair<>('U', 8.8)), Range.MEDIUM_FREQUENCY,
                        List.of(new Pair<>('C', 8.5), new Pair<>('T', 8.8), new Pair<>('R', 8.2), new Pair<>('R', 7.0),
                                new Pair<>('S', 8.4), new Pair<>('L', 6.0), new Pair<>('C', 8.5), new Pair<>('T', 8.8)),
                        Range.LOW_FREQUENCY, List.of(new Pair<>('N', 3.0), new Pair<>('X', 0.5), new Pair<>('Z', 1.0),
                                new Pair<>('M', 2.9))));
        List<Character> startingLetters25 = new ArrayList<>();
        choosenLetters25.values().stream()
                .forEach(c -> startingLetters25.addAll(c.stream().map(l -> l.getKey()).collect(Collectors.toList())));
        LetterAllocator letterAllocator25 = new LetterAllocatorImpl(choosenLetters25, null);
        Set<Letter> allocatedLetters25 = letterAllocator25.allocateForTests();
        List<Character> finalLetters25 = new ArrayList<>();
        allocatedLetters25.forEach(l -> finalLetters25.add(l.getContent()));
        assertTrue(startingLetters25.containsAll(finalLetters25));
        assertTrue(letterAllocator25.checkGridForTests(allocatedLetters25));

    }

    @Test
    public void testAllocator6x6() {
        EnumMap<Range, List<Pair<Character, Double>>> choosenLetters36 = new EnumMap<Range, List<Pair<Character, Double>>>(
                Map.of(Range.HIGH_FREQUENCY,
                        List.of(new Pair<>('A', 10.5), new Pair<>('E', 8.9), new Pair<>('I', 10.0),
                                new Pair<>('O', 9.0), new Pair<>('A', 10.5), new Pair<>('E', 8.9),
                                new Pair<>('I', 10.0), new Pair<>('O', 9.0), new Pair<>('E', 8.9),
                                new Pair<>('A', 10.5), new Pair<>('E', 8.9), new Pair<>('I', 10.0),
                                new Pair<>('U', 8.8), new Pair<>('I', 10.0), new Pair<>('I', 10.0),
                                new Pair<>('O', 9.0), new Pair<>('E', 8.9), new Pair<>('A', 10.5)),
                        Range.MEDIUM_FREQUENCY,
                        List.of(new Pair<>('C', 8.5), new Pair<>('T', 8.8), new Pair<>('R', 8.2), new Pair<>('R', 7.0),
                                new Pair<>('S', 8.4), new Pair<>('L', 6.0), new Pair<>('C', 8.5), new Pair<>('T', 8.8),
                                new Pair<>('S', 8.4), new Pair<>('L', 6.0), new Pair<>('C', 8.5), new Pair<>('T', 8.8)),
                        Range.LOW_FREQUENCY, List.of(new Pair<>('N', 3.0), new Pair<>('X', 0.5), new Pair<>('Z', 1.0),
                                new Pair<>('M', 2.9), new Pair<>('M', 2.9), new Pair<>('N', 3.0))));
        List<Character> startingLetters36 = new ArrayList<>();
        choosenLetters36.values().stream()
                .forEach(c -> startingLetters36.addAll(c.stream().map(l -> l.getKey()).collect(Collectors.toList())));
        LetterAllocator letterAllocator36 = new LetterAllocatorImpl(choosenLetters36, null);
        Set<Letter> allocatedLetters36 = letterAllocator36.allocateForTests();
        List<Character> finalLetters36 = new ArrayList<>();
        allocatedLetters36.forEach(l -> finalLetters36.add(l.getContent()));
        assertTrue(startingLetters36.containsAll(finalLetters36));
        assertTrue(letterAllocator36.checkGridForTests(allocatedLetters36));

    }
}
