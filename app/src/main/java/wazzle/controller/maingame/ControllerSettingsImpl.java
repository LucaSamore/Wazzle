package wazzle.controller.maingame;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;

public class ControllerSettingsImpl implements ControllerSettings {
	
	private Settings settings;
	
	public ControllerSettingsImpl() {
		this.settings = new SettingsImpl();
	}

	@Override
	public Difficulty getDifficulty() {
		return this.settings.getDifficulty();
	}

	@Override
	public Pair<Integer, Integer> getGridShape() {
		return this.settings.getGridShape();
	} 

	@Override
	public void updateSettings(Difficulty difficulty, Pair<Integer, Integer> gridShape) {
		this.settings.updateDifficulty(difficulty);
		this.settings.updateGridShape(gridShape);
	}

	@Override
	public void updateDifficulty(Difficulty difficulty) {
		this.settings.updateDifficulty(difficulty);
	}

	@Override
	public void updateGridShape(Pair<Integer, Integer> gridShape) {
		this.settings.updateGridShape(gridShape);
	}

}
