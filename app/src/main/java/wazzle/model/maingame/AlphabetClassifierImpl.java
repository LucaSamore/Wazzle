package wazzle.model.maingame;

import java.util.Arrays;


import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;


public class AlphabetClassifierImpl implements AlphabetClassifier {
	
	private WeightedAlphabet weightedAlphabet;
	
	public AlphabetClassifierImpl (WeightedAlphabet weightedAlphabet) {
		this.weightedAlphabet = new WeightedAlphabetImpl(weightedAlphabet.getWeightedAlphabet());
	}
	
	public EnumMap<Range, WeightedAlphabet> classify() {
		EnumMap <Range, WeightedAlphabet> classifiedLetters = new EnumMap<>(Range.class);
		Arrays.asList(Range.values()).stream().collect(Collectors.toSet()).forEach(r -> classifiedLetters.put(r, new WeightedAlphabetImpl(new HashMap<>())));
		List<Entry<Character, Double>> sortedWeightedAlphabet = this.sortWeightedAlphabethByValue(this.weightedAlphabet.getWeightedAlphabet().entrySet().stream().collect(Collectors.toList()));
		//problema: errore nel put a riga 30: la mappa è unmodifiable
		sortedWeightedAlphabet.stream().forEach(e -> classifiedLetters.get(this.chooseRange(classifiedLetters)).getWeightedAlphabet().put(e.getKey(), e.getValue()));
		return classifiedLetters;
	}
	
	private Range chooseRange(EnumMap <Range, WeightedAlphabet> classifiedLetters) {
		return classifiedLetters.keySet().stream().filter(k -> !this.isFill(classifiedLetters, k)).collect(Collectors.toList()).get(0);
	}
	
	private boolean isFill (EnumMap <Range, WeightedAlphabet> classifiedLetters, Range range) {
		return classifiedLetters.get(range).getWeightedAlphabet().values().stream().reduce((double) 0, Double::sum) >= this.computeRangeQuote();
	}
	
	//ok
	private double computeRangeQuote() {
		return this.weightedAlphabet.getWeightedAlphabet().values().stream().reduce((double) 0, Double::sum)/Range.values().length;
	}

	//ok
	private List<Entry<Character, Double>> sortWeightedAlphabethByValue (List<Map.Entry<Character, Double>> weightedAlphabetList) {
		Collections.sort(weightedAlphabetList, ((v1, v2) -> v2.getValue().compareTo(v1.getValue())));
		return weightedAlphabetList;
	}

}
