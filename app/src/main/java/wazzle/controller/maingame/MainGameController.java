package wazzle.controller.maingame;

import java.util.Optional;
import java.util.Set;
import javafx.util.Pair;
import wazzle.controller.common.WazzleController;
import wazzle.controller.maingame.timer.GameTimer;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.grid.Difficulty;

/**
 * This interface represents the controller for {@link MainGame}. It provides methods for handling
 * every state of the game and gain information about it.
 */
public interface MainGameController {

  /**
   * Returns an {@code Optional} describing the current game.
   *
   * @return an {@code Optional} object.
   * @see java.util.Optional
   */
  Optional<MainGame> getGame();

  /**
   * This method starts a new game session by requesting a new {@link MainGame} instance.
   * @param dataset a {@link Dictionary} object used for creating the {@link Grid}
   * @param gridShape a {@code Pair<Integer,Integer> object that represents the shape of the {@link Grid}
   * @param difficulty a {@link Difficulty} object chosen by the user
   * @return void.
   */
  void startNewGame(Dictionary dataset, Pair<Integer, Integer> gridShape, Difficulty difficulty);

  /**
   * This method handles the end game situation.
   *
   * @return void.
   */
  void endGame();

  /**
   * This method calls {@link MainGame#tryWord(String)} and returns his result.
   *
   * @param word {@code String} the word to try
   * @return {@code true} if {@link MainGame#tryWord(String)} returns {@code true}, {@code false}
   *     otherwise.
   */
  boolean attempt(String word);

  /**
   * This method tells if the user needs help by checking how many failed attemps he got in row.
   *
   * @return {@code true} if {@link MainGame#getFailedAttempts()} equals a certain number, {@code
   *     false} otherwise.
   */
  boolean needHelp();

  /**
   * This method checks if the game is over.
   *
   * @return {@code true} if {@link MainGame#wordsFound()} == {@link MainGame#wordsCanBeFound()},
   *     {@code false} otherwise.
   */
  boolean areWeDone();

  /**
   * Consumes a score bonus. It increases {@link MainGame#getCurrentScore()}.
   *
   * @return void.
   */
  void useScoreBonus();

  /**
   * Consumes a time bonus. It increases the timer by using the {@link GameTimer} object.
   *
   * @return void.
   */
  void useTimeBonus();

  /**
   * Consumes a word bonus.
   *
   * @return a {@code Set<String>} containing some suggested words inside {@link
   *     MainGame#wordsToBeFound()}.
   * @see java.util.Set
   */
  Set<String> useWordBonus();

  /**
   * Returns the {@link WazzleController} object.
   *
   * @return a {@code WazzleController} object.
   */
  WazzleController getMainController();

  /**
   * Returns the longest word inside {@link MainGame#wordsFound()}.
   *
   * @return a {@code String} representing the longest word.
   */
  String longestWord();

  /**
   * Returns the word with the highest score inside {@link MainGame#wordsFound()}.
   *
   * @return a {@code String} representing the word with the highest score.
   */
  String highestScoreWord();

  /**
   * Returns the {@code GameTimer} object.
   *
   * @return a {@link GameTimer} object.
   */
  GameTimer getTimer();
}
