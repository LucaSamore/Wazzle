package wazzle.controller.common;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.checkerframework.checker.nullness.qual.NonNull;
import wazzle.controller.maingame.GameHistoryController;
import wazzle.controller.maingame.GameHistoryControllerImpl;
import wazzle.controller.maingame.SettingsController;
import wazzle.controller.maingame.SettingsControllerImpl;
import wazzle.controller.minigame.FiveLetterDictionary;
import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.ExtractedWordManager;
import wazzle.model.minigame.ExtractedWordManagerImpl;
import wazzle.model.minigame.SavedMiniGame;

/**
 * This class is a concrete implementation for {@link WazzleController}
 * It provides methods for manage every Wazzle component.
 */
public final class WazzleControllerImpl implements WazzleController {

	private static final int MAX_GAME_HISTORY_VISIBLE = 10;
	private final FileController fileController;
	private final SettingsController settingsController;
	private final GameHistoryController gameHistoryController;
	private final BonusManager bonusManager;
	private final Facade facade;
	private final ExtractedWordManager extractedWordManager;
		
	/**
	 * Construct a new {@link WazzleController}.
	 * 
	 * @throws IOException
	 */
	public WazzleControllerImpl() throws IOException {
		this.fileController = new FileControllerImpl();
		this.bonusManager = this.bonusesFromFile();
		this.settingsController = new SettingsControllerImpl(this.difficultiesFromFIle(), this.currentDifficultyFromFile());
		this.gameHistoryController = new GameHistoryControllerImpl(this.gameHistoryFromFile());
		this.facade = new Facade();
		this.extractedWordManager = new ExtractedWordManagerImpl(new FiveLetterDictionary(this.getShortDataset()));
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
	public Optional<SavedMiniGame> getLastMinigame() throws IOException {
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
	public ExtractedWordManager getExtractedWordManager() {
		return this.extractedWordManager;
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
	public void updateCurrentDifficulty(final Difficulty difficulty) {
		this.settingsController.setCurrentDifficulty(difficulty);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveCurrentDifficulty() throws IOException {
		this.fileController.saveCurrentSettings(WazzleFiles.SETTINGS.getFileName(), this.getCurrentDifficulty());
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
	public void saveMiniGame(final SavedMiniGame minigame) throws IOException {
		this.fileController.saveMiniGame(WazzleFiles.MINI_GAME.getFileName(), minigame);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEndedMiniGame() throws IOException {
		this.fileController.delete(WazzleFiles.getFullPathByName(WazzleFiles.MINI_GAME.getFileName()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public WazzleController getThis() {
		return this;
	}
	
	/**
	 * Takes bonuses from file using {@link FileController}.
	 * 
	 * @return BonusManager the {@link BonusManager} filled with bonuses quantities saved.
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
	 * Takes all the existing {@link Difficulty} from file using {@link FileController}.
	 * 
	 * @return List<Difficulty> which contains all the existing {@link Difficulty}.
	 */
	private List<Difficulty> difficultiesFromFIle() throws IOException {
		return this.fileController.getAllSettings(WazzleFiles.ALL_SETTINGS.getFileName());
	}
	
	/**
	 * Gives the current {@link Difficulty} saved.
	 * 
	 * @return Difficulty the current {@link Difficulty} setted.
	 * @throws IOException
	 */
	private @NonNull Difficulty currentDifficultyFromFile() throws IOException {
		return this.fileController.getCurrentSettings(WazzleFiles.SETTINGS.getFileName());
	}
	
	/**
	 * Takes game history from file using {@link FileController}.
	 * 
	 * @return List<MainGameImpl> the saved {@link MainGame} games.
	 */
	private List<MainGameImpl> gameHistoryFromFile() throws IOException {
		final var content = this.fileController.getMainGameHistory(WazzleFiles.HISTORY.getFileName());
		final var gameHistory = new LinkedList<MainGameImpl>();
		gameHistory.addAll(content);
		return gameHistory;
	}
}
