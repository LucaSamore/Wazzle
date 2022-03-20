package wazzle.controller.common.files;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class FileHandler {
	
	//TODO: Add javadoc

	private List<? extends Serializable> itemsFromFile = new ArrayList<>();
	private Optional<FileHandler> next;
	
	public abstract void handle(final FileOperation<? extends Serializable> operation) throws IOException;
	
	public List<? extends Serializable> getItemsFromFile() {
		return List.copyOf(this.itemsFromFile);
	}
	
	protected void setNextHandler(final Optional<FileHandler> next) {
		this.next = next;
	}
	
	protected void setItemsFromFile(final List<? extends Serializable> items) {
		this.itemsFromFile = items;
	}
	
	protected void handleNext(final FileOperation<? extends Serializable> operation) throws IOException {
		if(!this.next.isPresent()) {
			return;
		}
		
		this.next.get().handle(operation);
	}
}
