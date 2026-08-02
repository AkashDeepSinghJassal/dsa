class Solution {
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        // final final array
        long[] arr = new long[monsters.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = monsters[i];
        }
        long[] diff = new long[monsters.length + 1];
        for(int i = 0; i < boosts.length; i++) {
            int[] val = boosts[i];
            int l = val[0];
            int r = val[1];
            int v = val[2];
            diff[l] -= v;
            diff[r + 1] += v;
        }
        for(int i = 0; i < arr.length; i++) {
            arr[i] += diff[i];
            diff[i + 1] += diff[i];
        }
        System.out.println(Arrays.toString(arr));
        int i = arr.length - 1;
        while(i >= 0 && arr[i] <= 0)
            i--;
        if(i < 0)
            return 0;
        long energy = arr[i--];
        while(i >= 0) 
            energy += monsters[i--];
            
        return energy;
    }
}