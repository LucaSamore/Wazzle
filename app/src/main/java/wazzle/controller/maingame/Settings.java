package wazzle.controller.maingame;

import java.util.Map;

import wazzle.model.maingame.Difficulty;

public interface Settings {
	
	Difficulty getCurrentDifficulty();
	
	int getCurrentGridShape();
	
	Map<String, Map<Integer, Difficulty>> getAllDifficulties();
	
	String getCurrentDifficultyName();
	
	void updateCurrentDifficulty(Difficulty difficulty);
	
	void updateCurrentGridShape(int gridShape);

	
} 