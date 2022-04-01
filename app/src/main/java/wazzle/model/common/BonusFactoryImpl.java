package wazzle.model.common;

public class BonusFactoryImpl implements AbstractBonusFactory {

	public ScoreBonus createScoreBonus() {
		return new ScoreBonus(BonusStrategies.scoreBonus());
	}

	public TimeBonus createTimeBonus() {
		return new TimeBonus(BonusStrategies.timeBonus());
	}

	public WordBonus createWordBonus() {
		return new WordBonus(BonusStrategies.wordBonus());
	}

}
