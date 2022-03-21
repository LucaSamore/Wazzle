package wazzle.model.maingame;

import java.util.Arrays;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;


public class AlphabetClassifierImpl implements AlphabetClassifier {
	
	private WeightedAlphabet weightedAlphabet;
	private Function<EnumMap<Range, Map<Character, Double>>, Map<Range, WeightedAlphabet>> f = m -> m.entrySet()
	   .stream()
	   .collect(Collectors.toMap(Map.Entry::getKey, e -> new WeightedAlphabetImpl(e.getValue())));
	
	public AlphabetClassifierImpl (WeightedAlphabet weightedAlphabet) {
		this.weightedAlphabet = new WeightedAlphabetImpl(weightedAlphabet.getWeightedAlphabet());
	}
	
	public EnumMap<Range, WeightedAlphabet> classify() {
		EnumMap <Range, Map<Character, Double>> classifiedLetters = new EnumMap<>(Range.class);
		Arrays.asList(Range.values()).stream().collect(Collectors.toSet()).forEach(r -> classifiedLetters.put(r, new HashMap<>()));
		List<Entry<Character, Double>> sortedWeightedAlphabet = this.sortWeightedAlphabethByValue(this.weightedAlphabet.getWeightedAlphabet().entrySet().stream().collect(Collectors.toList()));
		sortedWeightedAlphabet.stream().forEach(e -> classifiedLetters.get(this.chooseRange(classifiedLetters)).put(e.getKey(), e.getValue()));
		return new EnumMap<>(f.apply(classifiedLetters));
	}
	
	private Range chooseRange(EnumMap <Range, Map<Character, Double>> classifiedLetters) {
		return classifiedLetters.keySet().stream().filter(k -> !this.isFill(classifiedLetters, k)).collect(Collectors.toList()).get(0);
	}
	
	private boolean isFill (EnumMap <Range, Map<Character, Double>> classifiedLetters, Range range) {
		return classifiedLetters.get(range).values().stream().reduce((double) 0, Double::sum) >= this.computeRangeQuote();
	}
	
	private double computeRangeQuote() {
		return this.weightedAlphabet.getWeightedAlphabet().values().stream().reduce((double) 0, Double::sum)/Range.values().length;
	}

	private List<Entry<Character, Double>> sortWeightedAlphabethByValue (List<Map.Entry<Character, Double>> weightedAlphabetList) {
		Collections.sort(weightedAlphabetList, ((v1, v2) -> v2.getValue().compareTo(v1.getValue())));
		return weightedAlphabetList;
	}

}
