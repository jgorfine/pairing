package frequency;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileProcessingTest {
    @Test
    public void can_load_file_with_one_entry() throws Exception {
        // input: filename: test1.txt
        // output: List<String> ("+12", "-1")
        List<String> fileOutput = FileProcessor.loadFile("test1.txt");
        List<String> expectedOutputs = asList("+12");

        assertEquals(fileOutput, expectedOutputs);
    }

    @Test
    public void can_load_file_with_multiple_entries() throws Exception {
        List<String> fileOutput = FileProcessor.loadFile("test2.txt");
        List<String> expectedOutputs = asList("-1", "+3");

        assertEquals(fileOutput, expectedOutputs);
    }
}
