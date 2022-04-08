package wazzle.controller.common.files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public final class TextHandler implements FileStrategies<String>{
	
	@Override
	public List<String> read(final String path) throws IOException {
		return Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
	}
	
	@Override
	public void append(final String path, final List<String> toBeAdded) throws IOException {
		for (final var element : toBeAdded) {
			Files.write(Path.of(path), 
					(element + System.lineSeparator()).getBytes(), 
					StandardOpenOption.APPEND);
		}
	}
	
	@Override
	public void write(final String path, final List<String> toBeWritten) throws IOException {
		this.clear(path);
		this.append(path, toBeWritten);
	}
}
