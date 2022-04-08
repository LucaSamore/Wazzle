package wazzle.controller.common.files;

import java.io.FileWriter;
import java.io.IOException;
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
	
	@Override
	public void serialize(final String path, List<X> toBeWritten) throws IOException {
		final var writer = new FileWriter(path);
		this.gson.toJson(toBeWritten, writer);
		writer.flush();
		writer.close();
	}
}
