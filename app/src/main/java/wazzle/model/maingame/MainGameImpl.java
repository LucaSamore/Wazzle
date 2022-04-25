package wazzle.model.maingame;

import com.google.gson.annotations.Expose;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import wazzle.model.maingame.grid.Grid;
import wazzle.model.maingame.letter.Letter;

/** This class represents an implementation for {@link wazzle.model.maingame.MainGame} */
public final class MainGameImpl implements MainGame {

  @Expose private final Set<String> wordsFound;

  @Expose private final LocalDateTime dateTime;

  @Expose private long duration;

  @Expose private int currentScore;

  private final Grid grid;
  private int failedAttempts;

  /**
   * Construct a new MainGameImpl object
   *
   * @param grid the {@link Grid} we are going to play with
   * @param duration a {@code long} describing how long does the game last
   */
  public MainGameImpl(final Grid grid, final long duration) {
    this.grid = grid;
    this.duration = duration;
    this.wordsFound = new HashSet<>();
    this.dateTime = LocalDateTime.now();
    this.failedAttempts = 0;
    this.currentScore = 0;
  }

  /** {@inheritDoc} */
  @Override
  public boolean tryWord(final String word) {
    final var attempt = Optional.of(word).filter(w -> this.wordsToBeFound().contains(w));

    if (attempt.isPresent()) {
      this.updateFailedAttempts(f -> f = 0);
      this.addWordFound(attempt.get());
      this.addWordScore(attempt.get());
      return true;
    }

    this.updateFailedAttempts(f -> f + 1);
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public Set<String> wordsFound() {
    return Set.copyOf(this.wordsFound);
  }

  /** {@inheritDoc} */
  @Override
  public Set<String> wordsToBeFound() {
    return this.grid.getWordsCanBeFound().stream()
        .filter(w -> !this.alreadyFound(w))
        .collect(Collectors.toSet());
  }

  /** {@inheritDoc} */
  @Override
  public Set<String> wordsCanBeFound() {
    return Set.copyOf(this.grid.getWordsCanBeFound());
  }

  /** {@inheritDoc} */
  @Override
  public Set<Letter> lettersInGrid() {
    return Set.copyOf(this.grid.getLetters());
  }

  /** {@inheritDoc} */
  @Override
  public Grid getGrid() {
    return this.grid;
  }

  /** {@inheritDoc} */
  @Override
  public LocalDateTime getDateTime() {
    return this.dateTime;
  }

  /** {@inheritDoc} */
  @Override
  public int getFailedAttempts() {
    return this.failedAttempts;
  }

  /** {@inheritDoc} */
  @Override
  public void setCurrentScore(final int newScore) {
    this.currentScore = newScore;
  }

  /** {@inheritDoc} */
  @Override
  public int getCurrentScore() {
    return this.currentScore;
  }

  /** {@inheritDoc} */
  public long getDuration() {
    return this.duration;
  }

  /** {@inheritDoc} */
  @Override
  public int getScoreFromWord(final String word) {
    return word.chars().mapToObj(c -> (char) c).collect(Collectors.toList()).stream()
        .map(c -> this.charsWithScore(word).get(c))
        .collect(Collectors.toList())
        .stream()
        .reduce(0, (x, y) -> x + y);
  }

  private void addWordFound(final String word) {
    this.wordsFound.add(word);
  }

  private void addWordScore(final String word) {
    word.chars()
        .mapToObj(c -> (char) c)
        .forEach(
            c -> {
              this.lettersInGrid().stream()
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

  private Map<Character, Integer> charsWithScore(final String word) {
    Map<Character, Integer> result = new HashMap<>();
    this.lettersInGrid().stream()
        .forEach(
            l -> {
              if (!result.keySet().contains(l.getContent())) {
                result.put(l.getContent(), l.getScore());
              }
            });
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + currentScore;
    result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
    result = prime * result + (int) (duration ^ (duration >>> 32));
    result = prime * result + failedAttempts;
    result = prime * result + ((grid == null) ? 0 : grid.hashCode());
    result = prime * result + ((wordsFound == null) ? 0 : wordsFound.hashCode());
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    MainGameImpl other = (MainGameImpl) obj;
    if (currentScore != other.currentScore) return false;
    if (dateTime == null) {
      if (other.dateTime != null) return false;
    } else if (!dateTime.equals(other.dateTime)) return false;
    if (duration != other.duration) return false;
    if (failedAttempts != other.failedAttempts) return false;
    if (grid == null) {
      if (other.grid != null) return false;
    } else if (!grid.equals(other.grid)) return false;
    if (wordsFound == null) {
      if (other.wordsFound != null) return false;
    } else if (!wordsFound.equals(other.wordsFound)) return false;
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "Wazzle MainGame info: "
        + System.lineSeparator()
        + "wordsFound: "
        + this.wordsFound
        + System.lineSeparator()
        + "score: "
        + this.currentScore
        + System.lineSeparator()
        + "dateTime: "
        + this.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        + System.lineSeparator()
        + "failedAttempts: "
        + this.failedAttempts
        + System.lineSeparator()
        + "duration: "
        + this.duration
        + System.lineSeparator();
  }
}
