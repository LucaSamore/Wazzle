package wazzle.model.maingame;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.util.Pair;
import wazzle.model.common.Dictionary;

/*
 * LetterChooser and LetterAllocator shall have a Mediator instance variable,
 * passed via constructor.
 * Public methods of these classes shall return void and the result will be passed to the Mediator through
 * the notifyFromXYZ() methods. All partial results will be handled in this class.
 * This class is meant to be an interface (sort of a Facade) for GridGenerator, because I want to separate
 * the process of getting N Letters from evaluating the grid quality (via the GridValidator object in GridGenerator)
*/
public final class Mediator {
	private final Pair<Integer,Integer> gridShape;
	private Frequency frequency;
	private WeightedAlphabet weightedAlphabet;
	private AlphabetClassifier classifier;
	private LetterChooser chooser;
	private ScoreConverter scoreConverter;
	private LetterAllocator allocator;
	private EnumMap<Range,WeightedAlphabet> classifiedLetters;
	private Optional<Set<Letter>> letters;

	public Mediator(final Dictionary dataset, final Pair<Integer,Integer> gridShape) {
		this.gridShape = gridShape;
		this.classifiedLetters = new EnumMap<>(Range.class);
		this.letters = Optional.empty();
		this.initialize(dataset);
	}

	public Optional<Set<Letter>> computeLetters() {
		//this.notifyFromClassifier(this.classifiedLetters);
		this.chooser.choose();
		return this.letters;
	}

	/*
	 * TODO: remember to remove this method
	*/
	public void notifyFromClassifier(final EnumMap<Range,WeightedAlphabet> classifiedLetters) {
		this.chooser.choose();
	}

	public void notifyFromChooser(final EnumMap<Range,List<Pair<Character,Double>>> chosenLetters) {
		this.allocator = new LetterAllocatorImpl(this.unify(chosenLetters), this);
		this.allocator.allocate();
	}

	public void notifyFromAllocator(final Set<Letter> letters) {
		this.letters = Optional.of(letters);
	}

	private void initialize(final Dictionary dataset) {
		this.frequency = new FrequencyImpl(dataset);
		this.weightedAlphabet = this.frequency.computeFrequency();
		this.scoreConverter = new ScoreConverterImpl(this.weightedAlphabet);
		this.classifier = new AlphabetClassifierImpl(weightedAlphabet);
		this.classifiedLetters = this.classifier.classify();
		this.chooser = new LetterChooserImpl(classifiedLetters, this.gridShape, this);
	}

	private EnumMap<Range, List<Pair<Character, Double>>> unify(final EnumMap<Range,List<Pair<Character,Double>>> chosenLetters) {
		List<Pair<Character, Double>> chosenLettersList = new LinkedList<>();
		
		chosenLetters.entrySet().forEach(e -> chosenLettersList.addAll(e.getValue()));
		
		//Filter scored letters for chosen letters
		List<Pair<Character, Double>> chosenScoredLetters = this.scoreConverter.convert()
																			   .getWeightedAlphabet()
																			   .entrySet()
																			   .stream()
																			   .filter(e -> chosenLettersList.stream()
																											 .map(p -> p.getKey()).collect(Collectors.toList())
																											  					  .contains(e.getKey()))
																			   .map(e -> new Pair<>(e.getKey(), e.getValue()))
																			   .collect(Collectors.toList());
		
		EnumMap<Range, List<Pair<Character, Double>>> chosenScoredLettersMap = new EnumMap<>(Range.class);
		
		chosenLetters.entrySet()
					 .forEach(e -> chosenScoredLettersMap.put(e.getKey(), 
							  chosenScoredLetters.stream()
							  					 .filter(p1 -> e.getValue().stream()
							  							 				   .map(p2 -> p2.getKey())
							  							 				   .collect(Collectors.toList())
							  							 				   .contains(p1.getKey()))
							  					 .collect(Collectors.toList())));
		return chosenScoredLettersMap;
	}
}
