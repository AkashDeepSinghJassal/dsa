
import java.util.Map;
import java.util.TreeMap;

public class MaximumPopulationYear {
    public static void main(String[] args) {
        int[][] years = {
            {2025, 2041},
            {1988, 2007},
            {2003, 2046},
            {2045, 2049},
            {2025, 2027},
            {2014, 2040},
            {2014, 2027},
            {2011, 2027},
            {1972, 2019}
        };

        MaximumPopulationYear mpy = new MaximumPopulationYear();
        int maxYear = mpy.maxPopulationSweepLine(years);
        System.out.println("max year with hightest population : " + maxYear);
    }

    public int maximumPopulation(int[][] logs) {
        // given constratint 1950 - 2050 
        int[] population = new int[100];
        for (int[] log : logs) {
            for (int i = log[0]; i < log[1]; i++) {
                int idx = i - 1950;
                population[idx]++;
            }
        }
        // get highest population earliest year
        int maxPopulationOfYear = 0;
        int maxYear = Integer.MAX_VALUE;
        for (int i = 0; i < population.length; i++) {
            if(population[i] > maxPopulationOfYear) {
                maxPopulationOfYear = population[i];
                maxYear = i + 1950;
            }
        }
        return maxYear;
    }

    public int maxPopulationSweepLine(int[][] logs) {
        // get a sorted map
        // store population at intervals
        TreeMap<Integer, Integer> intervals = new TreeMap<>();
        for (int[] log : logs) {
            intervals.put(log[0], intervals.getOrDefault(log[0], 0) + 1);
            intervals.put(log[1], intervals.getOrDefault(log[1], 0) - 1);
        }
        int maxPopulationOfYear = 0;
        int maxYear = Integer.MAX_VALUE;
        int currPop = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            
            currPop += entry.getValue();
            if(currPop > maxPopulationOfYear) {
                maxPopulationOfYear = currPop;
                maxYear = entry.getKey();
            }
        }
        return maxYear;
    }
}