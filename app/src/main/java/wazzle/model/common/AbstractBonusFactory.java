package wazzle.model.common;

/**
 * AbstractBonusFactory implements the creation of all the Bonuses.
 *
 */

public interface AbstractBonusFactory {
	
	/**
	 * Gives a new scoreBonus object.
	 * 
	 * @return ScoreBonus a new ScoreBonus object.
	 */
	ScoreBonus createScoreBonus();
	
	/**
	 * Gives a new TimeBonus object.
	 * 
	 * @return TimeBonus a new TimeBonus object.
	 */
	TimeBonus createTimeBonus();
	
	/**
	 * Gives a new WordBonus object.
	 * 
	 * @return WordBonus a new WordBonus object.
	 */
	WordBonus createWordBonus();

}
