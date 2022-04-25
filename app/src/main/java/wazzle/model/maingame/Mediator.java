package wazzle.model.maingame;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.alphabet.Frequency;
import wazzle.model.maingame.alphabet.FrequencyImpl;
import wazzle.model.maingame.alphabet.WeightedAlphabet;
import wazzle.model.maingame.grid.Difficulty;
import wazzle.model.maingame.grid.Grid;
import wazzle.model.maingame.grid.GridGenerator;
import wazzle.model.maingame.letter.AlphabetClassifier;
import wazzle.model.maingame.letter.AlphabetClassifierImpl;
import wazzle.model.maingame.letter.Letter;
import wazzle.model.maingame.letter.LetterAllocator;
import wazzle.model.maingame.letter.LetterAllocatorImpl;
import wazzle.model.maingame.letter.LetterChooser;
import wazzle.model.maingame.letter.LetterChooserImpl;
import wazzle.model.maingame.letter.Range;
import wazzle.model.maingame.letter.score.ScoreConverter;
import wazzle.model.maingame.letter.score.ScoreConverterImpl;

/**
 * The purpose of this class is to provide an interface (sort of a Facade) for {@link GridGenerator}.
 * {@code Mediator} separates the process of getting a certain number of {@link Letter} objects
 * from the creation of {@link Grid} object.
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

	/**
	 * Construct a new Mediator object
	 * @param dataset a {@link Dictionary} object describing the dataset we want to use for the future {@link Grid}
	 * @param gridShape a {@code Pair<Integer,Integer>} describing the shape of the future {@link Grid}
	 * @see javafx.util.Pair
	 */
	public Mediator(final Dictionary dataset, final Pair<Integer,Integer> gridShape) {
		Objects.requireNonNull(dataset);
		Objects.requireNonNull(gridShape);
		this.gridShape = gridShape;
		this.classifiedLetters = new EnumMap<>(Range.class);
		this.letters = Optional.empty();
		this.initialize(dataset);
	}

	/**
	 * Starts the process of getting ({@link Difficulty#getGridShape()} x {@link Difficulty#getGridShape()}) 
	 * {@link Letter} objects by calling {@link LetterChooser}.
	 * @return an {@code Optional} describing the letters computed.
	 */
	public Optional<Set<Letter>> computeLetters() {
		this.chooser.choose();
		return this.letters;
	}
	
	/**
	 * Given the response from {@link LetterChooser}, allocates the given letters by calling {@link LetterAllocator} 
	 * @param chosenLetters an {@code EnumMap<Range,List<Pair<Character,Double>>>} describing the letters produced by {@link LetterChooser}
	 * @return void
	 * @see java.util.EnumMap
	 * @see wazzle.model.maingame.letter.Range
	 * @see java.util.List
	 * @see javafx.util.Pair
	 */
	public void notifyFromChooser(final EnumMap<Range,List<Pair<Character,Double>>> chosenLetters) {
		this.allocator = new LetterAllocatorImpl(this.unify(chosenLetters), this);
		this.allocator.allocate();
	}

	/**
	 * Given the response from {@link LetterAllocator}, fill the computed letters {@code Optional}.
	 * @param letters a {@code Set<Letter>} describing the letters allocated by {@link LetterAllocator}
	 * @return void
	 * @see java.util.Set
	 */
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
		final var chosenLettersList = new LinkedList<Pair<Character, Double>>();
		chosenLetters.entrySet().forEach(e -> chosenLettersList.addAll(e.getValue()));
		final var chosenScoredLetters = this.getChosenScoredLetters(chosenLettersList); //Filter scored letters for chosen letters
		final var chosenScoredLettersMap = new EnumMap<Range, List<Pair<Character, Double>>>(Range.class);
		
		chosenLetters.entrySet().forEach(e -> chosenScoredLettersMap.put(e.getKey(), chosenScoredLetters
				.stream()
				.filter(p1 -> e.getValue()
						.stream()
						.map(Pair::getKey)
						.collect(Collectors.toList())
						.contains(p1.getKey()))
				.collect(Collectors.toList())));
		
		return chosenScoredLettersMap;
	}
	
	private List<Pair<Character, Double>> getChosenScoredLetters(final List<Pair<Character,Double>> chosenLettersList) {
		return chosenLettersList
				.stream()
				.map(p -> new Pair<>(p.getKey(),
						this.scoreConverter.convert()
							.getWeightedAlphabet()
							.entrySet()
							.stream()
							.filter(e -> e.getKey() == p.getKey())
							.findAny()
							.get()
							.getValue()))
				.collect(Collectors.toList());
	}
}
