package frequency;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void it_returns_results_from_given_file_name() throws IOException, URISyntaxException {
        // input: filename
        // output: int calculated frequency
        App app = new App();
        int result = app.getFrequency("test2.txt");

        assertEquals(result, 2);
    }
}
