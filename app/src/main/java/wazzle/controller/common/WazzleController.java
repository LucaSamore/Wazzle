package wazzle.controller.common;

import java.util.List;

import wazzle.controller.maingame.GameHistoryController;
import wazzle.controller.maingame.Settings;
import wazzle.controller.maingame.SettingsController;
import wazzle.model.common.BonusManager;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

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
	Settings getSettings();
	
	/**
	 * Gives the file controller.
	 * 
	 * @return FileController the file controller.
	 */
	FileController getFileController();
	
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
	 * Gives the Facade.
	 * 
	 * @return Facade the facade.
	 */
	Facade getFacade();
	
	/**
	 * Extract and add a bonus gained in the MiniGame, using a BonusManager method.
	 */
	void gainBonus();
	
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
	void updateSettings(final Settings settings);
	
	/**
	 * Gives this Wazzle controller.
	 * 
	 * @return WazzleController this Wazzle controller.
	 */
	WazzleController getThis();
}
