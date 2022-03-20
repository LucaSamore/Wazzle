package wazzle.controller.common.files;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

final class TxtHandler extends FileHandler{
	
	//TODO: Add javadoc

	@Override
	public void handle(FileOperation<? extends Serializable> operation) throws IOException {
		Objects.requireNonNull(operation);
		
		switch(operation.getOperation()) {
			case READ:
				this.read();
				break;
			
			case WRITE:
				this.write();
				break;
				
			case APPEND:
				this.append();
				break;
				
			case CLEAR:
				this.clear();
				break;
		}
		super.setNextHandler(Optional.empty());
		super.handleNext(operation);
	}
	
	private void read() throws IOException {
		
	}
	
	private void write() throws IOException {

	}
	
	private void append() throws IOException {
		
	}
	
	private void clear() throws IOException {
		
	}
	
}
