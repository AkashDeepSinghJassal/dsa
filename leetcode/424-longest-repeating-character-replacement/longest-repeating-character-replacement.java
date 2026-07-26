class Solution {
    public int characterReplacement(String s, int k) {
        int l,r;
        l = 0;
        r = 0;

        // take a map of count of chars
        // max of -> if length of window - max freq <= k then len.window
        int hash[] = new int[26];
        int maxFreq = 0;
        int maxLen = 0;
        while(r < s.length()) {
            char c = s.charAt(r);
            hash[c - 'A']++;
            maxFreq = Math.max(maxFreq, hash[c - 'A']);
            while(l <= r && (r - l + 1) - maxFreq > k) {
                hash[s.charAt(l) - 'A']--;
                l++;
            }
            // find max Freq
            maxLen = Math.max(maxLen, r - l + 1);
            r++;
            
        }
        return maxLen;
    }
    int findMaxFreq(int[] hash) {
        int max = 0;
        for(int i = 0; i < hash.length; i++) {
            max = Math.max(max, hash[i]);
        }
        return max;
    }
}