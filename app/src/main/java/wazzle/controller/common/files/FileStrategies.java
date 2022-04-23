package wazzle.controller.common.files;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * This interface provides methods for performing operations on a file.
 *
 * @param <X> the type of object we want to perform a file operation with.
 */
public interface FileStrategies<X> {
	
	/**
	 * Reads a file given his path
	 * @param path a {@code String} representing the path of the file.
	 * @param <X> the type of object we want to perform the read operation with.
	 * @return a {@code List<X>} containing the elements read from the file.
	 * @throws IOException
	 * @see java.util.List
	 */
	List<X> read(final String path) throws IOException;
	
	/**
	 * Reads a file given an {@link InputStream}.
	 * @param stream a {@code InputStream} representing the file.
	 * @param <X> the type of object we want to perform the read operation with.
	 * @return a {@code List<X>} containing the elements read from the file.
	 * @throws IOException
	 * @see java.util.List
	 */
	List<X> read(final InputStream stream) throws IOException;
	
	/**
	 * Writes the provided content to a file in write mode, given the path. Content will be overwritten.
	 * @param path a {@code String} representing the path.
	 * @param toBeWritten a {@code List<X>} representing the content we want to write.
	 * @param <X> the type of object we want to perform the write operation with.
	 * @return void
	 * @throws IOException
	 * @see java.util.List
	 */
	void write(final String path, final List<X> toBeWritten) throws IOException;
	
	/**
	 * Writes the provided content to a file in append mode, given the path.
	 * @param path a {@code String} representing the path.
	 * @param toBeAdded a {@code List<X>} representing the content we want to append.
	 * @param <X> the type of object we want to perform the append operation with.
	 * @return void
	 * @throws IOException
	 * @see java.util.List
	 */
	void append(final String path, final List<X> toBeAdded) throws IOException;
	
	/**
	 * Clears the content of a file given his path.
	 * @param path a {@code String} representing the path.
	 * @return void
	 * @throws IOException
	 */
	default void clear(final String path) throws IOException {
		Files.delete(Path.of(path));
		Files.createFile(Path.of(path));
	}
}
