class Solution {
    public int findKthLargest(int[] nums, int k) {
        return priorityQueueKth(nums, k);
        // return quickSelect(nums, 0, nums.length - 1, k);
    }
public int priorityQueueKth(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        
        return minHeap.peek();
    }
    public void swap(int[] nums, int i, int j) {
        if(i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public int findPivot(int[] nums, int l, int r) {
        Random rand = new Random();
        int randPivot = l + rand.nextInt(r - l + 1);
        swap(nums, randPivot, r);
        int i = l;
        int j = l;
        while(j < r) {
            if(nums[j] < nums[r]) {
                // swap i and j
                swap(nums, i, j);
                i++;
            }
            j++;
        }
        // swap i and j where i is pivot
        swap(nums, i, j);
        return i;
    }
    public int quickSelect(int[] nums, int i, int j, int k) {
        
        // base case 
        
        while(i <= j) {
            int pivot = findPivot(nums, i, j);
            int kPos = nums.length - k;
            if(pivot == kPos) {
                return nums[pivot];
            }
            else if(pivot > kPos) {
                j = pivot - 1;
            } else {
                i = pivot + 1;
            }

        }
        return -1;
    }
}