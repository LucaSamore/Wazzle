package wazzle.controller.common.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation for {@link FileStrategies}. All the methods provided work with
 * {@code String} as they operate with .txt files only.
 */
public final class TextHandler implements FileStrategies<String> {

  private static final String ENCODING = "UTF-8";

  /** {@inheritDoc} */
  @Override
  public List<String> read(final String path) throws IOException {
    return Files.readAllLines(Path.of(path));
  }

  /** {@inheritDoc} */
  @Override
  public List<String> read(final InputStream stream) throws IOException {
    final var fullContent = new ArrayList<String>();
    var line = "";

    try (final var reader = new BufferedReader(new InputStreamReader(stream, ENCODING))) {
      while ((line = reader.readLine()) != null) {
        fullContent.add(line);
      }
    }

    return fullContent;
  }

  /** {@inheritDoc} */
  @Override
  public void append(final String path, final List<String> toBeAdded) throws IOException {
    try (final var bufferedWriter =
        Files.newBufferedWriter(Path.of(path), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
      for (final var element : toBeAdded) {
        bufferedWriter.write(element + System.lineSeparator());
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void write(final String path, final List<String> toBeWritten) throws IOException {
    this.clear(path);
    this.append(path, toBeWritten);
  }
}
