package wazzle.controller.maingame;

import java.util.Map;

import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;

import wazzle.model.maingame.Difficulty;

public class SettingsImpl implements Settings {
	
	private static final Difficulty DEFAULT_DIFFICULTY = new Difficulty(126, 250, 240000L);
	private static final int DEFAULT_GRID_SHAPE = 4;
	private static final Map<String, Map<Integer, Difficulty>> DEFAULT_DIFFICULTIES = 
			Map.of("EASY", Map.of(4, new Difficulty(126, 250, 240000L),
								  5, new Difficulty(201, 325, 240000L),
								  6, new Difficulty(351, 500, 240000L)),
				   "HARD", Map.of(4, new Difficulty(50, 125, 180000L),
						   		  5, new Difficulty(125, 200, 180000L),
						   		  6, new Difficulty(250, 350, 180000L)));
	@Expose
	private Difficulty currentDifficulty;
	@Expose
	private int currentGridShape;
	private final Map<String, Map<Integer, Difficulty>> difficulties;
	
	public SettingsImpl() {
		this.currentDifficulty = DEFAULT_DIFFICULTY;
		this.currentGridShape = DEFAULT_GRID_SHAPE;
		this.difficulties = DEFAULT_DIFFICULTIES;
	}

	@Override
	public Difficulty getCurrentDifficulty() {
		return this.currentDifficulty;
	}

	@Override
	public int getCurrentGridShape() {
		return this.currentGridShape;
	}
	
	@Override
	public Map<String, Map<Integer, Difficulty>> getAllDifficulties() {
		return Map.copyOf(this.difficulties);
	} 
	
	@Override
	public String getCurrentDifficultyName() {
		return this.difficulties.entrySet()
								 .stream()
								 .collect(Collectors.toMap(e1 -> e1.getKey(), e2 -> e2.getValue().values().stream().collect(Collectors.toSet())))
								 .entrySet()
								 .stream()
								 .filter(e -> e.getValue().contains(this.getCurrentDifficulty()))
								 .findFirst()
								 .get()
								 .getKey();
	}

	@Override
	public void updateCurrentDifficulty(final Difficulty difficulty) {
		this.currentDifficulty = difficulty;
	}

	@Override
	public void updateCurrentGridShape(final int gridShape) {
		this.currentGridShape = gridShape;
	}

	@Override
	public String toString() {
		return "SettingsImpl [currentDifficulty=" + this.getCurrentDifficultyName() 
		+ ", currentGridShape=" + this.currentGridShape + "]";
	}

}