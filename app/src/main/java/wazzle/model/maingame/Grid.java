package wazzle.model.maingame;

import java.util.Set;

public interface Grid {
	Set<Letter> getLetters();
	
	Set<String> getWordsCanBeFound();
	
	Double getTotalScore();
}
