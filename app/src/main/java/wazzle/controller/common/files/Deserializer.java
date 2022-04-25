package wazzle.controller.common.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

/** This interface provides a method for objects deserialization to a .json file. */
public interface Deserializer {

  /**
   * Supplies a custom {@link Gson} object used for deserialization.
   *
   * @return a {@link Supplier} for {@code Gson} objects}.
   */
  default Supplier<Gson> deserializationGson() {
    return () ->
        new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .create();
  }

  /**
   * Deserializes a {@code List<T>} from a .json file, given the path.
   *
   * @param myClass the {@code Class} of the objects to be deserialized.
   * @param path a {@code String} representing the path of the file.
   * @param <T> the type of the objects to be deserialized.
   * @return a {@code List<T>} describing the content read.
   * @throws IOException
   * @see java.util.List
   */
  default <T> List<T> deserialize(final Class<T> myClass, final String path) throws IOException {
    return this.deserializationGson()
        .get()
        .fromJson(
            Files.readString(Path.of(path)),
            TypeToken.getParameterized(List.class, myClass).getType());
  }

  /**
   * Deserializes a {@code List<T>} from a .json file, given an {@link InputStream}.
   *
   * @param myClass the {@code Class} of the objects to be deserialized.
   * @param resource an {@code InputStream} representing the file.
   * @param <T> the type of the objects to be deserialized.
   * @return a {@code List<T>} describing the content read.
   * @throws IOException
   * @see java.util.List
   */
  default <T> List<T> deserialize(final Class<T> myClass, final InputStream resource)
      throws IOException {
    return this.deserializationGson()
        .get()
        .fromJson(
            new InputStreamReader(resource, StandardCharsets.UTF_8),
            TypeToken.getParameterized(List.class, myClass).getType());
  }
}
