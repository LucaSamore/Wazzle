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
	public int getScoreBonusQuantity() {
		return this.scoreBonus.getQuantity();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getWordBonusQuantity() {
		return this.wordBonus.getQuantity();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getTimeBonusQuantity() {
		return this.timeBonus.getQuantity();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public double applyScoreBonus(final double currentScore, final double gridTotalScore) {
		return this.scoreBonus.apply(currentScore, gridTotalScore);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Set<String> applyWordBonus(final Set<String> toFoundWords) {
		return this.wordBonus.apply(toFoundWords);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public long applyTimeBonus(final long currentTime) {
		return this.timeBonus.apply(currentTime);
	}

}
