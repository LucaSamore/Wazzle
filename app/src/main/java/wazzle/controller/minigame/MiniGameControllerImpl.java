package wazzle.controller.minigame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import wazzle.controller.common.WazzleController;
import wazzle.model.minigame.MiniGame;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.MiniGameImpl;
import wazzle.model.minigame.SavedMiniGame;
import wazzle.model.minigame.attempt.MiniGameWord;
import wazzle.model.minigame.attempt.Result;

public class MiniGameControllerImpl implements MiniGameController {

  private MiniGame currentMinigame;
  private WazzleController wazzleController;
  private MiniGameWord currentMiniGameWord;

  public MiniGameControllerImpl(final WazzleController wazzleController) {
    this.wazzleController = wazzleController;
  }

  /** {@inheritDoc} */
  @Override
  public void startGame() throws IOException {
    this.loadMiniGame()
        .ifPresentOrElse(
            (savedMiniGame -> {
              this.currentMinigame = new MiniGameImpl(savedMiniGame.getSavedTargetWord());
              this.currentMinigame.loadMiniGame(savedMiniGame);
            }),
            () -> this.currentMinigame = this.newMiniGame());

    if (loadMiniGame().isPresent()) {
      this.wazzleController.deleteEndedMiniGame();
    }
  }

  /** {@inheritDoc} */
  @Override
  public void saveMiniGame() throws IOException {
    this.wazzleController.saveMiniGame(currentMinigame.takeMiniGameSnapshot());
  }

  /** Loads a minigame. */
  private Optional<SavedMiniGame> loadMiniGame() throws IOException {
    return this.wazzleController.getLastMinigame();
  }

  /** {@inheritDoc} */
  @Override
  public MiniGameWord guessWord(String guessedWord) {
    this.currentMiniGameWord = currentMinigame.computeResult(guessedWord);
    return this.currentMiniGameWord;
  }

  /** {@inheritDoc} */
  @Override
  public Optional<String> obtainedBonus() throws IOException {
    if (this.currentMinigame.getGameState() == State.WON) {
      var currentBonusName = this.wazzleController.gainBonus();
      this.wazzleController.saveBonuses();
      return Optional.of(currentBonusName);
    }
    return Optional.empty();
  }

  /** {@inheritDoc} */
  @Override
  public int getCurrentAttemptsNumber() {
    return currentMinigame.getCurrentAttemptsNumber();
  }

  /** {@inheritDoc} */
  @Override
  public List<MiniGameWord> getGuessedMinigameWordsSoFar() {
    return List.copyOf(currentMinigame.getAllGuessedWords());
  }

  /** {@inheritDoc} */
  @Override
  public int getWordLength() {
    return currentMinigame.getWordLength();
  }

  /** {@inheritDoc} */
  @Override
  public int getMaxAttemptsNumber() {
    return currentMinigame.getMaxAttemptsNumber();
  }

  /** {@inheritDoc} */
  @Override
  public int getStateOfCurrentMinigame() {
    return currentMinigame.getGameState().getState();
  }

  /** {@inheritDoc} */
  @Override
  public String getTargetWord() {
    return this.currentMinigame.getTargetWord();
  }

  /** {@inheritDoc} */
  @Override
  public WazzleController getMainController() {
    return this.wazzleController;
  }

  /** {@inheritDoc} */
  @Override
  public int getLetterResultAtIndex(int index) {
    return this.currentMiniGameWord.getCompositeWord().get(index).getResult();
  }

  /** {@inheritDoc} */
  @Override
  public char getLetterCharAtIndex(int index) {
    return this.currentMiniGameWord.getCompositeWord().get(index).getCharacter();
  }

  /** {@inheritDoc} */
  @Override
  public List<Character> getAllWrongLetters() {
    List<Character> wrongLetters = new ArrayList<>();
    this.currentMinigame
        .getAllGuessedWords()
        .forEach(
            word ->
                word.getCompositeWord()
                    .forEach(
                        letter -> {
                          if (letter.getResult() == Result.WRONG.getState()) {
                            wrongLetters.add(letter.getCharacter());
                          }
                        }));
    return List.copyOf(wrongLetters);
  }

  /** {@inheritDoc} */
  @Override
  public boolean isTheLetterCorrectInAnotherPlace(char letter) {
    return !this.currentMiniGameWord.getCompositeWord().stream()
        .filter(wordElement -> wordElement.getCharacter() == letter)
        .filter(
            wordElement2 ->
                wordElement2.getResult() == Result.CORRECT.getState()
                    || wordElement2.getResult() == Result.CORRECT_WRONG_PLACE.getState())
        .collect(Collectors.toList())
        .isEmpty();
  }

  /** Starts a new minigame. */
  private MiniGame newMiniGame() {
    return this.wazzleController.startNewMiniGame(this.wazzleController.getWordsDispenser());
  }
}
