package wazzle.controller.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import wazzle.controller.common.files.Deserializer;
import wazzle.controller.common.files.FileStrategies;
import wazzle.controller.common.files.Serializer;
import wazzle.controller.common.files.TextHandler;
import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;
import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.DifficultyNames;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGameImpl;

public final class FileControllerImpl implements FileController, Serializer, Deserializer {
	
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
	public void saveGames(final String fileName, final List<MainGameImpl> games) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		this.<MainGame>serialize(DIRECTORY + fileName, games.toArray(new MainGameImpl[games.size()]));
	}
	
	@Override
	public void saveMiniGame(final String fileName, final MiniGame game) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		this.<MiniGame>serialize(DIRECTORY + fileName, List.of(game).toArray(new MiniGameImpl[0]));
	}

	@Override
	public void saveBonuses(final String fileName, final BonusManager bonuses) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		this.<BonusManager>serialize(DIRECTORY + fileName, List.of(bonuses).toArray(new BonusManagerImpl[0]));
	}
	
	@Override
	public void saveCurrentSettings(final String fileName, final Difficulty settings) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		this.<Difficulty>serialize(DIRECTORY + fileName, List.of(settings).toArray(new Difficulty[0]));
	}
	
	@Override
	public void saveAllSettings(final String fileName, final List<Difficulty> allSettings) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		this.<Difficulty>serialize(DIRECTORY + fileName, allSettings.toArray(new Difficulty[allSettings.size()]));
	}
	
	@Override
	public List<MainGameImpl> getMainGameHistory(final String fileName) throws IOException{
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
			this.<MainGame>serialize(DIRECTORY + fileName, List.of().toArray(new MainGameImpl[0]));
		}
		
		return this.<MainGameImpl>deserialize(MainGameImpl.class, DIRECTORY + fileName);
	}
	
	@Override
	public Optional<MiniGameImpl> getMiniGame(final String fileName) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			return Optional.empty();
		}
		
		return Optional.of(this.<MiniGameImpl>deserialize(MiniGameImpl.class, DIRECTORY + fileName).get(0));
	}
	
	@Override
	public BonusManagerImpl getBonuses(final String fileName) throws IOException{
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
			this.<BonusManager>serialize(DIRECTORY + fileName, List.of(new BonusManagerImpl()).toArray(new BonusManagerImpl[0]));
		}
		
		return this.<BonusManagerImpl>deserialize(BonusManagerImpl.class, DIRECTORY + fileName).get(0);
	}
	
	@Override
	public Difficulty getCurrentSettings(final String fileName) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
			this.<Difficulty>serialize(DIRECTORY + fileName, List.of(new Difficulty(
					Difficulty.getDefault().getDifficultyName(), 
					Difficulty.getDefault().getGridShape(),
					Difficulty.getDefault().getLowerBound(),
					Difficulty.getDefault().getUpperBound(),
					Difficulty.getDefault().getTimeInMilliseconds())).toArray(new Difficulty[0]));
		}
		
		return this.<Difficulty>deserialize(Difficulty.class, DIRECTORY + fileName).get(0);
	}
	
	@Override
	public List<Difficulty> getAllSettings(final String fileName) throws IOException {
		return this.<Difficulty>deserialize(Difficulty.class, ClassLoader.getSystemResourceAsStream("files/" + fileName));
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
