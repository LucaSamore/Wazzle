package wazzle.model.common;

import java.util.Set;
import java.util.function.UnaryOperator;

public class BonusManagerImpl implements BonusManager {
	
	private ScoreBonus scoreBonus;
	private WordBonus wordBonus;
	private TimeBonus timeBonus;
	private AbstractBonusFactory bonusFactory;
	
	/**
	 * Constructs a new BonusManager.
	 */
	public BonusManagerImpl() {
		this.bonusFactory = new BonusFactoryImpl();
		this.scoreBonus = this.bonusFactory.createScoreBonus();
		this.wordBonus = this.bonusFactory.createWordBonus();
		this.timeBonus = this.bonusFactory.createTimeBonus();
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateScoreBonusQuantity(final UnaryOperator<Integer> operation) {
		this.scoreBonus.updateQuantity(operation);
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void updateWordBonusQuantity(final UnaryOperator<Integer> operation) {
		this.wordBonus.updateQuantity(operation);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void updateTimeBonusQuantity(final UnaryOperator<Integer> operation) {
		this.timeBonus.updateQuantity(operation);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void applyScoreBonus(final double currentScore, final double gridTotalScore) {
		this.scoreBonus.apply(currentScore, gridTotalScore);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void applyWordBonus(final Set<String> toFoundWords) {
		this.wordBonus.apply(toFoundWords);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void applyTimeBonus(final long currentTime) {
		this.timeBonus.apply(currentTime);
	}

}
