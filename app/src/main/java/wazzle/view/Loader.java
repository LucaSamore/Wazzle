package wazzle.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 * This interface provides a static method used for loading a FXML element.
 */
public interface Loader {
	
	/**
	 * Loads a FXML element given the controller used by that element and the path.
	 * @param controller the controller attached to the element.
	 * @param path a {@code String} which represents the path of the file to be loaded.
	 * @param <X> the type of the controller object.
	 * @param <Y> the type of the element to be loaded. 
	 * @return the element loaded
	 * @throws IOException
	 */
	static <X,Y> Y loadFXMLElement(final X controller, final String path) throws IOException {
		final var loader = new FXMLLoader(ClassLoader.getSystemResource(path));
		loader.setController(controller);
		return loader.load();
	}
}
