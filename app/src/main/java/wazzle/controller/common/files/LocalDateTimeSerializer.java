package wazzle.controller.common.files;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

final class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
	
	// I wanna cry :(
	
	@Override
	public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(date.toString());
	}
}
