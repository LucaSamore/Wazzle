package wazzle.controller.common.files;

import java.io.IOException;
import java.io.Serializable;
import com.google.common.io.Files;
import java.util.Objects;
import java.util.Optional;

public class ConcreteFileHandler extends FileHandler {
	
	//TODO: Add javadoc
	
	private static final String JSON_EXTENSION = "json";
	private static final String TXT_EXTENSION = "txt";
	
	@Override
	public void handle(final FileOperation<? extends Serializable> operation) throws IOException {
		Objects.requireNonNull(operation);
		
		switch(Files.getFileExtension(operation.getFileName())) {
			case JSON_EXTENSION:
				super.setNextHandler(Optional.of(new JsonHandler()));
				break;
			
			case TXT_EXTENSION:
				super.setNextHandler(Optional.of(new TxtHandler()));
				break;
				
			default:
				super.setNextHandler(Optional.empty());
		}
		
		super.handleNext(operation);
	}
	
	public void clearFile(final String fileName) {
		
	}
}
