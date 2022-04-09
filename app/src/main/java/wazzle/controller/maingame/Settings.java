package wazzle.controller.maingame;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;

public interface Settings {
	
	Difficulty getDifficulty();
	
	Pair<Integer, Integer> getGridShape();
	
	void updateDifficulty(Difficulty difficulty);
	
	void updateGridShape(Pair<Integer, Integer> gridShape);

} 
