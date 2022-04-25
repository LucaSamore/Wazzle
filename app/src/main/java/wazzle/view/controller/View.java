package wazzle.view.controller;

import java.io.IOException;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * This class represents an abstraction for view objects. It provides a set of abstract methods for
 * set up the graphics elements, build the actual view and switch to the next view.
 *
 * @param <T> the type of the application controller that the concrete view uses.
 */
public abstract class View<T> {
  protected Stage stage;
  protected DoubleProperty visualUnit;
  protected T controller;

  /**
   * Initializes the concrete view.
   *
   * @return void
   */
  public void initialize() {
    this.buildView();
  }

  /**
   * Switch to the next view when an {@link ActionEvent} has occurred.
   *
   * @param event the {@code ActionEvent} occurred.
   * @return void
   * @throws IOException
   */
  public abstract void nextScene(final ActionEvent event) throws IOException;

  protected abstract void buildView();

  protected abstract void setGraphics();
}
