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
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGameImpl;

public final class FileControllerImpl implements FileController, Serializer, Deserializer {
	
	private final FileStrategies<String> textFileHandler;
	
	public FileControllerImpl() throws IOException {
		this.textFileHandler = new TextHandler();
		this.buildFoldersStructure();
	}
	
	@Override
	public Dictionary getDataset(final String path) throws IOException {
		return new DictionaryImpl(this.textFileHandler
				.read(ClassLoader.getSystemResourceAsStream(path))
				.stream()
				.collect(Collectors.toSet()));
	}

	@Override
	public void saveGames(final String fileName, final List<MainGameImpl> games) throws IOException {
		if(!this.exists(WazzleFiles.getFullPathByName(fileName))) {
			this.create(WazzleFiles.getFullPathByName(fileName));
		}
		
		this.<MainGame>serialize(WazzleFiles.getFullPathByName(fileName), games.toArray(new MainGameImpl[games.size()]));
	}
	
	@Override
	public void saveMiniGame(final String fileName, final MiniGame game) throws IOException {
		if(!this.exists(WazzleFiles.getFullPathByName(fileName))) {
			this.create(WazzleFiles.getFullPathByName(fileName));
		}
		
		this.<MiniGame>serialize(WazzleFiles.getFullPathByName(fileName), List.of(game).toArray(new MiniGameImpl[0]));
	}

	@Override
	public void saveBonuses(final String fileName, final BonusManager bonuses) throws IOException {
		if(!this.exists(WazzleFiles.getFullPathByName(fileName))) {
			this.create(WazzleFiles.getFullPathByName(fileName));
		}
		
		this.<BonusManager>serialize(WazzleFiles.getFullPathByName(fileName), List.of(bonuses).toArray(new BonusManagerImpl[0]));
	}
	
	@Override
	public void saveCurrentSettings(final String fileName, final Difficulty settings) throws IOException {
		if(!this.exists(WazzleFiles.getFullPathByName(fileName))) {
			this.create(WazzleFiles.getFullPathByName(fileName));
		}
		
		this.<Difficulty>serialize(WazzleFiles.getFullPathByName(fileName), List.of(settings).toArray(new Difficulty[0]));
	}
	
	@Override
	public void saveAllSettings(final String fileName, final List<Difficulty> allSettings) throws IOException {
		if(!this.exists(WazzleFiles.getFullPathByName(fileName))) {
			this.create(WazzleFiles.getFullPathByName(fileName));
		}
		
		this.<Difficulty>serialize(WazzleFiles.getFullPathByName(fileName), allSettings.toArray(new Difficulty[allSettings.size()]));
	}
	
	@Override
	public List<MainGameImpl> getMainGameHistory(final String fileName) throws IOException{
		if(!this.exists(WazzleFiles.getFullPathByName(fileName))) {
			this.create(WazzleFiles.getFullPathByName(fileName));
			this.<MainGame>serialize(WazzleFiles.getFullPathByName(fileName), List.of().toArray(new MainGameImpl[0]));
		}
		
		return this.<MainGameImpl>deserialize(MainGameImpl.class, WazzleFiles.getFullPathByName(fileName));
	}
	
	@Override
	public Optional<MiniGameImpl> getMiniGame(final String fileName) throws IOException {
		if(!this.exists(WazzleFiles.getFullPathByName(fileName))) {
			return Optional.empty();
		}
		
		return Optional.of(this.<MiniGameImpl>deserialize(MiniGameImpl.class, WazzleFiles.getFullPathByName(fileName)).get(0));
	}
	
	@Override
	public BonusManagerImpl getBonuses(final String fileName) throws IOException{
		if(!this.exists(WazzleFiles.getFullPathByName(fileName))) {
			this.create(WazzleFiles.getFullPathByName(fileName));
			this.<BonusManager>serialize(WazzleFiles.getFullPathByName(fileName), List.of(new BonusManagerImpl()).toArray(new BonusManagerImpl[0]));
		}
		
		return this.<BonusManagerImpl>deserialize(BonusManagerImpl.class, WazzleFiles.getFullPathByName(fileName)).get(0);
	}
	
	@Override
	public Difficulty getCurrentSettings(final String fileName) throws IOException {
		if(!this.exists(WazzleFiles.getFullPathByName(fileName))) {
			this.create(WazzleFiles.getFullPathByName(fileName));
			this.<Difficulty>serialize(WazzleFiles.getFullPathByName(fileName), List.of(new Difficulty(
					Difficulty.getDefault().getDifficultyName(), 
					Difficulty.getDefault().getGridShape(),
					Difficulty.getDefault().getLowerBound(),
					Difficulty.getDefault().getUpperBound(),
					Difficulty.getDefault().getTimeInMilliseconds())).toArray(new Difficulty[0]));
		}
		
		return this.<Difficulty>deserialize(Difficulty.class, WazzleFiles.getFullPathByName(fileName)).get(0);
	}
	
	@Override
	public List<Difficulty> getAllSettings(final String path) throws IOException {
		return this.<Difficulty>deserialize(Difficulty.class, ClassLoader.getSystemResourceAsStream(path));
	}
	
	@Override
	public FileController getThis() {
		return this;
	}
	
	private void buildFoldersStructure() throws IOException {
		if(!this.exists(WazzleFiles.getFoldersStructure())) {
			Files.createDirectories(Path.of(WazzleFiles.getFoldersStructure()));
		}
	}
}
