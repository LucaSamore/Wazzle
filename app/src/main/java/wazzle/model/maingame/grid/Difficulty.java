package wazzle.model.maingame.grid;

import com.google.gson.annotations.Expose;
import java.util.Objects;

/**
 * This class represents a configuration for {@link wazzle.model.maingame.MainGame} difficulty. More
 * precisely, it encapsulates all the parameters used for improving the difficulty of a {@link
 * wazzle.model.maingame.grid.Grid}. It also provides a default configuration via {@link
 * wazzle.model.maingame.grid.Difficulty#getDefault()}
 */
public final class Difficulty {
  private static final String DEFAULT_NAME = DifficultyNames.EASY.getName();
  private static final int DEFAULT_SHAPE = 4;
  private static final int DEFAULT_LOWER_BOUND = 76;
  private static final int DEFAULT_UPPER_BOUND = 200;
  private static final long DEFAULT_TIME = 240_000L;

  @Expose private final String difficultyName;

  @Expose private final int gridShape;

  @Expose private final int lowerBound;

  @Expose private final int upperBound;

  @Expose private final long time;

  /**
   * Construct a new Difficulty object
   *
   * @param difficultyName a {@code String} which represents the name of this difficulty
   *     configuration. This name comes from {@link wazzle.model.maingame.grid.DifficultyNames}.
   * @param gridShape an integer describing the shape of the {@link wazzle.model.maingame.grid.Grid}
   * @param lowerBound an integer describing the lower bound
   * @param upperBound an integer describing the upper bound
   * @param time a long describing how long does a match last (in milliseconds)
   */
  public Difficulty(
      final String difficultyName,
      final int gridShape,
      final int lowerBound,
      final int upperBound,
      final long time) {
    this.difficultyName = difficultyName;
    this.gridShape = gridShape;
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
    this.time = time;
  }

  /**
   * Returns the name of this difficulty configuration.
   *
   * @return a {@code String} which represents the name of this difficulty configuration.
   */
  public String getDifficultyName() {
    return this.difficultyName;
  }

  /**
   * Returns the shape of this difficulty configuration.
   *
   * @return a {@code int} which represents the shape of this difficulty configuration.
   */
  public int getGridShape() {
    return this.gridShape;
  }

  /**
   * Returns the lower bound of this difficulty configuration.
   *
   * @return a {@code int} which represents the lower bound of this difficulty configuration.
   */
  public int getLowerBound() {
    return this.lowerBound;
  }

  /**
   * Returns the upper bound of this difficulty configuration.
   *
   * @return a {@code int} which represents the upper bound of this difficulty configuration.
   */
  public int getUpperBound() {
    return this.upperBound;
  }

  /**
   * Returns the time in milliseconds of this difficulty configuration.
   *
   * @return a {@code long} which represents the time of this difficulty configuration.
   */
  public long getTimeInMilliseconds() {
    return this.time;
  }

  /**
   * Returns the default configuration
   *
   * @return a {@code wazzle.model.maingame.Difficulty} which represents the default configuration.
   */
  public static Difficulty getDefault() {
    return new Difficulty(
        DEFAULT_NAME, DEFAULT_SHAPE, DEFAULT_LOWER_BOUND, DEFAULT_UPPER_BOUND, DEFAULT_TIME);
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(difficultyName, gridShape, lowerBound, time, upperBound);
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Difficulty other = (Difficulty) obj;
    return Objects.equals(difficultyName, other.difficultyName)
        && gridShape == other.gridShape
        && lowerBound == other.lowerBound
        && time == other.time
        && upperBound == other.upperBound;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "Difficulty [difficultyName="
        + difficultyName
        + ", gridShape="
        + gridShape
        + ", lowerBound="
        + lowerBound
        + ", upperBound="
        + upperBound
        + ", time="
        + time
        + "]";
  }
}
