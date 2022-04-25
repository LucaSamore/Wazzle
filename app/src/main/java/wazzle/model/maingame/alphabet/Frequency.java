package wazzle.model.maingame.alphabet;

/**
 * An interface for compute the frequency of letters inside a given dataset
 */
public interface Frequency {
    /**
     * 
     * This method transform a Set of Strings into a Map, having as Key values all
     * the letters present in the Set and as Values the number of occurrences of
     * the associated key divided by the total number of letters (Relative Frequency),
     * then it returns a new Object that stores it.
     *
     *
     * @return WeightedAlphabet		An Object storing a Map with the letters present in the
     *                              given set as keys and the relative number of occurrences
     *                              as values.
     * @see WeightedAlphabet
     * 
     */
	WeightedAlphabet computeFrequency();
}
