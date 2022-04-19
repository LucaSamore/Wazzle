package wazzle.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public interface WindowCloser {
	
	private void defaultClose(final WindowEvent e) {
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
	
	default void closeWindow(final Stage stage) {
		stage.getScene()
			.getWindow()
			.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::defaultClose);
	}
}
