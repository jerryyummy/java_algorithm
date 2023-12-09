import java.util.ArrayList;
import java.util.List;

public class HouseValuation {

    public static int findValuation(int reqArea, int[] area, int[] price) {
        List<Integer> filteredIndices = new ArrayList<>();
        for (int i = 0; i < area.length; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < area.length; j++) {
                if(i!=j && area[j]==area[i]){
                    temp.add(price[j]);
                }
            }
            if (!isOutlier(price[i],temp)) filteredIndices.add(i);
        }

        // If no houses are left
        if (filteredIndices.isEmpty()) return 1000;

        // Calculate the valuation based on the remaining houses
        int valuation;
        if (filteredIndices.size() == 1) {
            valuation = price[filteredIndices.get(0)];
        } else {
            // Interpolate or extrapolate the price
            valuation = interpolateOrExtrapolatePrice(reqArea, area, price, filteredIndices);
        }

        // Clamp the valuation to the specified range and round to the nearest integer
        valuation = Math.max(1000, Math.min(1000000, valuation));
        return Math.round(valuation);
    }

    private static boolean isOutlier(int price, List<Integer> comparablePrices) {
        if (comparablePrices.isEmpty()) {
            return false;
        }
        double mean = comparablePrices.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(Double.NaN);
        double stdDev = calculateStandardDeviation(comparablePrices);
        return Math.abs(price - mean) > 3 * stdDev;
    }

    private static double calculateStandardDeviation(List<Integer> prices) {
        // Calculate the mean of the list
        double mean = prices.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(Double.NaN);

        // Calculate the sum of the squared differences from the mean
        double sumSquaredDifferences = prices.stream()
                .mapToDouble(price -> Math.pow(price - mean, 2))
                .sum();

        // Calculate the standard deviation
        return Math.sqrt(sumSquaredDifferences / prices.size());
    }

    private static int interpolateOrExtrapolatePrice(int reqArea, int[] area, int[] price, List<Integer> filteredIndices) {
        int[] sortedIndices = filteredIndices.stream()
                .sorted((i, j) -> area[i] - area[j])
                .mapToInt(i -> i)
                .toArray();

        int lowerIndex = -1;
        int higherIndex = -1;

        for (int i : sortedIndices) {
            if (area[i] <= reqArea) lowerIndex = i;
            if (area[i] >= reqArea && higherIndex == -1) higherIndex = i;
        }

        if (lowerIndex == -1 || higherIndex == -1) {
            return price[sortedIndices[0]];
        }

        double slope = (double) (price[higherIndex] - price[lowerIndex]) / (area[higherIndex] - area[lowerIndex]);
        return (int) (price[lowerIndex] + slope * (reqArea - area[lowerIndex]));
    }

    public static void main(String[] args) {
        int reqArea = 1200;
        int[] area = {1500, 500, 1000,  2000, 2500};
        int[] price = {30000, 10000, 20000, 40000, 50000};
        int valuation = findValuation(reqArea, area, price);
        System.out.println("The valuation for the house with area " + reqArea + " is: " + valuation);
    }
}
