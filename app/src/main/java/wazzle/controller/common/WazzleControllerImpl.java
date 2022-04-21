package wazzle.controller.common;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import wazzle.controller.maingame.GameHistoryController;
import wazzle.controller.maingame.GameHistoryControllerImpl;
import wazzle.controller.maingame.SettingsController;
import wazzle.controller.maingame.SettingsControllerImpl;
import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGameImpl;

public class WazzleControllerImpl implements WazzleController {

	private static final int MAX_GAME_HISTORY_VISIBLE = 10;
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
		this.bonusManager = this.bonusesFromFile();
		this.settingsController = new SettingsControllerImpl(this.settingsFromFile());
		this.gameHistoryController = new GameHistoryControllerImpl(this.gameHistoryFromFile());
		this.facade = new Facade();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dictionary getDataset() throws IOException {
		return this.fileController.getDataset(WazzleFiles.DATASET.getFileName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dictionary getShortDataset() throws IOException {
		return this.fileController.getDataset(WazzleFiles.SHORT_DATASET.getFileName());
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
	public Difficulty getCurrentDifficulty() {
		return this.settingsController.getCurrentDifficulty();
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
		this.gameHistoryController.sortGameHistoryByData();
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
	@Override
	public Optional<MiniGameImpl> getLastMinigame() throws IOException {
		return this.fileController.getMiniGame(WazzleFiles.MINI_GAME.getFileName());
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
	public String gainBonus() {
		return this.bonusManager.extractBonus();
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
	public void updateSettings(final Difficulty difficulty) {
//		this.settingsController.updateSettings(settings.getCurrentDifficulty(), settings.getCurrentGridShape());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveSettings() throws IOException {
//		this.fileController.saveSettings(WazzleFiles.SETTINGS.getFileName(), this.getCurrentDifficulty());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveGameHistory() throws IOException {
		this.fileController.saveGames(WazzleFiles.HISTORY.getFileName(), this.getGameHistory().stream()
				.limit(MAX_GAME_HISTORY_VISIBLE)
				.collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveBonuses() throws IOException {
		this.fileController.saveBonuses(WazzleFiles.BONUSES.getFileName(), this.getBonusManager());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveMiniGame(final MiniGame minigame) throws IOException {
		this.fileController.saveMiniGame(WazzleFiles.MINI_GAME.getFileName(), minigame);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEndedMiniGame() throws IOException {
		this.fileController.delete(WazzleFiles.MINI_GAME.getFileName());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public WazzleController getThis() {
		return this;
	}
	
	/**
	 * Takes bonuses from file using file controller.
	 * 
	 * @return BonusManager the bonus manager filled with bonuses quantities saved.
	 */
	private BonusManager bonusesFromFile() throws IOException {
		final var bonusQuantity = this.fileController.getBonuses(WazzleFiles.BONUSES.getFileName());
		final var bonuses = new BonusManagerImpl();
		bonuses.updateScoreBonusQuantity(i -> bonusQuantity.getScoreBonusQuantity());
		bonuses.updateTimeBonusQuantity(i -> bonusQuantity.getTimeBonusQuantity());
		bonuses.updateWordBonusQuantity(i -> bonusQuantity.getWordBonusQuantity());
		return bonuses;
	}
	
	/**
	 * Takes settings from file using file controller.
	 * 
	 * @return Settings the saved settings.
	 */
	private List<Difficulty> settingsFromFile() throws IOException {
//		final var content = this.fileController.getSettings(WazzleFiles.SETTINGS.getFileName());
//		final var settings = new SettingsImpl();
//		settings.updateCurrentDifficulty(content.getCurrentDifficulty());
//		settings.updateCurrentGridShape(content.getCurrentGridShape());
//		return settings;
		return Collections.emptyList();
	}
	
	/**
	 * Takes game history from file using file controller.
	 * 
	 * @return List<MainGameImpl> the saved main game games.
	 */
	private List<MainGameImpl> gameHistoryFromFile() throws IOException {
		final var content = this.fileController.getMainGameHistory(WazzleFiles.HISTORY.getFileName());
		final var gameHistory = new LinkedList<MainGameImpl>();
		gameHistory.addAll(content);
		return gameHistory;
	}
}
