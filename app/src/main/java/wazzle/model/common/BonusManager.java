package wazzle.model.common;

import java.util.Set;
import java.util.function.UnaryOperator;
import wazzle.model.common.bonus.ScoreBonus;
import wazzle.model.common.bonus.TimeBonus;
import wazzle.model.common.bonus.WordBonus;

/**
 * This interface provides the methods for {@link ScoreBonus}, {@link TimeBonus} and {@link
 * WordBonus} handling.
 */
public interface BonusManager {

  /**
   * Updates the {@link ScoreBonus} quantity.
   *
   * @param operation The operation that must be done when the {@link ScoreBonus} is used by the
   *     user.
   */
  void updateScoreBonusQuantity(UnaryOperator<Integer> operation);

  /**
   * Updates the {@link WordBonus} quantity.
   *
   * @param operation The operation that must be done when the {@link WordBonus} is used by the
   *     user.
   */
  void updateWordBonusQuantity(UnaryOperator<Integer> operation);

  /**
   * Updates the {@link TimeBonus} quantity.
   *
   * @param operation The operation that must be done when the {@link TimeBonus} is used by the
   *     user.
   */
  void updateTimeBonusQuantity(UnaryOperator<Integer> operation);

  /**
   * Gives the quantity of {@link ScoreBonus}.
   *
   * @return int which represent the quantity of {@link ScoreBonus}.
   */
  int getScoreBonusQuantity();

  /**
   * Gives the quantity of {@link WordBonus}.
   *
   * @return int which represent the quantity of {@link WordBonus}.
   */
  int getWordBonusQuantity();

  /**
   * Gives the quantity of {@link TimeBonus}.
   *
   * @return int which represent the quantity of TimeBonus.
   */
  int getTimeBonusQuantity();

  /**
   * Apply the implications of the {@link ScoreBonus}.
   *
   * @param currentScore The current score obtained by the user.
   * @param gridTotalScore The sum of all letter scores in the grid.
   * @return int which represents the updated score.
   */
  int applyScoreBonus(int currentScore, int gridTotalScore);

  /**
   * Apply the implications of the {@link WordBonus}.
   *
   * @param toFoundWords The words that the user haven't already found.
   * @return Set<String> the extracted words that will be suggested to the user.
   */
  Set<String> applyWordBonus(Set<String> toFoundWords);

  /**
   * Apply the implications of the {@link TimeBonus}.
   *
   * @param currentTime The current time remaining to the user to found other words.
   * @return long which represent the incremented time.
   */
  long applyTimeBonus(long currentTime);

  /**
   * Extract randomly a bonus and updates its quantity.
   *
   * @return String the bonus name.
   */
  String extractBonus();
}
