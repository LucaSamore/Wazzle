package wazzle.model.maingame;

import java.util.ArrayList;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.util.Pair;

public final class LetterAllocatorImpl implements LetterAllocator {
	
	private EnumMap<Range, List<Pair<Character, Double>>> choosenLetters;
	private int gridSize;
	private int shape;
	private static final Random RANDOM = new Random();
	
	
	public LetterAllocatorImpl(final EnumMap<Range, List<Pair<Character, Double>>> choosenLetters) {
		Objects.requireNonNull(choosenLetters);
		this.choosenLetters = new EnumMap<Range, List<Pair<Character, Double>>>(choosenLetters);
		this.gridSize = this.getGridSize();
		this.shape = this.getShape();
	}
	
	private int getGridSize() {
		return this.choosenLetters.values().stream()
		  .map(l -> l.size())
		  .reduce(0, (x, y) -> x + y);
	}
	
	private int getShape() {
		return (int) Math.sqrt(this.gridSize);
	}
	
	@Override
	public Set<Letter> alloc() {
		Set<Pair<Integer, Integer>> grid = new HashSet<>();
		
		//Fill grid with position from 0,0 to gridShape-1,gridShape-1
		IntStream.range(-1, this.shape-1)
				 .boxed()
				 .forEach(i -> IntStream.range(-1, this.shape-1)
								   		.boxed()
								   		.forEach(j -> grid.add(new Pair<>(i+1, j+1))));
		
		List<Pair<Character, Double>> toAllocLetters = new ArrayList<>();
		Set<Letter> allocatedLetters;
		
		do {
			toAllocLetters.clear();
			//Insert choosenLetters EnumMap<Range, List<Pair<Character, Double>>> 
			//Into a List<Pair<Character. Double>> toAllocLetters
			this.choosenLetters.values()
							   .stream()
							   .forEach(c -> toAllocLetters.addAll(c));
			
			//Extract random letter - taken toAllocLetter and placed in a grid
			allocatedLetters = this.extractLetters(toAllocLetters, grid);
			//Check grid until it have a correct structure
		} while (!this.checkGrid(allocatedLetters));
		
		
		return allocatedLetters;
	}
	
	//For debug only
	public boolean checkGridForTests (final Set<Letter> alreadyAllocated){
		return this.checkGrid(alreadyAllocated);
	}
	
	//Compute how many letter of every Range have to stay in a row/column 
	//The number of letter for range is defined by a range Pair<Integer, Integer> - minimum and maximum number of letter for range
	private EnumMap<Range, Pair<Integer, Integer>> getNumberOfLetterForShape() {
		final EnumMap<Range, Pair<Integer, Integer>> rangeForRange = new EnumMap<>(Range.class);
		this.choosenLetters.entrySet().forEach(e -> {
			int numberOfLettersForRange = getChoosenLetterListSize(e.getKey());
			
			rangeForRange.put(e.getKey(), new Pair<>(
					numberOfLettersForRange / this.shape - 1 < 0 
					? 0
					: numberOfLettersForRange / this.shape - 1, 
					numberOfLettersForRange / this.shape + 1));
		});
		System.out.println(rangeForRange);
		return rangeForRange;
	}
	
	//Returns the size of the list of Character contained in a Range
	private int getChoosenLetterListSize(final Range r) {
		return this.choosenLetters.get(r).size();
	}
	
	//Extract random letter for every position of the grid
	private Set<Letter> extractLetters(List<Pair<Character, Double>> toAllocLetters, Set<Pair<Integer, Integer>> grid) {
		Set<Letter> allocatedLetters = new HashSet<>();
		int extraction = 0;
		for(var p: grid) {
			//The last 2 letter aren't taken casually, to avoid bugs
			if (toAllocLetters.size() > 1) {
				extraction = RANDOM.nextInt(toAllocLetters.size()-1);
				allocatedLetters.add(new LetterImpl(p, toAllocLetters.get(extraction).getKey(), toAllocLetters.get(extraction).getValue()));
				toAllocLetters.remove(extraction);
			}
			else {
				allocatedLetters.add(new LetterImpl(p, toAllocLetters.get(0).getKey(), toAllocLetters.get(0).getValue()));
				toAllocLetters.remove(0);
			}	
		}
		
		return allocatedLetters;
	}
	
	//Returns the letter allocated in the given row
	private Set<Letter> getRow(final Set<Letter> alreadyAllocated, final int row) {
		System.out.println("row " + row + alreadyAllocated.stream().filter(l -> l.getPosition().getKey() == row).collect(Collectors.toSet()));
		return alreadyAllocated.stream()
							   .filter(l -> l.getPosition().getKey() == row)
							   .collect(Collectors.toSet());
	}
	
	//Returns the letter allocated in the given column
	private Set<Letter> getColumn(final Set<Letter> alreadyAllocated, final int column) {
		System.out.println("column " + column + alreadyAllocated.stream().filter(l -> l.getPosition().getValue() == column).collect(Collectors.toSet()));
		return alreadyAllocated.stream()
							   .filter(l -> l.getPosition().getValue() == column)
							   .collect(Collectors.toSet());
	}
	
	//Returns the Range which the given Character belongs to
	private Optional<Range> getLetterRange(Character c) {
		Optional<Range> r = Optional.empty();
		for (var e: this.choosenLetters.entrySet()) {
			Optional<Pair<Character, Double>> pair = e.getValue().stream().filter(p -> p.getKey().equals(c)).findFirst();
			if (!pair.equals(Optional.empty())) {
				r = Optional.of(e.getKey());
				return r;
			}
		}
//		//TODO: TO BE REFACTORED 
		return r;
	}
		
	
	//Returns an EnumMap<Range, Integer> which contains the number of letters for every range 
	//contained in the given Set<Letter>
	private EnumMap<Range, Integer> getAttendency(final Set<Letter> letters) {
		final EnumMap<Range, Integer> attendency = new EnumMap<>(Range.class);
		letters.forEach(l -> attendency.put(this.getLetterRange(l.getContent()).get(), 
				attendency.containsKey(this.getLetterRange(l.getContent()).get())
				? attendency.get(this.getLetterRange(l.getContent()).get()) + 1
				: 1));
		
		System.out.println(attendency);
		return attendency;
	}
	 
	//Returns the letter which have to be replaced because of those exceed the maximum threshold of letter for range in a row/column
	private boolean isTheLetterSetCorrect(final Set<Letter> letters) {
		final EnumMap<Range, Integer> attendency = this.getAttendency(letters);
		final EnumMap<Range, Pair<Integer, Integer>> rangeForRange = this.getNumberOfLetterForShape();
		
		boolean result = true;
		
		for(var e: attendency.entrySet()) {
			result = Optional.of(e.getValue())
							 .filter(i -> i < rangeForRange.get(e.getKey()).getKey() || 
									 	  i > rangeForRange.get(e.getKey()).getValue())
							 .isPresent() ? false : result;		
		}
		return result;
	}

	//Returns the updated set of letter
	//After checking for every rows and columns if there are letters in a wrong position
	//and if there are any of them, it change their position
	private boolean checkGrid(final Set<Letter> alreadyAllocated) {
		boolean checkRows = true;
		
		for (int i=0; i<this.shape; i++) {
			checkRows = this.isTheLetterSetCorrect(this.getRow(alreadyAllocated, i)) ? checkRows : false;
			System.out.println("risultato del check row: " + this.isTheLetterSetCorrect(this.getRow(alreadyAllocated, i)) + System.lineSeparator());
		}
		System.out.println("FINALE RIGHE: " + checkRows);
		boolean checkColumns = true;
		
		for (int j=0; j<this.shape; j++) {
			checkColumns = this.isTheLetterSetCorrect(this.getColumn(alreadyAllocated, j)) ? checkColumns : false;
			System.out.println("risultato del check columns: " + this.isTheLetterSetCorrect(this.getColumn(alreadyAllocated, j)) + System.lineSeparator());
		}
		System.out.println("FINALE COLONNE: " + checkColumns);
		return checkRows && checkColumns;
	}
}
