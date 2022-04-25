package wazzle.controller.common;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import javafx.util.Pair;
import wazzle.controller.maingame.GameHistoryController;
import wazzle.controller.maingame.SettingsController;
import wazzle.model.common.BonusManager;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.maingame.grid.Difficulty;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.SavedMiniGame;
import wazzle.model.minigame.word.WordsDispenser;

/**
 * This interface provides methods for {@link MiniGameController}, {@link MainGameController},
 * {@link GameHistoryController} and {@link SettingsController}, for managing dataset, savings,
 * difficulty, bonuses and continuing a started minigame game.
 */
public interface WazzleController {
	
	/**
	 * This method allows the user to retrieve a {@code MainGame} object.
	 * @param dataset a {@code Dictionary} object that represents what dataset the game is going to use.
	 * @param gridShape a {@code Pair<Integer,Integer>} object that represents the shape of the grid.
	 * @param difficulty the {@code Difficulty} selected for this game.
	 * @return a {@code MainGame} describing the game newly created
	 * @see wazzle.model.common.Dictionary
	 * @see javafx.util.Pair
	 * @see wazzle.model.maingame.grid.Difficulty
	 */
	MainGame startNewMainGame(Dictionary dataset, Pair<Integer,Integer> gridShape, Difficulty difficulty);
	
	/**
	 * This method allows the user to retrieve a {@code MiniGame} object.
	 * @param wordsDispenser a {@code WordsDispenser} object.
	 * @return a {@code MiniGame} describing the game newly created
	 * @see wazzle.model.minigame.word.WordsDispenser
	 */
	MiniGame startNewMiniGame(WordsDispenser wordsDispenser);

	/**
	 * Gives the dataset.
	 * 
	 * @return Dataset the dataset which {@link MainGame} takes the words from.
	 * @throws IOException
	 */
	Dictionary getDataset() throws IOException;
	
	/**
	 * Gives the shortest dataset.
	 * 
	 * @return Dataset the short dataset which {@link MiniGame} takes the words from.
	 * @throws IOException
	 */
	Dictionary getShortDataset() throws IOException;
	
	/**
	 * Gives the {@link SettingsController}.
	 * 
	 * @return SettingsController the {@link SettingsController}.
	 */
	SettingsController getSettingsController();
	
	/**
	 * Gives the current {@link Difficulty}.
	 * 
	 * @return Difficulty the current {@link Difficulty}.
	 */
	Difficulty getCurrentDifficulty();
	
	/**
	 * Gives the {@link GameHistoryController}.
	 * 
	 * @return GameHistoryController the {@link GameHistoryController}.
	 */
	GameHistoryController getGameHistoryController();
	
	/**
	 * Gives the game history.
	 * 
	 * @return List<MainGameImpl> the game history.
	 */
	List<MainGameImpl> getGameHistory();
	
	/**
	 * Gives the {@link BonusManager}.
	 * 
	 * @return BonusManager the {@link BonusManager}.
	 */
	BonusManager getBonusManager();
	
	/**
	 * Gives the last {@link SavedMiniGame}.
	 * 
	 * @return MiniGameImpl the last {@link SavedMiniGame}.
	 */
	Optional<SavedMiniGame> getLastMinigame() throws IOException;
	
	/**
	 * Gives the {@link WordsDispenser}.
	 * 
	 * @return WordsDispenser the {@link WordsDispenser}.
	 */
	WordsDispenser getWordsDispenser();
	
	/**
	 * Extract and add a bonus gained in a {@link MiniGame} game, using a {@link BonusManager} method.
	 */
	String gainBonus();
	
	/**
	 * Add a {@link MainGame} to game history.
	 * 
	 * @param mainGame 		The {@link MainGame} to be inserted.
	 */
	void addMainGametoHistory(final MainGame mainGame);
	
	/**
	 * Update the current {@link Difficulty}.
	 * 
	 * @param Settings 		The current {@link Difficulty}.
	 */
	void updateCurrentDifficulty(Difficulty difficulty);	
	
	/**
	 * Save current difficulty to file.
	 * 
	 * @throws IOException 
	 */
	void saveCurrentDifficulty() throws IOException;
	
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
	 * Save {@link SavedMiniGame} to file.
	 * 
	 * @param minigame 		The {@link SavedMiniGame} which have to been saved.
	 * @throws IOException
	 */
	void saveMiniGame(SavedMiniGame minigame) throws IOException;
	
	/**
	 * Delete the last {@link SavedMiniGame}.
	 * 
	 * @throws IOException
	 */
	void deleteEndedMiniGame() throws IOException;
}
