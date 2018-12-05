package frequency;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class FrequencyCalculatorTest {
    @Test
    public void can_calculate_frequency_of_short_list() {
        // input: List<Strings>
        // output: a single integer, total balance

        List<String> input = asList("+50");
        int result = FrequencyCalculator.calculate(input);

        assertEquals(result, 50);
    }

    @Test
    public void can_calculate_frequency_of_long_list() {
        List<String> input = asList("+1", "-2", "+40");
        int result = FrequencyCalculator.calculate(input);

        assertEquals(result, 39);
    }

}
