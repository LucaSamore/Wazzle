package wazzle.model.maingame;
/**
 * The Enumeration Range contains all the possible ranges which Characters or Letters could
 * belongs to. 
 *
 */
public enum Range {
	
	/**
	 * The high frequency, which have the hightest weight.
	 */
	HIGH_FREQUENCY(3),

	/**
	 * The medium frequency, which have a medium weight.
	 */
	MEDIUM_FREQUENCY(2),

	/**
	 * The low frequency, which have the lowest weight.
	 */
	LOW_FREQUENCY(1);

	int weight;

	/**
	 * Contruct a Range.
	 * 
	 * @param weight is a value which depends on the importance of the Range.
	 */
	Range(int weight) {
		this.weight = weight;
	}

	/**
	 * Get a range weight.
	 * 
	 * @return int which is the weight of the Range.
	 */
	public int getWeight() {
		return this.weight;
	}
}