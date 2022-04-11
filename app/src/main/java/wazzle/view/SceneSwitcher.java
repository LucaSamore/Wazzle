package wazzle.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface SceneSwitcher {
	static <X> void switchScene(final ActionEvent event, final X controller, final String path) throws IOException {
		final var node = (Node) event.getSource();
		final var stage = (Stage) node.getScene().getWindow();
		final var element = Loader.<X,Parent>loadFXMLElement(controller, path);
		final var scene = new Scene(element, stage.getScene().getWidth(), stage.getScene().getHeight());
		stage.setScene(scene);
		stage.show();
	}
}
