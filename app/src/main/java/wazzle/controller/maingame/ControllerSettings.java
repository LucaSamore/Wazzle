package wazzle.controller.maingame;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;

public interface ControllerSettings {

	Difficulty getDifficulty();
	
	Pair<Integer, Integer> getGridShape();
	
	void updateSettings(final Difficulty difficulty, final Pair<Integer, Integer> gridShape);
	
	void updateDifficulty(final Difficulty difficulty);
	
	void updateGridShape(final Pair<Integer, Integer> gridShape);
} 
