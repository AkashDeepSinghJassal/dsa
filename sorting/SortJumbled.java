import java.util.Arrays;

public class SortJumbled {
    
    public static void main(String[] args) {
        SortJumbled sortJumbled = new SortJumbled();
        int[] mapping = {8,9,4,0,2,1,3,5,7,6};
        int[] nums = {991,338,38};
        int[] out = sortJumbled.sortJumbled(mapping, nums);
        Arrays.stream(out).forEach(s -> System.out.println(s));
        
    }
        /**
    Pre requisits
    order is important
     */
    public int[] sortJumbled(int[] mapping, int[] nums) {
        // map to get new numbers in different array 
        // then sort
        System.out.println("in sort jumbled");
        int jumbledArr[] = Arrays.stream(nums)
        .mapToObj(i -> {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            return sb;
        })
        .map(sb -> {
            for (int i = 0; i < sb.length(); i++) {
                sb.setCharAt(i, (char) (mapping[sb.charAt(i) - '0'] + '0'));
            }
            return sb.toString();
        })
        .mapToInt(Integer::parseInt).toArray();
        
        Arrays.stream(jumbledArr).forEach(i -> System.out.print(i + " "));
        System.out.println();
        // for(int i = 0; i < jumbledArr.length; i++){
        //     for(int j = 0; j < jumbledArr.length - i - 1; j++){
        //         // check if frequency of nums[j] is bigger than freq of nums[j + 1]
        //         // or if freq is same then nums[j] is less then nums[j + 1]
        //         if(jumbledArr[j] > jumbledArr[j + 1]){
        //             // swap
        //             int temp = nums[j];
        //             nums[j] = nums[j + 1];
        //             nums[j + 1] = temp;
        //             temp = jumbledArr[j];
        //             jumbledArr[j] = jumbledArr[j + 1];
        //             jumbledArr[j + 1] = temp;
                    
        //         }
        //     }
        // }
        sort(nums, jumbledArr, 0, nums.length - 1);
        Arrays.stream(jumbledArr).forEach(i -> System.out.print(i + " "));
        return nums;
    }


    public void sort(int[] nums, int[] jumbledNums, int l, int r) {
        if (l >= r)
            return;
        int mid = (l + r) / 2;
        sort(nums, jumbledNums,l, mid);
        sort(nums, jumbledNums, mid + 1, r);
        merge(nums, jumbledNums,l, mid, r);
    }

    public void merge(int[] nums, int[] jumbledNums, int l, int mid, int r) {
        int temp[] = new int[-l + r + 1];
        int tempJumbled[] = new int[-l + r + 1];
        int i = l;
        int j = mid + 1;
        int c = 0;
        while (i <= mid && j <= r) {
            if (jumbledNums[i] <= jumbledNums[j]) {
                temp[c] = nums[i];
                tempJumbled[c] = jumbledNums[i];
                c++;
                i++;
            } else {
                temp[c] = nums[j];
                tempJumbled[c] = jumbledNums[j];
                c++;
                j++;
            }
        }
        while (i <= mid) {
            temp[c] = nums[i];
            tempJumbled[c] = jumbledNums[i];
            c++;
            i++;
        }
        while (j <= r) {
            temp[c] = nums[j];
            tempJumbled[c] = jumbledNums[j];
            c++;
            j++;
        }
        // copy content
        for (int x = l; x <= r; x++) {
            nums[x] = temp[x - l];
            jumbledNums[x] = tempJumbled[x - l];
        }
    }
}
