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
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGameImpl;

public interface FileController {
	String getPath();
	
	Dictionary getDataset(final String fileName) throws IOException;
	
	void saveGames(final String fileName, final List<MainGameImpl> games) throws IOException;
	
	void saveMiniGame(final String fileName, final MiniGame game) throws IOException;
	
	void saveBonuses(final String fileName, final BonusManager bonuses) throws IOException;
	
	void saveCurrentSettings(final String fileName, Difficulty settings) throws IOException;
	
	void saveAllSettings(final String fileName, final List<Difficulty> allSettings) throws IOException;
	
	List<MainGameImpl> getMainGameHistory(final String fileName) throws IOException;
	
	Optional<MiniGameImpl> getMiniGame(final String fileName) throws IOException;
	
	BonusManagerImpl getBonuses(final String fileName) throws IOException;
	
	Difficulty getCurrentSettings(final String fileName) throws IOException;
	
	List<Difficulty> getAllSettings(final String fileName) throws IOException;
	
	default void create(final String fullPath) throws IOException {
		Files.createFile(Path.of(fullPath));
	}
	
	default void delete(final String fullPath) throws IOException {
		Files.delete(Path.of(fullPath));
	}
	
	default boolean exists(final String fullPath) throws IOException {
		return Files.exists(Path.of(fullPath), LinkOption.NOFOLLOW_LINKS);
	}
	
	FileController getThis();
}
