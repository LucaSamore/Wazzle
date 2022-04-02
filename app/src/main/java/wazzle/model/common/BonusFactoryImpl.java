package wazzle.model.common;

public class BonusFactoryImpl implements AbstractBonusFactory {

	/**
	 * {@inheritDoc}
	 */
	public ScoreBonus createScoreBonus() {
		return new ScoreBonus(BonusStrategies.scoreBonus());
	}

	/**
	 * {@inheritDoc}
	 */
	public TimeBonus createTimeBonus() {
		return new TimeBonus(BonusStrategies.timeBonus());
	}

	/**
	 * {@inheritDoc}
	 */
	public WordBonus createWordBonus() {
		return new WordBonus(BonusStrategies.wordBonus());
	}

}
