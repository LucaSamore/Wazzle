package wazzle.controller;

import static org.junit.Assert.*;
import org.junit.Test;

import javafx.util.Pair;
import wazzle.controller.common.files.Deserializers;
import wazzle.controller.common.files.FileStrategies;
import wazzle.controller.common.files.Serializer;
import wazzle.controller.common.files.SerializerImpl;
import wazzle.controller.common.files.TextHandler;
import wazzle.model.DatasetReader;
import wazzle.model.common.Dictionary;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.GridGenerator;
import wazzle.model.maingame.GridGeneratorImpl;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public final class TestFileHandler {
	
	private static final String TEST_FILE = "test.txt";
	private static final String EMPTY_FILE = "empty.txt";
	private static final String JSON_TEST_FILE = "games.json";
	
	private final FileStrategies<String> handler = new TextHandler();

	@Test
	public void testTextFile() {
		try {
			List<String> items = this.handler.read(DatasetReader.getPath() + EMPTY_FILE);
			System.out.println("Read values from empty test file: " + items);
			assertEquals(Collections.emptyList(), items); // read an empty .txt file
			
			items = this.handler.read(DatasetReader.getPath() + TEST_FILE);
			System.out.println("Read values from test file: " + items);
			assertTrue(items.size() > 0); // read a .txt file with some lines
			
			this.handler.write(DatasetReader.getPath() + EMPTY_FILE, List.of("this", "is", "a dummy test"));
			assertTrue(Files.size(Path.of(DatasetReader.getPath() + EMPTY_FILE)) > 0); // write some lines to the empty .txt file

			this.handler.clear(DatasetReader.getPath() + EMPTY_FILE);
			assertTrue(Files.size(Path.of(DatasetReader.getPath() + EMPTY_FILE)) == 0); // clear the empty.txt file previously written
			
			long oldSize = Files.size(Path.of(DatasetReader.getPath() + TEST_FILE));
			this.handler.append(DatasetReader.getPath() + TEST_FILE, List.of("Added", "content", ":*"));
			assertTrue(Files.size(Path.of(DatasetReader.getPath() + TEST_FILE)) > oldSize); // add a few lines to a non-empty .txt file (size must change)
			
			assertThrows(IOException.class, () -> this.handler.read("I do not exist")); // try to read a file that does not exist
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJsonFile() {
		try {
			final Dictionary dataset = new DictionaryImpl(DatasetReader.readDataset("datasetNuovo.txt"));
			final Pair<Integer,Integer> shape = new Pair<>(4,4);
			final Difficulty difficulty = new Difficulty(126, 250, 240000L);
			final GridGenerator generator = new GridGeneratorImpl(dataset, shape, difficulty);
			
			final MainGame game1 = new MainGameImpl(generator.generate(), difficulty.getTimeInMilliseconds());
			final MainGame game2 = new MainGameImpl(generator.generate(), difficulty.getTimeInMilliseconds());
			final MainGame game3 = new MainGameImpl(generator.generate(), difficulty.getTimeInMilliseconds());
			
			final List<MainGame> toBeSerialized = List.of(game1, game2, game3);
			
			final Serializer<MainGame> serializer = new SerializerImpl<>();
			
			serializer.serialize(DatasetReader.getPath() + JSON_TEST_FILE, toBeSerialized.toArray(new MainGameImpl[toBeSerialized.size()]));
			
			List<MainGameImpl> deserializedGames = Deserializers.mainGames(DatasetReader.getPath() + JSON_TEST_FILE);
			
			System.out.println(deserializedGames + System.lineSeparator());
			System.out.println(deserializedGames.get(0));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
