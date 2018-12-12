package frequency;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class FrequencyCalculator {
    public static int calculateFinalFrequency(List<String> input) {
        return input.stream().mapToInt(Integer::valueOf).sum();
//        int total = 0;
//
//        for (int i = 0; i < input.size(); i++) {
//            total = total + Integer.parseInt(input.get(i));
//        }
//
//        return total;
    }

    public static int calculateDuplicateFrequency(List<String> input) {
        IntStream frequenciesStream = input.stream().mapToInt(Integer::valueOf);

        int[] frequenciesArray = frequenciesStream.toArray();
        System.out.println(Arrays.toString(frequenciesArray));

        // Create frequencyTotal array with initial entry of 0
        // Establish newTotal as frequencyTotal plus current iteration
        // Check to see if the value of newTotal is already in (unique) the frequencyTotal array
        // If it's already there, return!
        // If it isn't there yet, push it in
        // Repeat as necessary

        return 0;
    }
}
