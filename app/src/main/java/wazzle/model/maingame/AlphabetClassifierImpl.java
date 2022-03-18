package wazzle.model.maingame;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import wazzle.model.common.WeightedAlphabet;
import wazzle.model.common.WeightedAlphabetImpl;

public class AlphabetClassifierImpl implements AlphabetClassifier {
	
	private WeightedLetters weightedLetters;
	
	public AlphabetClassifierImpl (WeightedLetters weightedLetters) {
		this.weightedLetters = weightedLetters;
	}
	
	public EnumMap<Range, WeightedLetters> classify() {
		EnumMap <Range, Optional<Map<String, WeightedAlphabet>>> classifiedLetters;
		List<Range> ranges = new LinkedList<>(Arrays.asList(Range.values()));
		Double limit = this.weightedLetters.getWeightedAlphabet().stream().foreach(z, y).reduce(0, (x, y) -> x + y);
		//per ogni range
		for (var r: ranges) {
			//per ogni elemento weighted.... ordinato
			for (var a: Collections.sort(this.weightedLetters.getMappedWeightedAlphabet().entrySet().stream().collect.toList())) {
				totalWeight = this.weightedLetters.getWeightedAlphabet().foreach(x).reduce(0, (x, y) -> x + y);
				if (classifiedLetters.get(r).getMappedWeightedAlphabet().valueSet().sum() <= classifiedLetters.get(r).getMappedWeightedAlphabet().valueSet().sum()/Range.values().length) {
					classifiedLetters.put(r, a);
				}
			}
		}
		return classifiedLetters;
	}

}
