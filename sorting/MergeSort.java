
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = { 5, 3, 7, 1 , 101, 6, 14, 93, -2};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int l, int r) {
        if (l >= r)
            return;
        int mid = (l + r) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    public void merge(int[] nums, int l, int mid, int r) {
        int temp[] = new int[-l + r + 1];
        int i = l;
        int j = mid + 1;
        int c = 0;
        while (i <= mid && j <= r) {
            if (nums[i] < nums[j]) {
                temp[c++] = nums[i++];
            } else {
                temp[c++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[c++] = nums[i++];
        }
        while (j <= r) {
            temp[c++] = nums[j++];
        }
        // copy content
        for (int x = l; x <= r; x++) {
            nums[x] = temp[x - l];
        }
    }
}
