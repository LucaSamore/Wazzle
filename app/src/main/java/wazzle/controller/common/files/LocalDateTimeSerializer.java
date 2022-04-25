package wazzle.controller.common.files;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a custom serializer for {@link LocalDateTime} objects and it's used by
 * {@link Serializer}. The class is package protected.
 */
final class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

  /** {@inheritDoc} */
  @Override
  public JsonElement serialize(
      LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
  }
}
