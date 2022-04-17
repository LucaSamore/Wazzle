package wazzle.model.maingame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.annotations.Expose;

import java.util.Optional;

import wazzle.model.common.BonusManager;

public class MainGameImpl implements MainGame {
	
	@Expose
	private final Set<String> wordsFound;
	
	@Expose
	private final LocalDateTime dateTime;
	
	@Expose
	private long duration;
	
	@Expose
	private double currentScore;
	
	private final Grid grid;
	private int failedAttempts;	
	
	public MainGameImpl(final Grid grid, final long duration) {
		this.grid = grid;
		this.duration = duration;
		this.wordsFound = new HashSet<>();
		this.dateTime = LocalDateTime.now();
		this.failedAttempts = 0;
		this.currentScore = 0;
	}
	
	@Override
	public boolean tryWord(final String word) {
		final var attempt = Optional.of(word).filter(w -> this.wordsToBeFound().contains(w));
		
		if(attempt.isPresent()) {
			this.updateFailedAttempts(f -> f = 0);
			this.addWordFound(attempt.get());
			this.addWordScore(attempt.get());
			return true;
		}
		
		this.updateFailedAttempts(f -> f + 1);
		return false;	
	}
	
	@Override
	public Set<String> wordsFound() {
		return Set.copyOf(this.wordsFound);
	}
	
	@Override
	public Set<String> wordsToBeFound() {
		return this.grid.getWordsCanBeFound()
				.stream()
				.filter(w -> !this.alreadyFound(w))
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<String> wordsCanBeFound() {
		return Set.copyOf(this.grid.getWordsCanBeFound());
	}
	
	@Override
	public Set<Letter> lettersInGrid() {
		return Set.copyOf(this.grid.getLetters());
	}
	
	@Override
	public Grid getGrid() {
		return this.grid;
	}
	
	@Override
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}
	
	@Override
	public int getFailedAttempts() {
		return this.failedAttempts;
	}
	
	@Override
	public void setCurrentScore(final double newScore) {
		this.currentScore = newScore;
	}
	
	@Override
	public double getCurrentScore() {
		return this.currentScore;
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	private void addWordFound(final String word) {
		this.wordsFound.add(word);
	}
	
	private void addWordScore(final String word) {
        word.chars()
        	.mapToObj(c -> (char)c)
        	.forEach(c -> {
        		this.lettersInGrid()
        			.stream()
        			.filter(l -> l.getContent() == c)
        			.findFirst()
        			.ifPresent(l -> this.currentScore += l.getScore());
        });
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

	@Override
	public double getScoreFromWord(final String word) {
        return word.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.toList())
                .stream()
                .map(c -> this.charsWithScore(word).get(c))
                .collect(Collectors.toList())
                .stream()
                .reduce(0.0, (x, y) -> x + y);
	}
	
	private Map<Character, Double> charsWithScore(final String word) {
		Map<Character, Double> result = new HashMap<>();
		this.lettersInGrid()
			.stream()
			.forEach(l -> { if (!result.keySet().contains(l.getContent())) {
				result.put(l.getContent(), l.getScore());
			}});
		return result;
	}
}
