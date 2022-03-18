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

public class LetterChooserImpl implements LetterChooser {
	
	private EnumMap<Range, WeightedLetters> classifiedLetters;
	private Pair<Integer, Integer> gridShape;
	private static final double CONSTANCE_ROUND = 0.5;
	private Function<Map<Character, Double>, List<Pair<Character, Double>>> f = (m) -> m.entrySet().stream()
																									.map(x -> new Pair<>(x.getKey(), x.getValue()))
																									.collect(Collectors.toList());
	
	public LetterChooserImpl (EnumMap<Range, WeightedLetters> classifiedLetters, Pair<Integer, Integer> gridShape) {
		this.classifiedLetters = classifiedLetters;
		this.gridShape = gridShape;
	}
	

	public EnumMap<Range, List<Pair<Character, Double>>> choose() {
		
		EnumMap<Range, List<Pair<Character, Double>>> result;
//		Stream.of(Range.values())
//			  .forEach(r -> this.extractLettersFromRange(this.getTotalLetterFromRange(r), 
//					  this.classifiedLetters.get(r).getWeightedletters()));
//		result.entrySet().stream()
		
	}
		
	private int getTotalLetterFromRange(Range range) {
		double allotmentIndex = (this.gridShape.getKey()*this.gridShape.getValue())/
				Stream.of(Range.values())
					  .map(Range::getWeight)
					  .reduce(0, (x, y) -> x + y);
		
		return (int) ((double) allotmentIndex*range.getWeight() + CONSTANCE_ROUND);
	}
	
	private List<Pair<Character, Double>> extractLettersFromRange (int numberOfExtraction, Set<Pair<Character, Double>> pool) {
		Random random = new Random();
		var listPool = pool.stream().collect(Collectors.toList());
		return IntStream.range(0, numberOfExtraction)
				 .boxed()
				 .map(i -> listPool.get(random.nextInt(pool.size())))
				 .collect(Collectors.toList());
	}
}
