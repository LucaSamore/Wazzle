package wazzle.model.maingame.alphabet;

public interface Frequency {
    /**
     * 
     * This method transform a Set of Strings into a Map, having as Key values all
     * the letters present in the Set and as Values the number of occurrences of
     * the associated key divided by the total number of letters (Relative Frequency),
     * then it returns a new Object that stores it.
     *
     *
     * @return WeightedAlphabetImpl An Object storing a Map with the letters present in the
     *                              given set as keys and the relative number of occurrences
     *                              as values.
     * @see WeightedAlphabetImpl
     * 
     */
	WeightedAlphabet computeFrequency();
}
