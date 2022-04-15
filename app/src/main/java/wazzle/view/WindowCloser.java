package wazzle.view;


import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WindowCloser {
	private WindowCloser() {}
	
	private static void defaultClose(WindowEvent e) {
		final var alert = new Alert(AlertType.NONE);
		final var confirm = new ButtonType("Conferma");
		final var cancel = new ButtonType("Annulla");
		
		alert.setContentText("Vuoi uscire dal gioco?");
		alert.getButtonTypes().setAll(confirm, cancel);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get().equals(confirm)) {
			System.exit(0);
		}
		
		e.consume();
	}
	
	static void onExit(final Stage stage) {
		stage.getScene()
			.getWindow()
			.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> defaultClose(e));
	}
}
