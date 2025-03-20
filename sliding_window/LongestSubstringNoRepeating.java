/**
 * Given a string s, find the length of the longest

without duplicate characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

 

Constraints:

    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.

 * 
 */
class LongestSubstringNoRepeating {
    HashMap<Character, Integer> map = new HashMap<>();

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int max = -1;
        int i;
        for(i = 0; i < s.length(); i++){
            // add to map index
            // if index already exists 
            if(map.containsKey(s.charAt(i))) {
                int index = map.get(s.charAt(i));
                if(max < (i - left)) {
                    max = i - left;
                }
                if(index >= left) {
                    left = index + 1;
                }
            }
            map.put(s.charAt(i), i);
            // add if index don't exists
        }    
        if(max < (i - left)) {
            max = i - left;
        }
        return max;
    }

    public static void main(String[] args){
        LongestSubstringNoRepeating lsnr = new LongestSubstringNoRepeating();
        int length = lsnr.lengthOfLongestSubstring("abcabcbb");
        System.out.println("Length of longest substring non repeating" + length);
    }
}