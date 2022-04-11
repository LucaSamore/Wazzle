package wazzle.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

public interface Loader {
	static <X,Y> Y loadFXMLElement(final X controller, final String path) throws IOException {
		final var loader = new FXMLLoader(ClassLoader.getSystemResource(path));
		loader.setController(controller);
		return loader.load();
	}
}
