package wazzle.controller.maingame;

import java.util.Map;
import wazzle.model.maingame.Difficulty;

public interface SettingsController {

		
	Map<String, Map<Integer, Difficulty>> getAllDifficulties();
	
	Settings getCurrentSettings();

	int getCurrentGridShape();

	Difficulty getCurrentDifficulty();
	
	void updateSettings(final Difficulty difficulty, final int gridShape);
	
	void updateCurrentDifficulty(final Difficulty difficulty);
	
	void updateCurrentGridShape(int gridShape);


} 