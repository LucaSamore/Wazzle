package wazzle.controller.maingame;

import javafx.util.Pair;
import wazzle.model.maingame.Difficulty;

public class SettingsImpl implements Settings {
	
	private static final Difficulty DEFAULT_DIFFICULTY = new Difficulty(126, 250, 240000L);
	private static final Pair<Integer, Integer> DEFAULT_GRID_SHAPE = new Pair<>(4,4);
	private Difficulty difficulty;
	private Pair<Integer, Integer> gridShape;
	
	public SettingsImpl() {
		this.difficulty = DEFAULT_DIFFICULTY;
		this.gridShape = DEFAULT_GRID_SHAPE;
	}

	@Override
	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	@Override
	public Pair<Integer, Integer> getGridShape() {
		return this.gridShape;
	}

	@Override
	public void updateDifficulty(final Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public void updateGridShape(final Pair<Integer, Integer> gridShape) {
		this.gridShape = gridShape;
	} 

}
