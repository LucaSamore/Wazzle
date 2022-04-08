package wazzle.controller.common.files;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerializerImpl<X> implements Serializer<X> {

	private final Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.excludeFieldsWithoutExposeAnnotation()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
			.serializeNulls()
			.create();
	
	@SuppressWarnings("unchecked")
	@Override
	public void serialize(final String path, final X... toBeWritten) throws IOException {
		try(final var writer = new FileWriter(path, StandardCharsets.UTF_8)) {
			this.gson.toJson(toBeWritten, writer);
			writer.flush();
			writer.close();
		}
	}
}
