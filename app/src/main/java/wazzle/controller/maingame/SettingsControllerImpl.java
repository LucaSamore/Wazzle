package wazzle.controller.maingame;

import java.util.Map;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;

public class SettingsControllerImpl implements SettingsController {
	
	private Settings settings;
	
	public SettingsControllerImpl() {
		this.settings = new SettingsImpl();
	}

	@Override
	public Difficulty getCurrentDifficulty() {
		return this.settings.getCurrentDifficulty();
	}

	@Override
	public Pair<Integer, Integer> getCurrentGridShape() {
		return this.settings.getCurrentGridShape();
	} 

	@Override
	public Map<String, Map<Pair<Integer, Integer>, Difficulty>> getAllDifficulties() {
		return this.settings.getAllDifficulties();
	} 
	
	@Override
	public void updateSettings(Difficulty difficulty, Pair<Integer, Integer> gridShape) {
		this.settings.updateCurrentDifficulty(difficulty);
		this.settings.updateCurrentGridShape(gridShape);
	}

	@Override
	public void updateCurrentDifficulty(Difficulty difficulty) {
		this.settings.updateCurrentDifficulty(difficulty);
	}

	@Override
	public void updateCurrentGridShape(Pair<Integer, Integer> gridShape) {
		this.settings.updateCurrentGridShape(gridShape);
	}
	

}
