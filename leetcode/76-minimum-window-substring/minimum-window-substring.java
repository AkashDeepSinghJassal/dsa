class Solution {
    public String minWindow(String s, String t) {
        // store count of found char
        int tSum = t.length();
        char[] sArr = s.toCharArray();
        int count = 0;
        int[] hash = new int[256];
        int l, r;
        int li, ri;
        li = ri = -1;
        int minLen = Integer.MAX_VALUE;
        l = r = 0;
        for(char c : t.toCharArray()) {
            hash[c]++;
        }
        // increment r 
        // check if hash[s[r]] > 0 for given s[r] then decrease 1 from hash[s[r]];
        while(r < sArr.length) {
            if(hash[sArr[r]] > 0) {
                count++;
            }
            hash[sArr[r]]--;
        // if count == tSum
            if(count == tSum) {
        // try to reduce window
                while(hash[sArr[l]] < 0) {
                    hash[sArr[l]]++;
                    l++;
                }
                if(minLen > r - l + 1) {
                    minLen = r - l + 1;
                    li = l;
                    ri = r;
                }
            }
            r++;
        }
        if(li == -1)
            return "";
        return s.substring(li, ri + 1);
    }
}