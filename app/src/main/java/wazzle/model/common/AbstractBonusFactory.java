package wazzle.model.common;

/**
 * This interface provides methods for implementing the {@link ScoreBonus}, {@link TimeBonus} 
 * and {@link WordBonus}.
 *
 */
public interface AbstractBonusFactory {
	
	/**
	 * Gives a new {@link ScoreBonus} object.
	 * 
	 * @return ScoreBonus a new {@link ScoreBonus} object.
	 */
	ScoreBonus createScoreBonus();
	
	/**
	 * Gives a new {@link TimeBonus} object.
	 * 
	 * @return TimeBonus a new {@link TimeBonus} object.
	 */
	TimeBonus createTimeBonus();
	
	/**
	 * Gives a new {@link WordBonus} object.
	 * 
	 * @return WordBonus a new {@link WordBonus} object.
	 */
	WordBonus createWordBonus();

}
