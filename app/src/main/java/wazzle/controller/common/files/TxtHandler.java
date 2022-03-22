package wazzle.controller.common.files;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final class TxtHandler extends ConcreteFileHandler{
	
	//TODO: Add javadoc
	
	@Override
	public void handle(FileOperation<? extends Serializable> operation) throws IOException {
		Objects.requireNonNull(operation);
		
		switch(operation.getOperation()) {
			case READ:
				super.setItemsFromFile(this.readAsString(operation.getPath()));
				break;
			
			case WRITE:
				this.writeAsString(operation.getPath(), operation.getItems(), false);
				break;
				
			case APPEND:
				this.writeAsString(operation.getPath(), operation.getItems(), true);
				break;
				
			case CLEAR:
				this.clear(operation.getPath());
				break;
		}
		
		this.setNextHandler(Optional.empty());
		this.handleNext(operation);
	}
	
	private List<String> readAsString(final String path) throws IOException {
		return Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
	}
	
	private void writeAsString(final String path, final List<? extends Serializable> toBeWritten, boolean append) throws IOException {
		// This method should be able to serialize any (serializable) object, but for the sake of this project we're gonna use it to write simple strings only
		// Object serialization should be demanded to JsonHandler
		// Object serialization/deserialization to .txt file may will be implemented in the future :)
		
		@SuppressWarnings("unchecked")
		List<String> items = (List<String>)(List<?>) toBeWritten;
		
		items.forEach(i -> {
			try {
				Files.write(Path.of(path),
						   (i + System.lineSeparator()).getBytes(), 
							append ? StandardOpenOption.APPEND : StandardOpenOption.WRITE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	private void clear(final String path) throws IOException {
		Files.delete(Path.of(path));
		Files.createFile(Path.of(path));
	}
	
}
