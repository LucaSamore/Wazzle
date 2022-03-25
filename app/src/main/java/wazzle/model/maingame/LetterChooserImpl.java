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
	private static final double CONSTANCE_ROUND = 0.5;
	private final Function<Map<Character, Double>, Set<Pair<Character, Double>>> f = (m) -> m.entrySet().stream()
																									.map(x -> new Pair<>(x.getKey(), x.getValue()))
																									.collect(Collectors.toSet());
	
	public LetterChooserImpl (final EnumMap<Range, WeightedAlphabet> classifiedLetters, final Pair<Integer, Integer> gridShape) {
		this.classifiedLetters = new EnumMap<>(classifiedLetters);
		this.gridShape = new Pair<Integer, Integer>(gridShape.getKey(), gridShape.getValue());
	}
	

	public EnumMap<Range, List<Pair<Character, Double>>> choose() {
		EnumMap<Range, List<Pair<Character, Double>>> result = new EnumMap<>(Range.class);
		Stream.of(Range.values())
			  .forEach(r -> result.put(r, this.extractLettersFromRange(this.getTotalLetterFromRange(r), 
					  f.apply(this.classifiedLetters.get(r).getWeightedAlphabet()))));
		return result;
	}
		
	private int getTotalLetterFromRange(final Range range) {
		double allotmentIndex = ((double) this.gridShape.getKey()*this.gridShape.getValue())/(double)
				Stream.of(Range.values())
					  .map(Range::getWeight)
					  .reduce(0, (x, y) -> x + y);
		return (int) ((double) allotmentIndex*range.getWeight() + CONSTANCE_ROUND);
	}
	
	private List<Pair<Character, Double>> extractLettersFromRange (final int numberOfExtraction, final Set<Pair<Character, Double>> pool) {
		var random = new Random();
		var listPool = pool.stream().collect(Collectors.toList());
		return IntStream.range(0, numberOfExtraction)
				 .boxed()
				 .map(i -> listPool.get(random.nextInt(pool.size())))
				 .collect(Collectors.toList());
	}
}
