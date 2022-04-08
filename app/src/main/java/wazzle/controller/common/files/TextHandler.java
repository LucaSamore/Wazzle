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
		return Files.readAllLines(Path.of(path));
	}
	
	@Override
	public void append(final String path, final List<String> toBeAdded) throws IOException {
		try (final var bufferedWriter = Files.newBufferedWriter(Path.of(path), 
				StandardCharsets.UTF_8, 
				StandardOpenOption.APPEND)) {
			for (final var element : toBeAdded) {
				bufferedWriter.write(element + System.lineSeparator());
			}
		}
	}
	
	@Override
	public void write(final String path, final List<String> toBeWritten) throws IOException {
		this.clear(path);
		this.append(path, toBeWritten);
	}
}
