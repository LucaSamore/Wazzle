/**
 *  //TODO Scrivere la documentazione
 */
package wazzle.model.maingame;

import java.util.Map;
import java.util.Set;

public class ScoreAdapterImpl implements ScoreAdapter {

	private Frequency wa;

	public ScoreAdapterImpl(Set<String> words) {
		wa = new FrequencyImpl();
	}

	@Override
	public Map<Character, Double> getWeightedAlphabet() {
		return convertFrequencyToScore(this.wa.getMappedWeightedAlphabet());
	}

	private Map<Character, Double> convertFrequencyToScore(Map<Character, Double> weightedAlphabet) {
		weightedAlphabet.entrySet().forEach((e) -> {

			if (e.getValue() >= 0.05) {
				e.setValue(1.0);
			} else if (e.getValue() >= 0.04) {
				e.setValue(2.0);
			} else if (e.getValue() >= 0.03) {
				e.setValue(3.0);
			} else if (e.getValue() >= 0.02) {
				e.setValue(4.0);
			} else if (e.getValue() <= 0.02) {
				e.setValue(5.0);
			}

		});
		return weightedAlphabet;

	}

}
