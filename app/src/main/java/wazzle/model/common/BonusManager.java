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
	 * Apply the implications of the ScoreBonus.
	 * 
	 * @param currentScore the current score obtained by the user.
	 * @param gridTotalScore the sum of all letter scores in the grid.
	 */
	void applyScoreBonus(final double currentScore, final double gridTotalScore);
	
	/**
	 * Apply the implications of the WordBonus.
	 * 
	 * @param toFoundWords the words that the user haven't already found.
	 */
	void applyWordBonus(final Set<String> toFoundWords);
	
	/**
	 * Apply the implications of the TimeBonus.
	 * 
	 * @param currentTime the current time remaining to the user to found other words.
	 */
	void applyTimeBonus(final long currentTime);

}
