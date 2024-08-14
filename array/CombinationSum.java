import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides a method to find all unique combinations of numbers from
 * a given array that sum up to a target value.
 * Each number in the array can be used only once in the combination.
 */
public class CombinationSum {
    // A list to store all unique combinations that sum up to the target value
    List<List<Integer>> sumList = new ArrayList<>();
    // The target sum that combinations need to achieve
    int target = 0;

    /**
     * Finds all unique combinations of numbers in the candidates array that sum up to the target.
     * @param candidates The array of candidate numbers.
     * @param target The target sum.
     * @return A list of lists, where each list contains a unique combination of numbers that sum up to the target.
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.target = target;
        // Sort the candidates to handle duplicates and facilitate the combination process
        Arrays.sort(candidates);
        // Start the recursive traversal to find all combinations
        traverse(candidates, 0, 0, new ArrayList<>());
        return sumList;
    }

    /**
     * A recursive method to find all valid combinations by either including or excluding each candidate number.
     * @param nums The sorted array of candidate numbers.
     * @param sum The current sum of the selected numbers.
     * @param i The current index in the array being processed.
     * @param selectedNums The list of currently selected numbers.
     */
    public void traverse(int[] nums, int sum, int i, List<Integer> selectedNums) {
        // If the current sum exceeds the target, stop further exploration
        if (sum > target)
            return;
        
        // If the current sum matches the target, add the current combination to the result list
        if (sum == target) {
            List<Integer> tempList = new ArrayList<>(selectedNums);
            sumList.add(tempList);
            return;
        }
        
        // If all numbers have been processed, stop further exploration
        if (i == nums.length)
            return;

        // Include the current number in the combination and recursively explore further
        selectedNums.add(nums[i]);
        traverse(nums, sum + nums[i], i + 1, selectedNums);
        
        // Backtrack by removing the last added number to explore other combinations
        selectedNums.remove(selectedNums.size() - 1);

        // Skip all duplicate numbers to avoid repeating the same combination
        int temp = nums[i];
        while (i < nums.length && nums[i] == temp)
            i++;
        
        // Exclude the current number from the combination and recursively explore further
        traverse(nums, sum, i, selectedNums);
    }

    /**
     * This method checks if a list is already present in the sumList.
     * It is a computationally expensive operation and should be avoided if possible.
     * @param sumList The list of lists containing all valid combinations.
     * @param list The combination list to be checked for duplicates.
     * @return True if the combination list is a duplicate, false otherwise.
     */
    public boolean checkDuplicate(List<List<Integer>> sumList, List<Integer> list) {
        for (int i = 0; i < sumList.size(); i++) {
            if (sumList.get(i).size() == list.size() &&
                    sumList.get(i).containsAll(list) &&
                    list.containsAll(sumList.get(i))) {
                System.out.println("duplicate " + sumList.get(i) + list);
                return true;
            }
        }
        return false;
    }

    /**
     * The main method to test the combinationSum2 method with an example.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("combinational sum");

        int[] nums = { 4, 4, 2, 1, 4, 2, 2, 1, 3 };
        int target = 6;
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> combinationSumList = combinationSum.combinationSum2(nums, target);
        System.out.println(combinationSumList);
    }
}