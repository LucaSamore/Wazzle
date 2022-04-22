package wazzle.controller;

import static org.junit.Assert.*;
import org.junit.Test;

import javafx.util.Pair;
import wazzle.controller.common.files.Deserializer;
import wazzle.controller.common.files.FileStrategies;
import wazzle.controller.common.files.Serializer;
import wazzle.controller.common.files.TextHandler;
import wazzle.model.TestFileUtils;
import wazzle.model.common.BonusManager;
import wazzle.model.common.BonusManagerImpl;
import wazzle.model.common.DictionaryImpl;
import wazzle.model.maingame.Difficulty;
import wazzle.model.maingame.DifficultyNames;
import wazzle.model.maingame.GridGeneratorImpl;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.MainGameImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TestFileHandling implements Serializer, Deserializer {
	
	private static final String TEST_FILE = "test.txt";
	private static final String EMPTY_FILE = "empty.txt";
	private static final String JSON_TEST_GAMES_FILE = "games.json";
	private static final String JSON_TEST_BONUSES_FILE = "bonuses.json";
	private static final String JSON_ALL_DIFFICULTIES = "all-settings.json";
	
	private final FileStrategies<String> handler = new TextHandler();

	@Test
	public void testTextFile() {
		try {
			List<String> items = this.handler.read(TestFileUtils.getPath() + EMPTY_FILE);
			System.out.println("Read values from empty test file: " + items);
			assertEquals(Collections.emptyList(), items); // read an empty .txt file
			
			items = this.handler.read(TestFileUtils.getPath() + TEST_FILE);
			System.out.println("Read values from test file: " + items);
			assertTrue(items.size() > 0); // read a .txt file with some lines
			
			this.handler.write(TestFileUtils.getPath() + EMPTY_FILE, List.of("this", "is", "a dummy test"));
			assertTrue(Files.size(Path.of(TestFileUtils.getPath() + EMPTY_FILE)) > 0); // write some lines to the empty .txt file

			this.handler.clear(TestFileUtils.getPath() + EMPTY_FILE);
			assertTrue(Files.size(Path.of(TestFileUtils.getPath() + EMPTY_FILE)) == 0); // clear the empty.txt file previously written
			
			long oldSize = Files.size(Path.of(TestFileUtils.getPath() + TEST_FILE));
			this.handler.append(TestFileUtils.getPath() + TEST_FILE, List.of("Added", "content", ":*"));
			assertTrue(Files.size(Path.of(TestFileUtils.getPath() + TEST_FILE)) > oldSize); // add a few lines to a non-empty .txt file (size must change)
			
			assertThrows(IOException.class, () -> this.handler.read("I do not exist")); // try to read a file that does not exist
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJsonGamesFile() {
		try {
			final var dataset = new DictionaryImpl(TestFileUtils.readDataset("datasetNuovo.txt"));
			final var shape = new Pair<Integer,Integer>(4,4);
			final var difficulty = Difficulty.getDefault();
			final var generator = new GridGeneratorImpl(dataset, shape, difficulty);
			
			final MainGame game1 = new MainGameImpl(generator.generate(), difficulty.getTimeInMilliseconds());
			final MainGame game2 = new MainGameImpl(generator.generate(), difficulty.getTimeInMilliseconds());
			final MainGame game3 = new MainGameImpl(generator.generate(), difficulty.getTimeInMilliseconds());
			
			final List<MainGame> toBeSerialized = List.of(game1, game2, game3);
			
			this.<MainGame>serialize(TestFileUtils.getPath() + JSON_TEST_GAMES_FILE, toBeSerialized.toArray(new MainGameImpl[toBeSerialized.size()]));
			
			List<MainGameImpl> deserializedGames = this.<MainGameImpl>deserialize(MainGameImpl.class, TestFileUtils.getPath() + JSON_TEST_GAMES_FILE);
			
			System.out.println(deserializedGames + System.lineSeparator());
			System.out.println(deserializedGames.get(0));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJsonBonusesFile() {
		try {
			final var bonuses = new BonusManagerImpl();
			
			bonuses.updateScoreBonusQuantity(q -> q + 500);
			bonuses.updateTimeBonusQuantity(q -> q + 100);
			bonuses.updateWordBonusQuantity(q -> q + 25);
			
			this.<BonusManager>serialize(TestFileUtils.getPath() + JSON_TEST_BONUSES_FILE, List.of(bonuses).toArray(new BonusManagerImpl[0]));
			
			BonusManagerImpl deserializedBonuses = this.<BonusManagerImpl>deserialize(BonusManagerImpl.class, TestFileUtils.getPath() + JSON_TEST_BONUSES_FILE).get(0);
			
			System.out.println("Score quantity: " + deserializedBonuses.getScoreBonusQuantity() + 
					" Time quantity: " + deserializedBonuses.getTimeBonusQuantity() + 
					" Word quantity: " + deserializedBonuses.getWordBonusQuantity());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAllSettingsSerialization() {
		try {
			// Serialize all settings combinations (used to produce the actual all-settings.json in resources folder)
			final var allSettings = new ArrayList<Difficulty>();
			allSettings.add(new Difficulty(DifficultyNames.EASY.getName(), 4, 76, 200, 240000L));
			allSettings.add(new Difficulty(DifficultyNames.HARD.getName(), 4, 25, 75, 180000L));
			allSettings.add(new Difficulty(DifficultyNames.EASY.getName(), 5, 151, 250, 240000L));
			allSettings.add(new Difficulty(DifficultyNames.HARD.getName(), 5, 100, 150, 180000L));
			allSettings.add(new Difficulty(DifficultyNames.EASY.getName(), 6, 226, 350, 240000L));
			allSettings.add(new Difficulty(DifficultyNames.HARD.getName(), 6, 150, 225, 180000L));
			this.<Difficulty>serialize(TestFileUtils.getPath() + JSON_ALL_DIFFICULTIES, allSettings.toArray(new Difficulty[allSettings.size()]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAllSettingsDeserialization() {
		try {
			final var allSettings = new ArrayList<Difficulty>();
			allSettings.add(new Difficulty(DifficultyNames.EASY.getName(), 4, 76, 200, 240000L));
			allSettings.add(new Difficulty(DifficultyNames.HARD.getName(), 4, 25, 75, 180000L));
			allSettings.add(new Difficulty(DifficultyNames.EASY.getName(), 5, 151, 250, 240000L));
			allSettings.add(new Difficulty(DifficultyNames.HARD.getName(), 5, 100, 150, 180000L));
			allSettings.add(new Difficulty(DifficultyNames.EASY.getName(), 6, 226, 350, 240000L));
			allSettings.add(new Difficulty(DifficultyNames.HARD.getName(), 6, 150, 225, 180000L));
			final var deserializedSettings = this.<Difficulty>deserialize(Difficulty.class, TestFileUtils.getPath() + JSON_ALL_DIFFICULTIES);
			assertTrue(allSettings.equals(deserializedSettings));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
