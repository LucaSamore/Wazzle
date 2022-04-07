package wazzle.model.maingame;

import java.util.Set;

public interface MainGame {
	boolean attempt(final String word);
	
	void useScoreBonus();
	
	Set<String> useWordBonus();

	long useTimeBonus(final long currentTime);
	
	boolean needHelp();
	
	boolean areWeDone();
	
	int getFailedAttempts();
	
	double getCurrentScore();
	
	Set<Letter> getLettersInGrid();
	
	Set<String> getWordsFound();
	
	Set<String> getWordsCanBeFound();
}
