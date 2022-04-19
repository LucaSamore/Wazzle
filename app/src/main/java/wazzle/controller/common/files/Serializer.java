package wazzle.controller.common.files;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.function.Supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Serializer {
	default Supplier<Gson> serializationGson() {
		return () -> new GsonBuilder()
				.setPrettyPrinting()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
				.serializeNulls()
				.create();
	}
	
	@SuppressWarnings("unchecked")
	default <X> void serialize(final String path, final X... toBeWritten) throws IOException {
		try(final var writer = new FileWriter(path, StandardCharsets.UTF_8)) {
			this.serializationGson().get().toJson(toBeWritten, writer);
			writer.flush();
			writer.close();
		}
	}
}
