package wazzle.controller.maingame;

import java.io.IOException;

import java.util.List;

import wazzle.controller.common.FileController;
import wazzle.controller.common.FileControllerImpl;
import wazzle.model.common.BonusManager;
import wazzle.model.maingame.MainGameImpl;

public class WazzleControllerImpl implements WazzleController {

	private final FileController fileController;
	private final Settings settings;
	private final List<MainGameImpl> gameHistory;
	private final BonusManager bonusManager;
		
	public WazzleControllerImpl() throws IOException {
		this.fileController = new FileControllerImpl();
		this.settings = new SettingsImpl();
		this.gameHistory = this.fileController.getMainGameHistory("history.json");
		this.bonusManager = this.fileController.getBonuses("bonus.json");
	}
	
	@Override
	public FileController getFileController() {
		return this.fileController;
	}

	@Override
	public Settings getSettings() {
		return this.settings;
	}
	
	@Override
	public List<MainGameImpl> getGameHistory() {
		return this.gameHistory;
	}

	@Override
	public void gainBonus() {
		this.bonusManager.extractBonus();
	}

}
