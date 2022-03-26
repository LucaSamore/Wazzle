package wazzle.controller.common.files;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ConcreteFileHandler<E> extends FileHandler {
	
	//TODO: Add javadoc
	
	private static final String JSON_EXTENSION = "json";
	private static final String TXT_EXTENSION = "txt";
	
	private List<E> itemsFromFile;
	
	public ConcreteFileHandler() {
		this.itemsFromFile = new ArrayList<>();
	}
	
	@Override
	public void handle(final FileOperation<? extends Serializable> operation) throws IOException {
		Objects.requireNonNull(operation);
		
		switch(this.getExtension(operation.getFileName()).get()) {
			case JSON_EXTENSION:
				this.setNextHandler(Optional.of(new JsonHandler<E>()));
				break;
			
			case TXT_EXTENSION:
				this.setNextHandler(Optional.of(new TxtHandler<E>()));
				break;
				
			default:
				this.setNextHandler(Optional.empty());
		}
		
		this.handleNext(operation);
	}
	
	public List<E> getItemsFromFile() {
		System.out.println("Getter: " + this.itemsFromFile);
		return List.copyOf(this.itemsFromFile);
	}
	
	protected void setItemsFromFile(final List<E> items) {
		System.out.println("Setter before set: " + this.itemsFromFile);
		this.itemsFromFile = items;
		System.out.println("Setter after set: " + this.itemsFromFile);
	}
	
	protected void clear(final String path) throws IOException {
		Files.delete(Path.of(path));
		Files.createFile(Path.of(path));
	}
	
	private Optional<String> getExtension(final String fileName) {
		return Optional.ofNullable(fileName)
			      .filter(f -> f.contains("."))
			      .map(f -> f.substring(fileName.lastIndexOf(".") + 1));
	}

	@Override
	public String toString() {
		return "ConcreteFileHandler [itemsFromFile=" + itemsFromFile + "]";
	}
}
