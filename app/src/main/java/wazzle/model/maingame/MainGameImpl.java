package wazzle.model.maingame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import java.util.Optional;

import wazzle.model.common.BonusManager;

public class MainGameImpl implements MainGame {
	
	//TODO: Add bonus handling
	
	private static final int ATTEMPTS_BEFORE_HELP = 5;
	private final Grid grid;
	private final Set<String> wordsFound;
	private final LocalDateTime dateTime;
	private final BonusManager bonusManager;
	private int failedAttempts;
	private long duration;
	private double currentScore;
	
	public MainGameImpl(final Grid grid, final long duration, final BonusManager bonusManager) {
		this.grid = grid;
		this.duration = duration;
		this.wordsFound = new HashSet<>();
		this.dateTime = LocalDateTime.now();
		this.bonusManager = bonusManager;
		this.failedAttempts = 0;
		this.currentScore = 0;
	}
	
	// return true if word is in grid.getWordsCanBeFound() AND word is not in this.wordsFound yet --> this.updateFailedAttempts(f -> f = 0)
	// performs the add operation as well
	// false otherwise --> this.updateFailedAttempts(f -> f + 1)
	
	@Override
	public boolean attempt(final String word) {
		Optional.of(word)
				.filter(w -> this.grid.getWordsCanBeFound().contains(word) && !this.alreadyFound(word))
				.ifPresent(w -> {
					this.updateFailedAttempts(f -> f = 0);
					this.addWordFound(w);
				});
		
		this.updateFailedAttempts(f -> f + 1);
		return false;	
	}
	
	@Override
	public void useScoreBonus() {
		this.currentScore = this.bonusManager.applyScoreBonus(currentScore, this.grid.getTotalScore());
	}
	
	@Override
	public Set<String> useWordBonus() {
		return this.bonusManager.applyWordBonus(this.wordsToBeFound());
	}

	@Override
	public long useTimeBonus(final long currentTime) {
		return this.bonusManager.applyTimeBonus(currentTime);
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
		//return this.areWeDone();
	}
	
	private boolean alreadyFound(final String word) {
		return this.wordsFound.contains(word);
	}
	
	private Set<String> wordsToBeFound() {
		return this.grid.getWordsCanBeFound()
				.stream()
				.filter(w -> !this.wordsFound.contains(w))
				.collect(Collectors.toSet());
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
				"Grid: " + System.lineSeparator() + this.grid.toString() + System.lineSeparator() +
				"wordsFound: " + this.wordsFound + System.lineSeparator() +
				"dateTime: " + this.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + System.lineSeparator() +
				"failedAttempts: " + this.failedAttempts + System.lineSeparator() +
				"duration: " + this.duration + System.lineSeparator();
	}
}
