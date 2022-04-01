package wazzle.model.maingame;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class MainGameImpl implements MainGame {
	
	//TODO: Add bonus handling
	
	private static final int ATTEMPTS_BEFORE_HELP = 5;
	private final Grid grid;
	private final Set<String> wordsFound;
	private final LocalDateTime dateTime;
	private int failedAttempts;
	private long duration;
	//private Bonus bonus;
	
	public MainGameImpl(final Grid grid, final long duration) {
		this.grid = grid;
		this.duration = duration;
		this.wordsFound = new HashSet<>();
		this.dateTime = LocalDateTime.now();
		this.failedAttempts = 0;
	}
	
	//TODO: implement this method
	@Override
	public boolean attempt(final String word) {
		throw new UnsupportedOperationException();
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
		return Set.copyOf(this.getLettersInGrid());
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
	
	public long getDuration() {
		return this.duration;
	}
	
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}
	
	private boolean addWordFound(final String word) {
		this.wordsFound.add(word);
		return this.areWeDone();
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
		return Objects.hash(dateTime, duration, failedAttempts, grid, wordsFound);
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
		return Objects.equals(dateTime, other.dateTime) && duration == other.duration
				&& failedAttempts == other.failedAttempts && Objects.equals(grid, other.grid)
				&& Objects.equals(wordsFound, other.wordsFound);
	}
	
	@Override
	public String toString() {
		return "MainGameImpl [grid=" + grid + ", wordsFound=" + wordsFound + ", dateTime=" + dateTime
				+ ", failedAttempts=" + failedAttempts + ", duration=" + duration + "]";
	}
}
