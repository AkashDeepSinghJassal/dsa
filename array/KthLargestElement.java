class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        // let think of pivot method
        // partion function will return pivot

        // based on pivot decide with partition to move next


        return quickSelect(nums, 0, nums.length - 1, k);
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public int findPivot(int[] nums, int l, int r) {

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

        int pivot = findPivot(nums, i, j);
        int kPos = nums.length - k;
        if(pivot == kPos) {
            return nums[pivot];
        }
        // choose partition
        if(pivot > kPos) {
            return quickSelect(nums, i, pivot - 1, k);
        } else {
            return quickSelect(nums, pivot + 1, j, k);
        }
        
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, -1, 5, 5, 6, -4};
        int k = 3;
        KthLargestElement kthLargestElement = new KthLargestElement();
        System.out.println(kthLargestElement.findKthLargest(nums, k));
    }
}