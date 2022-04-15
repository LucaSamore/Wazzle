package wazzle.controller.common;

import java.io.IOException;


import java.util.List;

import wazzle.controller.maingame.GameHistoryController;
import wazzle.controller.maingame.GameHistoryControllerImpl;
import wazzle.controller.maingame.Settings;
import wazzle.controller.maingame.SettingsController;
import wazzle.controller.maingame.SettingsControllerImpl;
import wazzle.controller.maingame.SettingsImpl;
import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

public class WazzleControllerImpl implements WazzleController {

	private static final String BONUSES_PATH = "files/bonuses.json";
	private final FileController fileController;
	private final SettingsController settingsController;
	private final GameHistoryController gameHistoryController;
	private final BonusManager bonusManager;
	private final Facade facade;
		
	/**
	 * Construct a new WazzleController.
	 * 
	 * @throws IOException
	 */
	public WazzleControllerImpl() throws IOException {
		this.fileController = new FileControllerImpl();
		this.settingsController = new SettingsControllerImpl(new SettingsImpl());
		//this.gameHistoryController = new GameHistoryControllerImpl(this.fileController.getMainGameHistory("history.json"));
		this.gameHistoryController = new GameHistoryControllerImpl(List.of());
		var bonusQuantity = this.fileController.getBonuses(BONUSES_PATH);
		this.bonusManager = new BonusManagerImpl();
		this.bonusManager.updateScoreBonusQuantity(i -> bonusQuantity.getScoreBonusQuantity());
		this.bonusManager.updateTimeBonusQuantity(i -> bonusQuantity.getTimeBonusQuantity());
		this.bonusManager.updateWordBonusQuantity(i -> bonusQuantity.getWordBonusQuantity());
		this.facade = new Facade();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileController getFileController() {
		return this.fileController.getThis();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SettingsController getSettingsController() {
		return this.settingsController;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Settings getSettings() {
		return this.settingsController.getCurrentSettings();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GameHistoryController getGameHistoryController() {
		return this.gameHistoryController;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MainGameImpl> getGameHistory() {
		return List.copyOf(this.gameHistoryController.getGameHistory());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BonusManager getBonusManager() {
		return this.bonusManager;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Facade getFacade() {
		return this.facade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void gainBonus() {
		this.bonusManager.extractBonus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addMainGametoHistory(final MainGame mainGame) {
		this.gameHistoryController.addNewGame((MainGameImpl) mainGame);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateSettings(Settings settings) {
		this.settingsController.updateSettings(settings.getCurrentDifficulty(), settings.getCurrentGridShape());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public WazzleController getThis() {
		return this;
	}
}
