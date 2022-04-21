package wazzle.controller.maingame;

import java.util.List;
import wazzle.model.maingame.Difficulty;

public interface SettingsController {

	List<Difficulty> getAllDifficulties();

	Difficulty getCurrentDifficulty();

	void setCurrentDifficulty(final Difficulty difficulty);

}