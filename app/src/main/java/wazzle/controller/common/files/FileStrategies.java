package wazzle.controller.common.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public interface FileStrategies<X> {
	List<X> read(final String path) throws IOException;
	
	void write(final String path, final List<X> toBeWritten) throws IOException;
	
	void append(final String path, final List<X> toBeAdded) throws IOException;
	
	default void clear(final String path) throws IOException {
		Files.delete(Path.of(path));
		Files.createFile(Path.of(path));
	}
}
