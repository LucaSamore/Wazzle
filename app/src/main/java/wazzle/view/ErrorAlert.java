package wazzle.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * This is an utility class that provides a static method for showing an {@link Alert}
 * when an {@link Exception} is thrown. When the user closes the {@code Alert} the application
 * is closed as well.
 */
public final class ErrorAlert {
	private ErrorAlert() {}
	
	/**
	 * Shows an {@link Alert} notifying the user that an error has occurred.
	 * Application will be shut down.
	 * @return void
	 */
	public static void show() {
		final var alert = new Alert(AlertType.NONE);
		alert.setContentText("Oops! An error has occured...");
		final var exitButton = new ButtonType("Close and cry");
		alert.getButtonTypes().setAll(exitButton);
		alert.showAndWait().ifPresent(btn -> System.exit(0));
	}
}
