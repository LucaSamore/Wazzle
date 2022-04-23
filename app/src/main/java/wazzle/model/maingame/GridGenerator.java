package wazzle.model.maingame;

/**
 * This interface provides a method for creating a {@link wazzle.model.maingame.Grid} object.
 */
public interface GridGenerator {
	/**
	 * Returns a new @{link Grid} object.
	 * @return the generated {@code Grid} object.
	 * @see wazzle.model.maingame.Grid
	 */
	Grid generate();
}
