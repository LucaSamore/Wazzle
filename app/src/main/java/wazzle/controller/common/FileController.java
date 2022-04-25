package wazzle.controller.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.maingame.grid.Difficulty;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.SavedMiniGame;

/**
 * This interface provides methods for file handling.
 */
public interface FileController {
	
	/**
	 * Reads a {@link Dictionary} object from given file.
	 * @param path a {@code String} representing the path of the file.
	 * @return a {@link Dictionary} object.
	 * @throws IOException
	 */
	Dictionary getDataset(final String path) throws IOException;
	
	/**
	 * Writes a {@code List<MainGameImpl>} objects from given file.
	 * @param fileName a {@code String} representing the name of the file.
	 * @param games a {@code List<MainGameImpl>} to be written.
	 * @return void
	 * @throws IOException
	 * @see MainGameImpl
	 */
	void saveGames(final String fileName, final List<MainGameImpl> games) throws IOException;
	

	/**
	 * Writes a {@link MiniGame} objects from given file.
	 * @param fileName a {@code String} representing the name of the file.
	 * @param game a {@code MiniGame} to be written.
	 * @return void
	 * @throws IOException
	 */
	void saveMiniGame(final String fileName, final SavedMiniGame minigame) throws IOException;
	
	/**
	 * Writes a {@link BonusManager} objects from given file.
	 * @param fileName a {@code String} representing the name of the file.
	 * @param bonuses a {@code BonusManager} to be written.
	 * @return void
	 * @throws IOException
	 */
	void saveBonuses(final String fileName, final BonusManager bonuses) throws IOException;
	
	/**
	 * Writes a {@link Difficulty} objects from given file.
	 * @param fileName a {@code String} representing the name of the file.
	 * @param settings a {@code Difficulty} to be written.
	 * @return void
	 * @throws IOException
	 */
	void saveCurrentSettings(final String fileName, Difficulty settings) throws IOException;
	
	/**
	 * Writes a {@code List<Difficulty>} objects from given file.
	 * @param fileName a {@code String} representing the name of the file.
	 * @param allSettings a {@code List<Difficulty>} to be written.
	 * @return void
	 * @throws IOException
	 * @see Difficulty
	 */
	void saveAllSettings(final String fileName, final List<Difficulty> allSettings) throws IOException;
	
	/**
	 * Reads a {@code List<MainGameImpl>} objects from given file.
	 * @param fileName a {@code String} representing the name of the file.
	 * @return a {@code List<MainGameImpl>} 
	 * @throws IOException
	 * @see MainGameImpl
	 */
	List<MainGameImpl> getMainGameHistory(final String fileName) throws IOException;
	
	/**
	 * Reads a {@code Optional<SavedMiniGame>} object from given file.
	 * @param fileName a {@code String} representing the name of the file.
	 * @return a {@code Optional<SavedMiniGame>} 
	 * @throws IOException
	 * @see SavedMiniGame
	 */
	Optional<SavedMiniGame> getMiniGame(final String fileName) throws IOException;

	
	/**
	 * Reads a {@link BonusManagerImpl} object from given file.
	 * @param fileName a {@code String} representing the name of the file.
	 * @return a {@code BonusManagerImpl} 
	 * @throws IOException
	 */
	BonusManagerImpl getBonuses(final String fileName) throws IOException;
	
	/**
	 * Reads a {@link Difficulty} object from given file.
	 * @param fileName a {@code String} representing the name of the file.
	 * @return a {@code Difficulty} 
	 * @throws IOException
	 */
	Difficulty getCurrentSettings(final String fileName) throws IOException;
	
	/**
	 * Reads a {@code List<Difficulty>} objects from given file.
	 * @param path a {@code String} representing the path of the file.
	 * @return a {@code List<Difficulty>} 
	 * @throws IOException
	 * @see Difficulty
	 */
	List<Difficulty> getAllSettings(final String path) throws IOException;
	
	/**
	 * Creates a file given the full path.
	 * @param fullPath a {@code String} representing the full path of the file.
	 * @return void
	 * @throws IOException
	 */
	default void create(final String fullPath) throws IOException {
		Files.createFile(Path.of(fullPath));
	}
	
	/**
	 * Deletes a file given the full path.
	 * @param fullPath a {@code String} representing the full path of the file.
	 * @return void
	 * @throws IOException
	 */
	default void delete(final String fullPath) throws IOException {
		Files.delete(Path.of(fullPath));
	}
	
	/**
	 * Checks if a file exists given the full path.
	 * @param fullPath a {@code String} representing the full path of the file.
	 * @return void
	 * @throws IOException
	 */
	default boolean exists(final String fullPath) throws IOException {
		return Files.exists(Path.of(fullPath), LinkOption.NOFOLLOW_LINKS);
	}
}
