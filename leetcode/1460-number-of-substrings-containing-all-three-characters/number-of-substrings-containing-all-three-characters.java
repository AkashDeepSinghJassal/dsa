class Solution {
    public int numberOfSubstrings(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        int[] hash = new int[3];
        int l, r;
        l = r = 0;
        while(r < str.length){
            // add to hash
            hash[str[r] - 'a']++;
            // if hash has all 3 occurance
            while(hash[0] > 0 && hash[1] > 0 && hash[2] > 0) {
                // find the substring
                count += str.length - r;
                hash[str[l] - 'a']--;
                l++;
            }
            r++;
        }
        return count;
    }
}