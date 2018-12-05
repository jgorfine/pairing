package frequency;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class App {
    public int getFrequency(String fileName) throws IOException, URISyntaxException {
        List<String> lines = FileProcessor.loadFile(fileName);
        return FrequencyCalculator.calculate(lines);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        App instance = new App();
        int result = instance.getFrequency("frequencies.txt");
        System.out.println(result);
    }
}
