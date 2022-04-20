package wazzle.model.common;

import java.util.Set;
import java.util.function.UnaryOperator;

public interface BonusManager {
	
	/**
	 * Updates the ScoreBonus quantity.
	 * 
	 * @param the operation that must be done when the ScoreBonus is used by the user.
	 */
	void updateScoreBonusQuantity(UnaryOperator<Integer> operation);
	
	/**
	 * Updates the WordBonus quantity.
	 * 
	 * @param the operation that must be done when the WordBonus is used by the user.
	 */
	void updateWordBonusQuantity(UnaryOperator<Integer> operation);
	
	/**
	 * Updates the TimeBonus quantity.
	 * 
	 * @param the operation that must be done when the TimeBonus is used by the user.
	 */
	void updateTimeBonusQuantity(UnaryOperator<Integer> operation);
	
	/**
	 * Gives the quantity of ScoreBonus.
	 * 
	 * @return int which represent the quantity of ScoreBonus.
	 */
	int getScoreBonusQuantity();
	
	/**
	 * Gives the quantity of WordBonus.
	 * 
	 * @return int which represent the quantity of WordBonus.
	 */
	int getWordBonusQuantity();
	
	/**
	 * Gives the quantity of TimeBonus.
	 * 
	 * @return int which represent the quantity of TimeBonus.
	 */
	int getTimeBonusQuantity();

	/**
	 * Apply the implications of the ScoreBonus.
	 * 
	 * @param currentScore the current score obtained by the user.
	 * @param gridTotalScore the sum of all letter scores in the grid.
	 * @return int which represents the updated score.
	 */
	int applyScoreBonus(final int currentScore, final int gridTotalScore);
	
	/**
	 * Apply the implications of the WordBonus.
	 * 
	 * @param toFoundWords the words that the user haven't already found.
	 * @return Set<String> the extracted words that will be suggested to the user.
	 */
	Set<String> applyWordBonus(final Set<String> toFoundWords);
	
	/**
	 * Apply the implications of the TimeBonus.
	 * 
	 * @param currentTime the current time remaining to the user to found other words.
	 * @return long which represent the incremented time.
	 */
	long applyTimeBonus(final long currentTime);
	
	/**
	 * Extract randomly a bonus and updates its quantity.
	 * 
	 */
	void extractBonus();

}
