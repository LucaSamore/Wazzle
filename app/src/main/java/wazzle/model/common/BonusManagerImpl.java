package wazzle.model.common;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.UnaryOperator;

import com.google.gson.annotations.Expose;

public class BonusManagerImpl implements BonusManager {
	
	private static final Random random = new Random();
	
	@Expose
	private ScoreBonus scoreBonus;
	
	@Expose
	private WordBonus wordBonus;
	
	@Expose
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
	public int applyScoreBonus(final int currentScore, final int gridTotalScore) {
		this.updateScoreBonusQuantity(b -> b - 1);
		return this.scoreBonus.apply(currentScore, gridTotalScore);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Set<String> applyWordBonus(final Set<String> toFoundWords) {
		this.updateWordBonusQuantity(b -> b - 1);
		return this.wordBonus.apply(toFoundWords);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public long applyTimeBonus(final long currentTime) {
		this.updateTimeBonusQuantity(b -> b - 1);
		return this.timeBonus.apply(currentTime);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void extractBonus() {
		var extractedBonus = this.extracter().getClass();
		if (this.scoreBonus.getClass().equals(extractedBonus)) {
			this.updateScoreBonusQuantity(q -> q+1);
		}
		if (this.wordBonus.getClass().equals(extractedBonus)) {
			this.updateWordBonusQuantity(q -> q+1);
		}
		if (this.timeBonus.getClass().equals(extractedBonus)) {
			this.updateTimeBonusQuantity(q -> q+1);
		}
	}
	
	/**
	 * Extract randomly a bonus.
	 * 
	 * @return AbstractBonus extracted.
	 */
	private AbstractBonus extracter() {
		List<AbstractBonus> bonuses = List.of(this.bonusFactory.createScoreBonus(), 
											  this.bonusFactory.createWordBonus(),
											  this.bonusFactory.createTimeBonus());
		return bonuses.get(random.nextInt(bonuses.size()));
	}

}
