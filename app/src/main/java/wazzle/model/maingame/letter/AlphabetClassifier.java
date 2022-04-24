package wazzle.model.maingame.letter;

import java.util.EnumMap;

import wazzle.model.maingame.alphabet.WeightedAlphabet;

/**
 * This interface provides a method for classify a {@link WeightedAlphabet} in {@link Ranges}.
 *
 */
public interface AlphabetClassifier {
	
	/**
	 * This method returns the {@link WeightedAlphabet} classified in {@link Ranges}. 
	 * The classification in Ranges depends on the frequency of every Character contained in the WeightedAlphabet.
	 * 
	 * @return 	EnumMap<Range, WeightedAlphabet> 	An EnumMap which store all the {@link Ranges}. Every 
	 * 												Range is associated with the {@link WeightedAlphabet} 
	 *												which contains all the Characters which have a similar 
	 *												frequency.
	 */
	public EnumMap<Range, WeightedAlphabet> classify();

}
