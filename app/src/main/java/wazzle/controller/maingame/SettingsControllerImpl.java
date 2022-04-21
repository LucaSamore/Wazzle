package wazzle.controller.maingame;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import wazzle.model.maingame.Difficulty;

//TODO setcurrent difficulty to default

public class SettingsControllerImpl implements SettingsController {
	@Expose
	private List<Difficulty> difficulties;
	
	@Expose
	private Difficulty currentDifficulty;

	public SettingsControllerImpl(final List<Difficulty> difficulties) {
		this.difficulties = new ArrayList<>(difficulties);
	}

	@Override
	public List<Difficulty> getAllDifficulties() {
		return List.copyOf(this.difficulties);
	}

	@Override
	public Difficulty getCurrentDifficulty() {
		return this.currentDifficulty;
	}

	@Override
	public void setCurrentDifficulty(final Difficulty difficulty) {
		this.currentDifficulty = difficulty;
	}
}