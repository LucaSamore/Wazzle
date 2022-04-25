package wazzle.controller.maingame;

import java.util.List;
import java.util.Optional;

import wazzle.model.maingame.grid.Difficulty;

public interface SettingsController {

	/**
	 * Set the current difficulty.
	 * 
	 * @see wazzle.model.maingame.grid.Difficulty
	 * @param difficulty the difficulty to set.
	 */
	void setCurrentDifficulty(Difficulty difficulty);
	
	/**
	 * Returns a list containing all difficulties.
	 * 
	 * @see wazzle.model.maingame.grid.Difficulty
	 * @return List<Difficulty> the List containing all the difficulties
	 */
	List<Difficulty> getAllDifficulties();

	/**
	 * Returns the current set difficulty.
	 * 
	 * @see wazzle.model.maingame.grid.Difficulty
	 * @return Difficulty the current set difficulty
	 */
	Difficulty getCurrentDifficulty();	
	
	/**
	 * Given the name of the difficulty and it's shape, returns the corresponding Difficulty.
	 * 
	 * @param name
	 * @param shape
	 * @return the selected difficulty
	 */
	Optional<Difficulty> getDifficultyByNameAndShape(String name, int shape);
}