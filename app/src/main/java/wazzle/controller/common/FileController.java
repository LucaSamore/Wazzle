package wazzle.controller.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;

import wazzle.controller.maingame.Settings;
import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

public interface FileController {
	String getPath();
	
	Dictionary getDataset(final String fileName) throws IOException;
	
	void saveGames(final String fileName, final List<MainGame> games) throws IOException;
	
	//void saveGames(final String fileName, List<MiniGame> games);
	
	void saveBonuses(final String fileName, final BonusManager bonuses) throws IOException;
	
	void saveSettings(final String fileName, Settings settings) throws IOException;
	
	List<MainGameImpl> getMainGameHistory(final String fileName) throws IOException;
	
	//List<MiniGameImpl> getMiniGameHistory(final String fileName) throws IOException;
	
	BonusManagerImpl getBonuses(final String fileName) throws IOException;
	
	Settings getSettings(final String fileName) throws IOException;
	
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
