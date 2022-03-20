package wazzle.controller.common.files;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import com.google.common.io.Files;

final class JsonHandler extends FileHandler{
	
	//TODO: Add javadoc

	@Override
	public void handle(FileOperation<? extends Serializable> operation) throws IOException {
		Objects.requireNonNull(operation);
		
		switch(operation.getOperation()) {
			case READ:
				this.deserialize();
				break;
			
			case WRITE:
				this.serialize();
				break;
				
			case APPEND:
				this.append();
				break;
		}
		super.setNextHandler(Optional.empty());
		super.handleNext(operation);
	}
	
	private void serialize() {
		
	}
	
	private void deserialize(){

	}
	
	private void append() {
		
	}

}
