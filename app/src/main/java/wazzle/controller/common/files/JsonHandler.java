package wazzle.controller.common.files;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

final class JsonHandler extends ConcreteFileHandler{
	
	//TODO: Add javadoc
	
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public void handle(FileOperation<? extends Serializable> operation) throws IOException {
		Objects.requireNonNull(operation);
		
		switch(operation.getOperation()) {
			case READ:
				super.setItemsFromFile(this.deserialize(operation.getPath()));
				break;
			
			case WRITE:
				this.serialize(operation.getPath(), operation.getItems());
				break;
				
			case APPEND:
				this.append();
				break;
				
			case CLEAR:
				this.clear(operation.getPath());
				break;
		}
		this.setNextHandler(Optional.empty());
		this.handleNext(operation);
	}
	
	private void serialize(String path, List<?> toBeWritten) throws IOException {
		
		toBeWritten.forEach(i -> {
			try {
				this.gson.toJson(i, new FileWriter(path));
			} catch (JsonIOException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	private List<? extends Serializable> deserialize(final String path) throws IOException {
		return null;
	}
	
	private void append() throws IOException {
		
	}
	
	private void clear(final String path) throws IOException {
		Files.delete(Path.of(path));
		Files.createFile(Path.of(path));
	}

}
