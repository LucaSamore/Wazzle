package wazzle.controller.maingame;

import java.util.Map;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;

public class SettingsImpl implements Settings {
	
	private static final Difficulty DEFAULT_DIFFICULTY = new Difficulty(126, 250, 240000L);
	private static final Pair<Integer, Integer> DEFAULT_GRID_SHAPE = new Pair<>(4,4);
	private static final Map<String, Map<Pair<Integer, Integer>, Difficulty>> DEFAULT_DIFFICULTIES = 
			Map.of("EASY", Map.of(new Pair<>(4,4), new Difficulty(126, 250, 240000L),
								  new Pair<>(5,5), new Difficulty(201, 325, 240000L),
								  new Pair<>(6,6), new Difficulty(351, 500, 240000L)),
				   "HARD", Map.of(new Pair<>(4,4), new Difficulty(50, 125, 180000L),
						   		  new Pair<>(5,5), new Difficulty(125, 200, 180000L),
						   		  new Pair<>(6,6), new Difficulty(250, 350, 180000L)));
	private Difficulty currentDifficulty;
	private Pair<Integer, Integer> currentGridShape;
	private Map<String, Map<Pair<Integer, Integer>, Difficulty>> difficulties;
	
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
	public Pair<Integer, Integer> getCurrentGridShape() {
		return this.currentGridShape;
	}
	
	@Override
	public Map<String, Map<Pair<Integer, Integer>, Difficulty>> getAllDifficulties() {
		return Map.copyOf(this.difficulties);
	} 

	@Override
	public void updateCurrentDifficulty(final Difficulty difficulty) {
		this.currentDifficulty = difficulty;
	}

	@Override
	public void updateCurrentGridShape(final Pair<Integer, Integer> gridShape) {
		this.currentGridShape = gridShape;
	}

}