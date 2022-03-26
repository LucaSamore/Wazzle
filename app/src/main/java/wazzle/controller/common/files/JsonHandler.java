package wazzle.controller.common.files;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

final class JsonHandler<E> extends ConcreteFileHandler<E>{
	
	//TODO: Add javadoc
	
	private final Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
			.create();

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
				this.append(operation.getPath(), operation.getItems());
				break;
				
			case CLEAR:
				this.clear(operation.getPath());
				break;
		}
		//return false;
		this.setNextHandler(Optional.empty());
		this.handleNext(operation);
		//return this.next.get().handle(operation);
	}
	
	private void serialize(String path, List<? extends Serializable> toBeWritten) throws IOException {	
		final var writer = new FileWriter(path);
		this.gson.toJson(toBeWritten, writer);
		writer.flush();
		writer.close();
	}
	
	@SuppressWarnings("serial")
	private List<E> deserialize(final String path) throws IOException {
		final List<E> items = this.gson.fromJson(Files.newBufferedReader(Path.of(path)), new TypeToken<List<E>>() {}.getType());
		System.out.println("Deserializer: " + items);
		return items;
	}
	
	@SuppressWarnings("unchecked")
	private void append(final String path, final List<? extends Serializable> toBeAppended) throws IOException {
		this.serialize(
				path,
				(List<? extends Serializable>) Stream.concat(this.deserialize(path).stream(), toBeAppended.stream())
					  								 .collect(Collectors.toList())
		);
	}
}
