/**
 *  //TODO Scrivere la documentazione
 */
package wazzle.model.maingame;

import java.util.Map;

public class WeightToPercentageAdapterImpl implements WeightToPercentageAdapter {

	private WeightedAlphabet wa;

	public WeightToPercentageAdapterImpl(Map<Character, Long> frequency, Long totalLetters) {
		
		wa = new WeightedAlphabetImpl(frequency, totalLetters);
}

	@Override
	public Map<Character, Double> getWeightedAlphabet() {
		return convertFrequencyToPercentage(this.wa.getWeightedAlphabet());
	}

	private Map<Character, Double> convertFrequencyToPercentage(Map<Character, Double> weightedAlphabet) {
		weightedAlphabet.replaceAll((k,v)-> v*100);
		return weightedAlphabet;

	}

}
