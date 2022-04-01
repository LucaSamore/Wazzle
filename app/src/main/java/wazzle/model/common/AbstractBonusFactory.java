package wazzle.model.common;

public interface AbstractBonusFactory {
	
	ScoreBonus createScoreBonus();
	
	TimeBonus createTimeBonus();
	
	WordBonus createWordBonus();

}
