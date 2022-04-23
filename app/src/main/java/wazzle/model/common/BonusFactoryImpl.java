package wazzle.model.common;

/**
 * This class is a concrete implementation for {@link AbstractBonusFactory}
 * It provides methods for create {@link ScoreBonus}, {@link TimeBonus} and {@link WordBonus}.
 */
public class BonusFactoryImpl implements AbstractBonusFactory {

	/**
	 * {@inheritDoc}
	 */
	public ScoreBonus createScoreBonus() {
		return new ScoreBonus(BonusStrategies.scoreBonus(), "Score Bonus");
	}

	/**
	 * {@inheritDoc}
	 */
	public TimeBonus createTimeBonus() {
		return new TimeBonus(BonusStrategies.timeBonus(), "Time Bonus");
	}

	/**
	 * {@inheritDoc}
	 */
	public WordBonus createWordBonus() {
		return new WordBonus(BonusStrategies.wordBonus(), "Word Bonus");
	}

}
