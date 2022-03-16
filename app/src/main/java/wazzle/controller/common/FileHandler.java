package wazzle.controller.common;

import com.google.common.base.Optional;

public abstract class FileHandler {
	
	//TODO: Add javadoc
	
	private Optional<FileHandler> next;
	
	public abstract boolean handle(FileOperation operation);
	
	public Optional<FileHandler> setNextHandler(FileHandler next) {
		this.next = Optional.of(next);
		return this.next;
	}
	
	protected boolean handleNext(FileOperation operation) {
		if(this.next.isPresent()) {
			return true;
		}
		
		return this.next.get().handle(operation);
	}
}
