package wazzle.controller.maingame;

import java.util.Map;
import wazzle.model.maingame.Difficulty;

public class SettingsControllerImpl implements SettingsController {
	
	private Settings settings;
	
	public SettingsControllerImpl(Settings settings) {
		this.settings = settings;
	}
	
	@Override
	public Map<String, Map<Integer, Difficulty>> getAllDifficulties() {
		return this.settings.getAllDifficulties();
	} 

	@Override
	public Settings getCurrentSettings() {
		return this.settings;
	}
	
	@Override
	public Difficulty getCurrentDifficulty() {
		return this.settings.getCurrentDifficulty();
	}

	@Override
	public int getCurrentGridShape() {
		return this.settings.getCurrentGridShape();
	} 
	
	@Override
	public void updateSettings(Difficulty difficulty, int gridShape) {
		this.settings.updateCurrentDifficulty(difficulty);
		this.settings.updateCurrentGridShape(gridShape);
	}

	@Override
	public void updateCurrentDifficulty(Difficulty difficulty) {
		this.settings.updateCurrentDifficulty(difficulty);
	}

	@Override
	public void updateCurrentGridShape(int gridShape) {
		this.settings.updateCurrentGridShape(gridShape);
	}
}
