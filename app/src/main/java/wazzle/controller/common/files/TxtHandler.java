package wazzle.controller.common.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final class TxtHandler extends FileHandler{
	
	//TODO: Add javadoc

	@Override
	public void handle(FileOperation<? extends Serializable> operation) throws IOException {
		Objects.requireNonNull(operation);
		
		switch(operation.getOperation()) {
			case READ:
				super.setItemsFromFile(this.readAsString(operation.getPath()));
				break;
			
			case WRITE:
				this.write(operation.getPath(), operation.getItems(), false);
				break;
				
			case APPEND:
				this.write(operation.getPath(), operation.getItems(), true);
				break;
				
			case CLEAR:
				this.clear(operation.getPath());
				break;
		}
		super.setNextHandler(Optional.empty());
		super.handleNext(operation);
	}
	
	private List<String> readAsString(final String path) throws IOException {
		return Files.readAllLines(Path.of(path));
	}
	
	private void write(final String path, final List<? extends Serializable> toBeWritten, boolean append) throws IOException {
		var fileStream = new FileOutputStream(new File(path), append);
		var objectStream = new ObjectOutputStream(fileStream);
		
		toBeWritten.forEach(i -> {
			try {
				objectStream.writeObject(String.valueOf(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		objectStream.close();
		fileStream.close();
	}
	
	private void clear(final String path) throws IOException {
		Files.delete(Path.of(path));
		Files.createFile(Path.of(path));
	}
	
}
