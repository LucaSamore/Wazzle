package wazzle.controller.maingame;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import javafx.util.Pair;
import wazzle.controller.common.WazzleController;
import wazzle.controller.maingame.timer.GameTimer;
import wazzle.controller.maingame.timer.GameTimerImpl;
import wazzle.model.common.Dictionary;
import wazzle.model.maingame.MainGame;
import wazzle.model.maingame.grid.Difficulty;

/** This class is a concrete implementation for {@link MainGameController} */
public final class MainGameControllerImpl implements MainGameController {
  private static final int ATTEMPTS_BEFORE_HELP = 5;
  private final WazzleController mainController;
  private Optional<MainGame> game;
  private final GameTimer timer;

  /**
   * Construct a new MainGameControllerImpl.
   *
   * @param mainController a {@link WazzleController} object.
   */
  public MainGameControllerImpl(final WazzleController mainController) {
    this.mainController = mainController;
    this.game = Optional.empty();
    this.timer =
        new GameTimerImpl(
            (long) this.mainController.getCurrentDifficulty().getTimeInMilliseconds() / 1000);
  }

  /** {@inheritDoc} */
  @Override
  public Optional<MainGame> getGame() {
    return this.game;
  }

  /** {@inheritDoc} */
  @Override
  public void startNewGame(
      final Dictionary dataset,
      final Pair<Integer, Integer> gridShape,
      final Difficulty difficulty) {
    if (this.game.isEmpty()) {
      this.game = Optional.of(this.mainController.startNewMainGame(dataset, gridShape, difficulty));
    }
  }

  /** {@inheritDoc} */
  @Override
  public void endGame() {
    this.game.ifPresent(
        g -> {
          this.saveGame();
          this.game = Optional.empty();
        });
  }

  /** {@inheritDoc} */
  @Override
  public boolean attempt(final String word) {
    if (this.game.isPresent()) {
      return this.game.get().tryWord(word);
    }
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public boolean needHelp() {
    return this.game.get().getFailedAttempts() == ATTEMPTS_BEFORE_HELP;
  }

  /** {@inheritDoc} */
  @Override
  public boolean areWeDone() {
    return this.game.get().wordsFound().size() == this.game.get().wordsCanBeFound().size();
  }

  /** {@inheritDoc} */
  @Override
  public void useScoreBonus() {
    this.game
        .get()
        .setCurrentScore(
            this.mainController
                .getBonusManager()
                .applyScoreBonus(
                    this.game.get().getCurrentScore(), this.game.get().getGrid().getTotalScore()));
  }

  /** {@inheritDoc} */
  @Override
  public void useTimeBonus() {
    this.timer.updateTime(
        this.mainController.getBonusManager().applyTimeBonus(this.timer.getRemainingTime()));
  }

  /** {@inheritDoc} */
  @Override
  public Set<String> useWordBonus() {
    return this.mainController.getBonusManager().applyWordBonus(this.game.get().wordsToBeFound());
  }

  /** {@inheritDoc} */
  @Override
  public WazzleController getMainController() {
    return this.mainController;
  }

  /** {@inheritDoc} */
  @Override
  public String longestWord() {
    return this.game.get().wordsFound().stream()
        .max(Comparator.comparingInt(String::length))
        .orElse("");
  }

  /** {@inheritDoc} */
  @Override
  public String highestScoreWord() {
    int max =
        this.game.get().wordsFound().stream()
            .map(w -> this.game.get().getScoreFromWord(w))
            .max(Comparator.comparingInt(Integer::intValue))
            .orElse(0);

    return this.game.get().wordsFound().stream()
        .filter(w -> max == this.game.get().getScoreFromWord(w))
        .findFirst()
        .get();
  }

  /** {@inheritDoc} */
  @Override
  public GameTimer getTimer() {
    return this.timer;
  }

  private void saveGame() {
    this.mainController.addMainGametoHistory(this.game.get());
  }
}
