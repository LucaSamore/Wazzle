package wazzle.model.maingame;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.UnaryOperator;

import wazzle.controller.common.FileController;

public interface MainGame {
	boolean tryWord(final String word);
	
	Set<String> wordsFound();
	
	Set<String> wordsToBeFound();
	
	Set<String> wordsCanBeFound();
	
	Set<Letter> lettersInGrid();
	
	Grid getGrid();
	
	LocalDateTime getDateTime();
	
	int getFailedAttempts();
	
	void setCurrentScore(final double newScore);
	
	double getCurrentScore();
}
