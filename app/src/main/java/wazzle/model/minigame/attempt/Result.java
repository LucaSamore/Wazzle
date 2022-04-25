package wazzle.model.minigame.attempt;

/**
 * Represent current status of each letter belonging to Minigame known as WordElement.
 *
 * @see WordElement
 */
public enum Result {
  NOT_SET(-1),
  CORRECT(0),
  CORRECT_WRONG_PLACE(1),
  WRONG(2);

  private final int state;

  private Result(final int state) {
    this.state = state;
  }

  public int getState() {
    return state;
  }
}
