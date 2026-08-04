class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        return count(n, logs, x, queries);
    }

    int[] count(int nEndpoints, int[][] accessTime, int lookback, int[] checkAccess) {
        int m = accessTime.length;
        int q = checkAccess.length;

        Arrays.sort(accessTime, (a, b) -> Integer.compare(a[1], b[1]));

        int[][] indexQuery = new int[q][2];
        for (int i = 0; i < q; i++) {
            indexQuery[i][0] = checkAccess[i];
            indexQuery[i][1] = i; // store index
        }
        // maintain sort

        Arrays.sort(indexQuery, (a, b) -> Integer.compare(a[0], b[0]));

        int[] freq = new int[nEndpoints + 1];

        int active = 0;
        int[] ans = new int[q];
        int left = 0; 
        int right = 0;

        for (int i = 0; i < q; i++) {
            int endWindow = indexQuery[i][0];
            int idx = indexQuery[i][1];
            int startWindow = endWindow - lookback;

            while(right < m && accessTime[right][1] <= endWindow) {
                int endPointVal = accessTime[right][0];
                freq[endPointVal]++;

                if(freq[endPointVal] == 1)
                    active++;
                right++;
            }

            while(left < m && accessTime[left][1] < startWindow) {
                int endPointVal = accessTime[left][0];
                freq[endPointVal]--;

                if(freq[endPointVal] == 0)
                    active--;
                left++;
            }
            ans[idx] = nEndpoints - active;
        }
        return ans;
    }
}