package wazzle.model.common;

import java.util.Set;
import java.util.function.UnaryOperator;

public class BonusManagerImpl implements BonusManager {
	
	private ScoreBonus scoreBonus;
	private WordBonus wordBonus;
	private TimeBonus timeBonus;
	private AbstractBonusFactory bonusFactory;
	
	public BonusManagerImpl() {
		this.bonusFactory = new BonusFactoryImpl();
		this.scoreBonus = this.bonusFactory.createScoreBonus();
		this.wordBonus = this.bonusFactory.createWordBonus();
		this.timeBonus = this.bonusFactory.createTimeBonus();
	}
	
	public void updateScoreBonusQuantity(final UnaryOperator<Integer> operation) {
		this.scoreBonus.updateQuantity(operation);
	}
	
	public void updateWordBonusQuantity(final UnaryOperator<Integer> operation) {
		this.wordBonus.updateQuantity(operation);
	}
	
	public void updateTimeBonusQuantity(final UnaryOperator<Integer> operation) {
		this.timeBonus.updateQuantity(operation);
	}
	
	public void applyScoreBonus(final double currentScore, final double gridTotalScore) {
		this.scoreBonus.apply(currentScore, gridTotalScore);
	}
	
	public void applyWordBonus(final Set<String> toFoundWords) {
		this.wordBonus.apply(toFoundWords);
	}
	
	public void applyTimeBonus(final long currentTime) {
		this.timeBonus.apply(currentTime);
	}

}
