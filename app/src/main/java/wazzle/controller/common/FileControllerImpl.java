package wazzle.controller.common;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import wazzle.controller.common.files.Deserializers;
import wazzle.controller.common.files.FileStrategies;
import wazzle.controller.common.files.SerializerImpl;
import wazzle.controller.common.files.TextHandler;
import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;
import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

public final class FileControllerImpl implements FileController {

	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String DIRECTORY = "src" + SEPARATOR + 
			"main" + SEPARATOR +
			"res" + SEPARATOR +
			"files" + SEPARATOR;
	
	private final FileStrategies<String> textFileHandler = new TextHandler();
	
	@Override
	public String getPath() {
		return DIRECTORY;
	}
	
	@Override
	public Dictionary getDataset(final String fileName) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			throw new IOException(fileName + " does not exist!");
		}
		
		return new DictionaryImpl(this.textFileHandler
				.read(DIRECTORY + fileName)
				.stream()
				.collect(Collectors.toSet()));
	}

	@Override
	public void saveGames(final String fileName, final List<MainGame> games) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		final var serializer = new SerializerImpl<MainGame>();
		serializer.serialize(DIRECTORY + fileName, games.toArray(new MainGameImpl[games.size()]));
	}

	@Override
	public void saveBonuses(final String fileName, final BonusManager bonuses) throws IOException {
		if(!this.exists(DIRECTORY + fileName)) {
			this.create(DIRECTORY + fileName);
		}
		
		final var serializer = new SerializerImpl<BonusManager>();
		serializer.serialize(DIRECTORY + fileName, bonuses);
	}
	

	@Override
	public List<MainGameImpl> getMainGameHistory(final String fileName) throws IOException{
		if(!this.exists(DIRECTORY + fileName)) {
			throw new IOException(fileName + " does not exist!");
		}
		
		return Deserializers.mainGames(DIRECTORY + fileName);
	}

	@Override
	public BonusManagerImpl getBonuses(final String fileName) throws IOException{
		if(!this.exists(DIRECTORY + fileName)) {
			throw new IOException(fileName + " does not exist!");
		}
		
		return Deserializers.bonuses(DIRECTORY + fileName);
	}
}
