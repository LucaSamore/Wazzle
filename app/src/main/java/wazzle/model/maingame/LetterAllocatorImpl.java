package wazzle.model.maingame;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.util.Pair;

public class LetterAllocatorImpl implements LetterAllocator {
	
	private EnumMap<Range, List<Pair<Character, Double>>> choosenLetters;
	private int gridSize;
	private int shape;
	private List<Pair<Character, Double>> value;
	
	
	public LetterAllocatorImpl(EnumMap<Range, List<Pair<Character, Double>>> choosenLetters) {
		this.choosenLetters = new EnumMap<Range, List<Pair<Character, Double>>>(choosenLetters);
		this.gridSize = this.choosenLetters.values().stream()
				  .map(l -> l.size())
				  .reduce(0, (x, y) -> x + y);
		this.shape = (int) Math.sqrt(this.gridSize);
	}
	
	@Override
	public Set<Letter> alloc() {
		//Map<Pair<Character, Pair<Integer, Integer>>, Range> allocatedLetters;
		Set<Letter> allocatedLetters;
		Set<Pair<Integer, Integer>> grid = new HashSet<>();
		IntStream.range(-1, this.shape-1).boxed()
		.forEach(i -> IntStream.range(-1, this.shape-1).boxed()
				.forEach(j -> grid.add(new Pair<>(i+1, j+1))));
		List<Character> toAllocLetters = new ArrayList<>();
		this.choosenLetters.values().stream().forEach(c -> toAllocLetters.addAll(c.stream().map(l -> l.getKey()).collect(Collectors.toList())));
		//estraggo casualmente le lettere
		Set<Letter> extractedLetters = this.extractLetters(toAllocLetters, grid);
		System.out.println(extractedLetters.toString());
		System.out.println(grid.toString());
		allocatedLetters = extractedLetters;
//		while (this.checkGrid(extractedLetters) == allocatedLetters) {
//			allocatedLetters = this.checkGrid(extractedLetters);
//		}
		return allocatedLetters;
	}
	
	//determina come deve essere conformata una riga/colonna - ossia con un numero di lettere per range compreso tra .. a ...
	private EnumMap<Range, Pair<Integer, Integer>> getNumberOfLetterForShape() {
		EnumMap<Range, Pair<Integer, Integer>> rangeForRange = new EnumMap<Range, Pair<Integer, Integer>>(Range.class);
		this.choosenLetters.entrySet().forEach(e -> {
			rangeForRange.put(e.getKey(), new Pair<>(
					getChoosenLetterListSize(e.getKey())/this.shape - 1 < 0 ? 0 : getChoosenLetterListSize(e.getKey())/this.shape - 1, 
							getChoosenLetterListSize(e.getKey())/this.shape + 1));
		});
		return rangeForRange;
	}
	
	private int getChoosenLetterListSize(Range r) {
		return this.choosenLetters.get(r).size();
	}
	
	//riempie la griglia casualmente con le lettere
	private Set<Letter> extractLetters(List<Character> toAllocLetters, Set<Pair<Integer, Integer>> grid) {
		Set<Letter> allocatedLetters = new HashSet<>();
		Random random = new Random();
		Set<Integer> alreadyExtractedIndex = new HashSet<>();
		System.out.println(grid.size());
		for(var p: grid) {
			System.out.println("size:" + toAllocLetters.size());
			do {
				extraction = random.nextInt(toAllocLetters.size()-1);
			}
			while (alreadyExtractedIndex.contains(extraction));
			alreadyExtractedIndex.add(extraction);
			System.out.println(extraction);
			toAllocLetters.remove(extraction);
			allocatedLetters.add(new LetterImpl(p, toAllocLetters.get(extraction)));
			System.out.println(allocatedLetters.toString());
			System.out.println("final size" + allocatedLetters.size());
		}
		return allocatedLetters;
	}
	
	private Set<Letter> getRow(Set<Letter> alreadyAllocated, int row) {
		return alreadyAllocated.stream().filter(l -> l.getPosition().getKey() == row).collect(Collectors.toSet());
	}
	
	
	private Set<Letter> getColumn(Set<Letter> alreadyAllocated, int column) {
		return alreadyAllocated.stream().filter(l -> l.getPosition().getValue() == column).collect(Collectors.toSet());
	}
	
	//mi restituisce un range data una lettera
	private Range getLetterRange(Character c) {
		return this.choosenLetters.entrySet().stream().filter(e -> e.getValue().stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue)).keySet().contains(c)).findFirst().get().getKey();
	}
	
	//mi dice quante lettere ci sono per ogni range
	private EnumMap<Range, Integer> getAttendency(Set<Letter> letters) {
		EnumMap<Range, Integer> attendency = new EnumMap<>(Range.class);
		letters.stream().forEach(l -> attendency.put(this.getLetterRange(l.getContent()), attendency.getOrDefault(this.getLetterRange(l.getContent()), 0) + 1));
		return attendency;
	}
	
	//mi dice quali sono i set che hanno delle lettere in più
	private Optional<Set<Range>> getRangeOver(EnumMap<Range, Integer> attendency, EnumMap<Range, Pair<Integer, Integer>> rangeForRange) {
		Map<Range, Integer> badRanges = attendency.entrySet().stream().filter(e -> e.getValue() < rangeForRange.get(e.getKey()).getKey() && e.getValue() > rangeForRange.get(e.getKey()).getValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return badRanges.size() == 0 ? Optional.empty() : Optional.of(badRanges.keySet());
	}
	 
	//mi dice quante lettere ci sono in più per range
	private int howManyLetterMoreForRange(Set<Letter> letters, Range r) {
		return this.getAttendency(letters).get(r) - this.getNumberOfLetterForShape().get(r).getValue();
	}
	
	//
	private Map<Letter, Range> getLettersToReplace(Set<Letter> letters) {
		Map<Letter, Range> toReplace = new HashMap<>();
		for (var l: letters) {
			if (this.getRangeOver(this.getAttendency(letters), this.getNumberOfLetterForShape()).get().contains(this.getLetterRange(l.getContent()))) {
				this.getRangeOver(this.getAttendency(letters), this.getNumberOfLetterForShape()).get().forEach(r -> toReplace.put(l, r));
			}
		}	
		return toReplace;
	}
	
	private Set<Letter> replaceLetter(Map<Letter, Range> toReplace, Set<Letter> alreadyAllocated) {
//		toReplace.forEach((k1, v1) -> 
//			toReplace.forEach((k2, v2) -> 
//			v1 == this.getLetterRange(k2.getContent()) || v2 == this.getLetterRange(k1.getContent()) 
//			? this.switchLetter(k1, k2, alreadyAllocated) : null));
		for (var e1: toReplace.entrySet()) {
			for (var e2: toReplace.entrySet()) {
				if (e1.getValue() == this.getLetterRange(e2.getKey().getContent()) || 
						e2.getValue() == this.getLetterRange(e1.getKey().getContent())) {
					alreadyAllocated = this.switchLetter(e1.getKey(), e2.getKey(), alreadyAllocated);
					toReplace.remove(e1.getKey());
					toReplace.remove(e2.getKey());
				}
			}
		}	
		return alreadyAllocated;
	}
	
	private Set<Letter> switchLetter(Letter l1, Letter l2, Set<Letter> alreadyAllocated) {
		alreadyAllocated.removeAll(Set.of(l1, l2));
		alreadyAllocated.add(new LetterImpl(l1.getPosition(), l2.getContent()));
		alreadyAllocated.add(new LetterImpl(l2.getPosition(), l1.getContent()));
		return alreadyAllocated;
	}
	
	private Set<Letter> checkGrid(Set<Letter> alreadyAllocated) {
		Map<Letter, Range> toReplace = new HashMap<>();
		IntStream.range(0, this.shape).forEach(i -> toReplace.putAll(this.getLettersToReplace(this.getRow(alreadyAllocated, i))));
		IntStream.range(0, this.shape).forEach(j -> toReplace.putAll(this.getLettersToReplace(this.getColumn(alreadyAllocated, j))));
		return this.replaceLetter(toReplace, alreadyAllocated);
	}
	
	

}
