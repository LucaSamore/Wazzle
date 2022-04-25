package wazzle.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

public interface TestFileUtils {

  final String separator = System.getProperty("file.separator");

  static String getPath() {
    return "src" + separator + "test" + separator + "res" + separator;
  }

  static Set<String> readDataset(final String fileName) throws IOException {
    return Files.lines(Paths.get(getPath() + fileName)).collect(Collectors.toSet());
  }
}
