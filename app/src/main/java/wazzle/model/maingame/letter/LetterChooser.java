package wazzle.model.maingame.letter;

import java.util.EnumMap;
import java.util.List;
import javafx.util.Pair;

/**
 * LetterChooser choose, based on a specific strategy, an amount of Characters, with their
 * frequency, in order to fill the game grid.
 */
public interface LetterChooser {

  /**
   * Choose the Character that have to populate the grid. The criterion on choice of the Characters
   * is that the grid have to contain a number of Character for every Range proportionally to the
   * weight of every Range. The result of the choice is passed to the notifyFromChooser Mediator
   * method.
   */
  public void choose();

  /**
   * Choose the Character that have to populate the grid. The criterion on choice of the Characters
   * is that the grid have to contain a number of Character for every Range proportionally to the
   * weight of every Range, but it doesn't use a Mediator. It's made for tests only.
   *
   * @return EnumMap<> which contains the chosen Characters for every Range.
   */
  public EnumMap<Range, List<Pair<Character, Double>>> chooseForTests();
}
