package wazzle.controller.maingame;

import java.util.List;
import java.util.Optional;

import wazzle.model.maingame.Difficulty;

public interface SettingsController {

	List<Difficulty> getAllDifficulties();

	Difficulty getCurrentDifficulty();

	void setCurrentDifficulty(final Difficulty difficulty);
	
	Optional<Difficulty> getDifficultyByNameAndShape(final String name, final int shape);
}