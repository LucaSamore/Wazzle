package wazzle.controller.common.files;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;

import com.google.common.io.Files;
import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ConcreteFileHandler extends FileHandler {
	
	//TODO: Add javadoc
	
	private static final String JSON_EXTENSION = "json";
	private static final String TXT_EXTENSION = "txt";
	
	// I know I shouldn't use static things but I can't be arsed anyway so...
	private static List<?> itemsFromFile = new ArrayList<>();
	
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
	
	public List<?> getItemsFromFile() {
		return List.copyOf(ConcreteFileHandler.itemsFromFile);
	}
	
	protected void setItemsFromFile(final List<?> items) {
		ConcreteFileHandler.itemsFromFile = items;
	}
}
