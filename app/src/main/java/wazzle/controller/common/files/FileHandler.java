package wazzle.controller.common.files;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public abstract class FileHandler {
	
	//TODO: Add javadoc
	
	private Optional<FileHandler> next;		
	
	public abstract void handle(final FileOperation<? extends Serializable> operation) throws IOException;
	
	protected void setNextHandler(final Optional<FileHandler> next) {
		this.next = next;
	}
	
	protected void handleNext(final FileOperation<? extends Serializable> operation) throws IOException {
		if(!this.next.isPresent()) {
			return;
		}
		
		this.next.get().handle(operation);
	}
}
