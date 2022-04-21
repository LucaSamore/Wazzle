package wazzle.controller.maingame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.gson.annotations.Expose;

import wazzle.model.maingame.Difficulty;

public class SettingsControllerImpl implements SettingsController {
	@Expose
	private List<Difficulty> difficulties;
	
	@Expose
	private Difficulty currentDifficulty;

	public SettingsControllerImpl(@NonNull final List<Difficulty> difficulties, @NonNull final Difficulty currentDifficulty) {
		this.difficulties = new ArrayList<>(difficulties);
		this.currentDifficulty = currentDifficulty;
	}

	@Override
	public List<Difficulty> getAllDifficulties() {
		return List.copyOf(this.difficulties);
	}

	@Override
	public Difficulty getCurrentDifficulty() {
		return this.currentDifficulty;
	}

	@Override
	public void setCurrentDifficulty(final Difficulty difficulty) {
		Objects.requireNonNull(difficulty);
		this.currentDifficulty = difficulty;
	}

	@Override
	public Optional<Difficulty> getDifficultyByNameAndShape(final String name, final int shape) {
		return this.difficulties.stream()
				.filter(d -> d.getDifficultyName().equals(name) && d.getGridShape() == shape)
				.findAny();
	}

}