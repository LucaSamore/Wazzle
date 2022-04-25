package wazzle.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This interface provides static methods for switching a scene. The switch can happen after an
 * {@link ActionEvent} has occurred. This interface uses {@link Loader} for loading the next scene.
 */
public interface SceneSwitcher {

  /**
   * Switches to a different scene given an {@link ActionEvent}, the controller for the next view
   * and the path to the FXML file of the next view.
   *
   * @param event the {@code ActionEvent} object.
   * @param controller the controller for the next view.
   * @param path a {@code String} which represents the path of the next view.
   * @param <X> the type of the controller object.
   * @return void
   * @throws IOException
   */
  static <X> void switchScene(final ActionEvent event, final X controller, final String path)
      throws IOException {
    final var node = (Node) event.getSource();
    final var stage = (Stage) node.getScene().getWindow();
    final var element = Loader.<X, Parent>loadFXMLElement(controller, path);
    final var scene = new Scene(element, stage.getScene().getWidth(), stage.getScene().getHeight());
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Switches to a different scene given an {@link Stage}, the controller for the next view and the
   * path to the FXML file of the next view.
   *
   * @param stage the {@code Stage} object.
   * @param controller the controller for the next view.
   * @param path a {@code String} which represents the path of the next view.
   * @param <X> the type of the controller object.
   * @return void
   * @throws IOException
   */
  static <X> void switchScene(final Stage stage, final X controller, final String path)
      throws IOException {
    final var element = Loader.<X, Parent>loadFXMLElement(controller, path);
    final var scene = new Scene(element, stage.getScene().getWidth(), stage.getScene().getHeight());
    stage.setScene(scene);
    stage.show();
  }
}
