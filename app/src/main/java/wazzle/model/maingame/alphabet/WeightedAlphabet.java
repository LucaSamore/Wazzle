package wazzle.model.maingame.alphabet;

import java.util.Map;

public interface WeightedAlphabet {
    /**
     * This method returns the field weightedLetterMap.
     * 
     * @return  weightedLetterMap   A Map holding all the letter present in the input
     *                              Map as keys and the relative value of all letters present in the given map
     *                              compared to the total number of letters present as values.
     * 
     */
	Map<Character, Double> getWeightedAlphabet();
}
