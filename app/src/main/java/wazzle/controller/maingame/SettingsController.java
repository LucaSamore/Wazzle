package wazzle.controller.maingame;

import java.util.Map;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;

public interface SettingsController {

		
	Map<String, Map<Pair<Integer, Integer>, Difficulty>> getAllDifficulties();

	Pair<Integer, Integer> getCurrentGridShape();

	Difficulty getCurrentDifficulty();
	
	void updateSettings(final Difficulty difficulty, final Pair<Integer, Integer> gridShape);
	
	void updateCurrentDifficulty(final Difficulty difficulty);
	
	void updateCurrentGridShape(Pair<Integer, Integer> gridShape);


} 
