package wazzle.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public final class ErrorAlert {
	private ErrorAlert() {}
	
	public static void show() {
		final var alert = new Alert(AlertType.NONE);
		alert.setContentText("Oops! An error has occured...");
		final var exitButton = new ButtonType("Close and cry");
		alert.getButtonTypes().setAll(exitButton);
		alert.showAndWait().ifPresent(btn -> System.exit(0));
	}
}
