package wazzle.controller.common.files;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This interface provides a method for objects serialization to a .json file.
 */
public interface Serializer {
	
	/**
	 * Supplies a custom {@link Gson} object used for serialization.
	 * @return a {@link Supplier} for {@code Gson} objects}.
	 */
	default Supplier<Gson> serializationGson() {
		return () -> new GsonBuilder()
				.setPrettyPrinting()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
				.serializeNulls()
				.create();
	}
	
	/**
	 * Serializes a sequence of generic objects to a .json file, given the path.
	 * @param path a {@code String} representing the path of the file.
	 * @param toBeWritten the elements we want to serialize.
	 * @param <X> the type of the objects to be serialized.
	 * @return void
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	default <X> void serialize(final String path, final X... toBeWritten) throws IOException {
		try(final var writer = new FileWriter(path, StandardCharsets.UTF_8)) {
			this.serializationGson().get().toJson(toBeWritten, writer);
			writer.flush();
			writer.close();
		}
	}
}
