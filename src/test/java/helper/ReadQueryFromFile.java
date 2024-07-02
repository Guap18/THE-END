package helper;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

public class ReadQueryFromFile {

    @SneakyThrows
    public static String readFromFile(Path filePath) {
      Path dir = Path.of("src/test/resources");
      Path path = dir.resolve(filePath);
      return Files.readString(path);
    }
}