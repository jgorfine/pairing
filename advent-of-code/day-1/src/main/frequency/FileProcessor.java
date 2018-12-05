package frequency;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileProcessor {
    public static List<String> loadFile(String filename) throws URISyntaxException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fileURL = classLoader.getResource(filename);
        Path filePath = Paths.get(fileURL.toURI());
        List<String> fileLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        return fileLines;
    }
}
