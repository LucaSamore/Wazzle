package wazzle.model.maingame;

import java.util.Set;

public interface MainGame {
	boolean attempt(final String word);
	
	boolean needHelp();
	
	boolean areWeDone();
	
	int getFailedAttempts();
	
	Set<Letter> getLettersInGrid();
	
	Set<String> getWordsFound();
	
	Set<String> getWordsCanBeFound();
}
