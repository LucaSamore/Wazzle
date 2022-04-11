package wazzle.controller.maingame;

import java.util.Map;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;

public interface Settings {
	
	Difficulty getCurrentDifficulty();
	
	Pair<Integer, Integer> getCurrentGridShape();
	
	Map<String, Map<Pair<Integer, Integer>, Difficulty>> getAllDifficulties();
	
	void updateCurrentDifficulty(Difficulty difficulty);
	
	void updateCurrentGridShape(Pair<Integer, Integer> gridShape);

} 