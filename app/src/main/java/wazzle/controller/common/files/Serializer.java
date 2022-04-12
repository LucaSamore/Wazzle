package wazzle.controller.common.files;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.function.Supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Serializer {
	static Supplier<Gson> gson() {
		return () -> new GsonBuilder()
				.setPrettyPrinting()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
				.serializeNulls()
				.create();
	}
	
	@SuppressWarnings("unchecked")
	static <X> void serialize(final String path, final X... toBeWritten) throws IOException {
		try(final var writer = new FileWriter(path, StandardCharsets.UTF_8)) {
			gson().get().toJson(toBeWritten, writer);
			writer.flush();
			writer.close();
		}
	}
}
