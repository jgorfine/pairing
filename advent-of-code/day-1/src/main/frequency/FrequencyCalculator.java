package frequency;

import java.util.List;

public class FrequencyCalculator {
    public static int calculate(List<String> input) {
        return input.stream().mapToInt(Integer::valueOf).sum();
    }
}
