package wazzle.model.common;

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
