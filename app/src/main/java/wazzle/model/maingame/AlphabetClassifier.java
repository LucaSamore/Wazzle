package wazzle.model.maingame;

import java.util.EnumMap;

public interface AlphabetClassifier {
	
	/**
	 * This method returns the WeightedAlphabet classified in Ranges. 
	 * The classification in Ranges depends on the frequency of every Character contained in the WeightedAlphabet.
	 * 
	 * @return 	EnumMap<Range, WeightedAlphabet> 	An EnumMap which store all the Ranges. Every Range is associated 
	 * 												with the WeghtedAlphabet which contains all the Characters which
	 * 												have a similar frequency. 
	 * 
	 */
	public EnumMap<Range, WeightedAlphabet> classify();

}
