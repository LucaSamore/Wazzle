package wazzle.model.maingame;

/**
 * 
 * ScoreConverter convert the WeightedAlphabet into another WeightedAlphabet which contains the score.
 *
 */
public interface ScoreConverter {
	
	/**
	 * Convert the WeightedAlphabet, which initially contains all the Characters and their
	 * frequency (represented by a Double), to an object WeightedAlphabet which contains all the Characters 
	 * and their score (represented by a Double).
	 * 
	 * @return WeightedAlphabet which contains all the Characters and their score.
	 */
	WeightedAlphabet convert();

}
