package wazzle.controller.common.files;

import java.io.IOException;
import java.io.Serializable;
import com.google.common.io.Files;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ConcreteFileHandler extends FileHandler {
	
	//TODO: Add javadoc
	
	private static final String JSON_EXTENSION = "json";
	private static final String TXT_EXTENSION = "txt";
	
	protected List<? extends Serializable> itemsFromFile;
	
	public ConcreteFileHandler() {
		this.itemsFromFile = new ArrayList<>();
	}
	
	@Override
	public void handle(final FileOperation<? extends Serializable> operation) throws IOException {
		Objects.requireNonNull(operation);
		
		switch(Files.getFileExtension(operation.getFileName())) {
			case JSON_EXTENSION:
				this.setNextHandler(Optional.of(new JsonHandler()));
				break;
			
			case TXT_EXTENSION:
				this.setNextHandler(Optional.of(new TxtHandler()));
				break;
				
			default:
				this.setNextHandler(Optional.empty());
		}
		
		this.handleNext(operation);
	}
	
	public List<? extends Serializable> getItemsFromFile() {
		System.out.println("Getter: " + this.itemsFromFile);
		return List.copyOf(this.itemsFromFile);
	}
	
	protected void setItemsFromFile(final List<? extends Serializable> items) {
		this.itemsFromFile = items;
		System.out.println("Setter: " + this.itemsFromFile);
	}
}
