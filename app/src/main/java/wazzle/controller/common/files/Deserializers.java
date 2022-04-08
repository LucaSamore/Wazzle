package wazzle.controller.common.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import wazzle.model.maingame.MainGameImpl;

public interface Deserializers {
	
	static Supplier<Gson> gson() {
		return () -> new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
				.create();
	}
	
	static List<MainGameImpl> mainGames(final String path) throws IOException {
		return gson().get().fromJson(Files.newBufferedReader(Path.of(path)), 
				new TypeToken<List<MainGameImpl>>() {
					private static final long serialVersionUID = -2698007719862279039L;
				}.getType());
	}
}
