package wazzle.controller.common;

import java.io.IOException;

import java.util.List;

import wazzle.controller.maingame.Settings;
import wazzle.controller.maingame.SettingsImpl;
import wazzle.model.common.BonusManager;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

public class WazzleControllerImpl implements WazzleController {

	private final FileController fileController;
	private final Settings settings;
	private final List<MainGameImpl> gameHistory;
	private final BonusManager bonusManager;
		
	/**
	 * Construct a new WazzleController.
	 * 
	 * @throws IOException
	 */
	public WazzleControllerImpl() throws IOException {
		this.fileController = new FileControllerImpl();
		this.settings = new SettingsImpl();
		this.gameHistory = this.fileController.getMainGameHistory("history.json");
		this.bonusManager = this.fileController.getBonuses("bonus.json");
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
	public Settings getSettings() {
		return this.settings;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MainGameImpl> getGameHistory() {
		return List.copyOf(this.gameHistory);
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
	@Override
	public void gainBonus() {
		this.bonusManager.extractBonus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addMainGametoHistory(final MainGame mainGame) {
		this.gameHistory.add((MainGameImpl) mainGame);
	}
	
	@Override
	public WazzleController getThis() {
		return this;
	}
}
