package wazzle.controller.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import wazzle.controller.common.files.Deserializer;
import wazzle.controller.common.files.FileStrategies;
import wazzle.controller.common.files.Serializer;
import wazzle.controller.common.files.TextHandler;
import wazzle.controller.maingame.Settings;
import wazzle.controller.maingame.SettingsImpl;
import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;
import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

public final class FileControllerImpl implements FileController {

	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String DIRECTORY = 
			System.getProperty("user.home") + SEPARATOR + 
			"wazzle" + SEPARATOR + 
			"files" + SEPARATOR;
	
	private final FileStrategies<String> textFileHandler;
	
	public FileControllerImpl() throws IOException {
		this.textFileHandler = new TextHandler();
		this.buildFoldersStructure();
	}
	
	@Override
	public String getPath() {
		return DIRECTORY;
	}
	
	@Override
	public Dictionary getDataset(final String fileName) throws IOException {
		return new DictionaryImpl(this.textFileHandler
				.read(ClassLoader.getSystemResourceAsStream("files/" + fileName))
				.stream()
				.collect(Collectors.toSet()));
	}

	@Override
	public void saveGames(final String fileName, final List<MainGame> games) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		Serializer.<MainGame>serialize(DIRECTORY + fileName, games.toArray(new MainGameImpl[games.size()]));
	}

	@Override
	public void saveBonuses(final String fileName, final BonusManager bonuses) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		Serializer.<BonusManager>serialize(DIRECTORY + fileName, List.of(bonuses).toArray(new BonusManagerImpl[0]));
	}
	
	@Override
	public void saveSettings(final String fileName, final Settings settings) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		Serializer.<Settings>serialize(DIRECTORY + fileName, List.of(settings).toArray(new SettingsImpl[0]));
	}
	
	@Override
	public List<MainGameImpl> getMainGameHistory(final String fileName) throws IOException{
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		return Deserializer.<MainGameImpl>deserialize(MainGameImpl.class, DIRECTORY + fileName);
	}

	@Override
	public BonusManagerImpl getBonuses(final String fileName) throws IOException{
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
			Serializer.<BonusManager>serialize(DIRECTORY + fileName, List.of(new BonusManagerImpl()).toArray(new BonusManagerImpl[0]));
		}
		
		return Deserializer.<BonusManagerImpl>deserialize(BonusManagerImpl.class, DIRECTORY + fileName).get(0);
	}
	
	@Override
	public Settings getSettings(String fileName) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
			Serializer.<Settings>serialize(DIRECTORY + fileName, List.of(new SettingsImpl()).toArray(new SettingsImpl[0]));
		}
		
		return Deserializer.<SettingsImpl>deserialize(SettingsImpl.class, DIRECTORY + fileName).get(0);
	}
	
	@Override
	public FileController getThis() {
		return this;
	}
	
	private void buildFoldersStructure() throws IOException {
		if(!this.exists(DIRECTORY)) {
			Files.createDirectories(Path.of(DIRECTORY));
		}
	}
}
