package imt.api.SummonsApi.controller;

import java.util.Random;
import java.util.stream.IntStream;

public class WeightedRandom {

    public static int getWeightedRandom(int[] weights) {
        int totalWeight = IntStream.of(weights).sum();
        Random random = new Random();
        int randomIndex = random.nextInt(totalWeight);

        int cumulativeWeight = 0;
        for (int i = 0; i < weights.length; i++) {
            cumulativeWeight += weights[i];
            if (randomIndex < cumulativeWeight) {
                return i + 1; // Return the star rating (1-based index)
            }
        }
        return weights.length; // Fallback, should never reach here
    }
}
