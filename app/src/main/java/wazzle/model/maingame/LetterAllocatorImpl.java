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
	
	/**
	 * Construct a new LetterAllocator.
	 * 
	 * @param chosenLetters The List of Character with their score ad Double which have to be allocated.
	 */
	public LetterAllocatorImpl(final EnumMap<Range, List<Pair<Character, Double>>> chosenLetters) {
		Objects.requireNonNull(chosenLetters);
		this.choosenLetters = new EnumMap<Range, List<Pair<Character, Double>>>(chosenLetters);
		this.gridSize = this.getGridSize();
		this.shape = this.getShape();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Set<Letter> allocate() {
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


	/**
	 * Method created for test only which check the correctness of a given grid.
	 * 
	 * @return boolean which represents the result of the check.
	 */
	public boolean checkGridForTests (final Set<Letter> alreadyAllocated){
		return this.checkGrid(alreadyAllocated);
	}
	
	/**
	 * Compute the grid size.
	 * 
	 * @return int which represents the grid size.
	 */
	private int getGridSize() {
		return this.choosenLetters.values().stream()
		  .map(l -> l.size())
		  .reduce(0, (x, y) -> x + y);
	}
	
	/**
	 * Compute the size of every rows and column.
	 * 
	 * @return int which represent the grid shape.
	 */
	private int getShape() {
		return (int) Math.sqrt(this.gridSize);
	}
	
	/**
	 * Compute how many letter of every Range have to stay in a row/column. The number of letter 
	 * for range is defined by a range Pair<Integer, Integer> - minimum and maximum number of letter for range.
	 * 
	 * @return EnumMap<> which contains the maximum number and the minimum number of letter, as a Pair<Inetger, Integer>
	 * that have to be in every range
	 */
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
		return rangeForRange;
	}
	
	/**
	 * Gives the size of the List which is the value of a EnumMap<Range, List<Pair<Character, Double>>
	 * 
	 * @param r The range which the list belongs to.
	 * @return int which represents the size of the list associated to the given range in choosenLetters EnumMap<>
	 */
	private int getChoosenLetterListSize(final Range r) {
		return this.choosenLetters.get(r).size();
	}
	
	/**
	 * Extract random letter for every position of the grid.
	 * 
	 * @param toAllocLetters Letters which have to be allocated.
	 * @param grid The grid where the letters have to be inserted in.
	 * @return Set<Letter> which contained the result of the extraction.
	 */
	//
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
	
	/**
	 * Gives the letter located in a given row.
	 * 
	 * @param alreadyAllocated The Letters which have been already allocated.
	 * @param row The index of the row.
	 * @return Set<Letter> which contains all the letter already allocated which are located in the given row.
	 */
	private Set<Letter> getRow(final Set<Letter> alreadyAllocated, final int row) {
		return alreadyAllocated.stream()
							   .filter(l -> l.getPosition().getKey() == row)
							   .collect(Collectors.toSet());
	}
	
	/**
	 * Gives the letter located in a given column.
	 * 
	 * @param alreadyAllocated The Letters which have been already allocated.
	 * @param row The index of the column. 
	 * @return Set<Letter> which contains all the letter already allocated which are located in the given column.
	 */
	private Set<Letter> getColumn(final Set<Letter> alreadyAllocated, final int column) {
		return alreadyAllocated.stream()
							   .filter(l -> l.getPosition().getValue() == column)
							   .collect(Collectors.toSet());
	}

	/**
	 * Gives the range which the given character belongs to.
	 * 
	 * @param c The character which have to be examined.
	 * @return Optional<Range> which represent, if it exists, the Range which the Character belongs to.
	 */
	private Optional<Range> getLetterRange(Character c) {
		Optional<Range> r = Optional.empty();
		for (var e: this.choosenLetters.entrySet()) {
			Optional<Pair<Character, Double>> pair = e.getValue().stream().filter(p -> p.getKey().equals(c)).findFirst();
			if (!pair.equals(Optional.empty())) {
				r = Optional.of(e.getKey());
				return r;
			}
		}
		return r;
	}
		
	/**
	 * Gives the number of letters which belongs to every Range based on the Letters contained in the given Set.
	 * 
	 * @param letters The Set<Letter> which have to be examined.
	 * @return EnumMap<Range, Integer> which contains the number of Letters which belongs to every Range 
	 * based on the letter contained in the given Set
	 */
	private EnumMap<Range, Integer> getAttendency(final Set<Letter> letters) {
		final EnumMap<Range, Integer> attendency = new EnumMap<>(Range.class);
		letters.forEach(l -> attendency.put(this.getLetterRange(l.getContent()).get(), 
				attendency.containsKey(this.getLetterRange(l.getContent()).get())
				? attendency.get(this.getLetterRange(l.getContent()).get()) + 1
				: 1));
		return attendency;
	}
	 
	/**
	 * Check if the format of the given Set<Letter> corresponds to the format expected.
	 * 
	 * @param letters The Set<Letter> which have to be examined.
	 * @return boolean which represents the result of the check.
	 */
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

	/**
	 * Check the correctness of a given grid.
	 * 
	 * @param alreadyAllocated The Letter which have been already allocated.
	 * @return boolean which represents the result of the check.
	 */
	private boolean checkGrid(final Set<Letter> alreadyAllocated) {
		boolean checkRows = true;
		for (int i=0; i<this.shape; i++) {
			checkRows = this.isTheLetterSetCorrect(this.getRow(alreadyAllocated, i)) ? checkRows : false;
		}
		boolean checkColumns = true;
		for (int j=0; j<this.shape; j++) {
			checkColumns = this.isTheLetterSetCorrect(this.getColumn(alreadyAllocated, j)) ? checkColumns : false;
		}
		return checkRows && checkColumns;
	}
}
