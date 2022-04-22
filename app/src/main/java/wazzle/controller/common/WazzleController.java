package wazzle.controller.common;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import wazzle.controller.maingame.GameHistoryController;
import wazzle.controller.maingame.SettingsController;
import wazzle.model.common.BonusManager;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGameImpl;

public interface WazzleController {

	/**
	 * Gives the settings controller.
	 * 
	 * @return SettingsController the settings controller.
	 */
	SettingsController getSettingsController();
	
	/**
	 * Gives the settings.
	 * 
	 * @return Settings the settings.
	 */
	Difficulty getCurrentDifficulty();

	/**
	 * Gives the dataset.
	 * 
	 * @return Dataset the dataset which MainGame takes the words from.
	 * @throws IOException
	 */
	Dictionary getDataset() throws IOException;
	
	/**
	 * Gives the shortest dataset.
	 * 
	 * @return Dataset the short dataset which MiniGame takes the words from.
	 * @throws IOException
	 */
	Dictionary getShortDataset() throws IOException;
	
	/**
	 * Gives the game history controller.
	 * 
	 * @return GameHistoryController the game history controller.
	 */
	GameHistoryController getGameHistoryController();
	
	/**
	 * Gives the game history.
	 * 
	 * @return List<MainGameImpl> the game history.
	 */
	List<MainGameImpl> getGameHistory();
	
	/**
	 * Gives the bonus manager.
	 * 
	 * @return BonusManager the bonus manager.
	 */
	BonusManager getBonusManager();
	
	/**
	 * Gives the last minigame saved.
	 * 
	 * @return MiniGameImpl the last MiniGame saved.
	 */
	Optional<MiniGameImpl> getLastMinigame() throws IOException;
	
	/**
	 * Gives the Facade.
	 * 
	 * @return Facade the facade.
	 */
	Facade getFacade();
	
	/**
	 * Extract and add a bonus gained in the MiniGame, using a BonusManager method.
	 */
	String gainBonus();
	
	/**
	 * Add a MainGame to game history.
	 * 
	 * @param mainGame The MainGame to be inserted.
	 */
	void addMainGametoHistory(final MainGame mainGame);
	
	/**
	 * Update Settings.
	 * 
	 * @param Settings The settings.
	 */
	void updateSettings(Difficulty difficulty);	
	/**
	 * Save settings to file.
	 * 
	 * @throws IOException 
	 */
	void saveSettings() throws IOException;
	
	/**
	 * Save game history to file.
	 * 
	 * @throws IOException 
	 */
	void saveGameHistory() throws IOException;
	
	/**
	 * Save bonuses to file.
	 * 
	 * @throws IOException 
	 */
	void saveBonuses() throws IOException;
	
	/**
	 * Save minigame to file.
	 * 
	 * @param minigame the minigame which have to been saved.
	 * @throws IOException
	 */
	void saveMiniGame(MiniGame minigame) throws IOException;
	
	/**
	 * Delete the last saved minigame.
	 * 
	 * @throws IOException
	 */
	void deleteEndedMiniGame() throws IOException;
	
	/**
	 * Gives this Wazzle controller.
	 * 
	 * @return WazzleController this Wazzle controller.
	 */
	WazzleController getThis();
}
