package wazzle.controller.common.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public final class TextHandler implements FileStrategies<String>{
	
	@Override
	public List<String> read(final InputStream path) throws IOException {
		//return Files.readAllLines(Path.of(path));
		String content = "";
		
		List<String> result = new ArrayList<>();
		
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(path, "UTF-8"))) {
			while ((content = bufferedReader.readLine()) != null) {    
                result.add(content);
            } 
		}
		
		return result;
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
