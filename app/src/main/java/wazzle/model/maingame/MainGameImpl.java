package wazzle.model.maingame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;

import java.util.Optional;

import wazzle.model.common.BonusManager;

public class MainGameImpl implements MainGame {
	private static final int ATTEMPTS_BEFORE_HELP = 5;
	private final Grid grid;
	private int failedAttempts;	
	
	@Expose
	private final Set<String> wordsFound;
	
	@Expose
	private final LocalDateTime dateTime;
	
	@Expose
	private long duration;
	
	@Expose
	private double currentScore;
	
	public MainGameImpl(final Grid grid, final long duration) {
		this.grid = grid;
		this.duration = duration;
		this.wordsFound = new HashSet<>();
		this.dateTime = LocalDateTime.now();
		this.failedAttempts = 0;
		this.currentScore = 0;
	}
	
	// return true if word is in grid.getWordsCanBeFound() AND word is not in this.wordsFound yet --> this.updateFailedAttempts(f -> f = 0)
	// performs the add operation as well
	// false otherwise --> this.updateFailedAttempts(f -> f + 1)
	
	@Override
	public boolean attempt(final String word) {
		final var attempt = Optional.of(word).filter(w -> this.wordsToBeFound().contains(w));
		
		if(attempt.isPresent()) {
			this.updateFailedAttempts(f -> f = 0);
			this.addWordFound(attempt.get());
			return true;
		}
		
		this.updateFailedAttempts(f -> f + 1);
		return false;	
	}
	
	@Override
	public Set<String> wordsToBeFound() {
		return this.grid.getWordsCanBeFound()
				.stream()
				.filter(w -> !this.alreadyFound(w))
				.collect(Collectors.toSet());
	}
	
	@Override
	public boolean needHelp() {
		return this.failedAttempts == ATTEMPTS_BEFORE_HELP;
	}
	
	@Override
	public boolean areWeDone() {
		return this.wordsFound.size() == this.grid.getWordsCanBeFound().size();
	}
	
	@Override
	public Set<Letter> getLettersInGrid() {
		return Set.copyOf(this.grid.getLetters());
	}
	
	@Override
	public Set<String> getWordsFound() {
		return Set.copyOf(this.wordsFound);
	}
	
	@Override
	public Set<String> getWordsCanBeFound() {
		return Set.copyOf(this.grid.getWordsCanBeFound());
	}
	
	@Override
	public int getFailedAttempts() {
		return this.failedAttempts;
	}
	
	@Override
	public double getCurrentScore() {
		return this.currentScore;
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}
	
	private void addWordFound(final String word) {
		this.wordsFound.add(word);
	}
	
	private boolean alreadyFound(final String word) {
		return this.wordsFound.contains(word);
	}
	
	private void updateFailedAttempts(final UnaryOperator<Integer> operation) {
		this.failedAttempts = operation.apply(this.failedAttempts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentScore, dateTime, duration, failedAttempts, wordsFound);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MainGameImpl other = (MainGameImpl) obj;
		return Double.doubleToLongBits(currentScore) == Double.doubleToLongBits(other.currentScore)
				&& Objects.equals(dateTime, other.dateTime) && duration == other.duration
				&& failedAttempts == other.failedAttempts && Objects.equals(wordsFound, other.wordsFound);
	}

	@Override
	public String toString() {
		return "Wazzle MainGame info: " + System.lineSeparator() +
				"wordsFound: " + this.wordsFound + System.lineSeparator() +
				"score: " + this.currentScore + System.lineSeparator() +
				"dateTime: " + this.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + System.lineSeparator() +
				"failedAttempts: " + this.failedAttempts + System.lineSeparator() +
				"duration: " + this.duration + System.lineSeparator();
	}
}
