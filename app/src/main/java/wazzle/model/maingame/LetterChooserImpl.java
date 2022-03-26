package wazzle.model.maingame;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.util.Pair;

public final class LetterChooserImpl implements LetterChooser {
	
	private EnumMap<Range, WeightedAlphabet> classifiedLetters;
	private Pair<Integer, Integer> gridShape;
	/**
	 * Is a value which allows to round the operation between Integer numbers
	 */
	private static final double CONSTANCE_ROUND = 0.5;
	private final Function<Map<Character, Double>, Set<Pair<Character, Double>>> f = (m) -> m.entrySet().stream()
																									.map(x -> new Pair<>(x.getKey(), x.getValue()))
																									.collect(Collectors.toSet());
	
	/**
	 * Construct a new Letter chooser. 
	 * 
	 * @param classifiedLetters The Characters with their frequency, classified in Ranges, which have to be choosen.
	 * @param gridShape The grid dimension.
	 */
	public LetterChooserImpl (final EnumMap<Range, WeightedAlphabet> classifiedLetters, final Pair<Integer, Integer> gridShape) {
		this.classifiedLetters = new EnumMap<>(classifiedLetters);
		this.gridShape = new Pair<Integer, Integer>(gridShape.getKey(), gridShape.getValue());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public EnumMap<Range, List<Pair<Character, Double>>> choose() {
		EnumMap<Range, List<Pair<Character, Double>>> result = new EnumMap<>(Range.class);
		Stream.of(Range.values())
			  .forEach(r -> result.put(r, this.extractLettersFromRange(this.getTotalLetterFromRange(r), 
					  f.apply(this.classifiedLetters.get(r).getWeightedAlphabet()))));
		return result;
	}
		
	/**
	 * Get the number of letter which have to be present in a grid, proportionally to the Range weight.
	 * 
	 * @param range The Range on which the compute is done.
	 * @return int which represent the number of letter of the given range have to be present in the grid.
	 */
	private int getTotalLetterFromRange(final Range range) {
		double allotmentIndex = ((double) this.gridShape.getKey()*this.gridShape.getValue())/(double)
				Stream.of(Range.values())
					  .map(Range::getWeight)
					  .reduce(0, (x, y) -> x + y);
		return (int) ((double) allotmentIndex*range.getWeight() + CONSTANCE_ROUND);
	}
	
	/**
	 * Extract randomly a given number of letter from a given pool.
	 * 
	 * @param numberOfExtraction How many extractions have to be done 
	 * @param pool The Characters and their score to extract from
	 * @return List<Pair<>> which contains the result the extractions
	 */
	private List<Pair<Character, Double>> extractLettersFromRange (final int numberOfExtraction, final Set<Pair<Character, Double>> pool) {
		var random = new Random();
		var listPool = pool.stream().collect(Collectors.toList());
		return IntStream.range(0, numberOfExtraction)
				 .boxed()
				 .map(i -> listPool.get(random.nextInt(pool.size())))
				 .collect(Collectors.toList());
	}
}
