class Solution {
    public int findContentChildren(int[] g, int[] s) {
     /**

     5, 4, 2, 8, 1, 3, 

     cookies - 9, 6, 3, 55, 2
     sort them and find satisfy condition


      */   

    Arrays.sort(g);
    Arrays.sort(s);
    // System.out.println(Arrays.toString(g));
    int count = 0;
    int l = 0;
    for(int i = 0; i < s.length; i++) {
        if(l < g.length && s[i] >= g[l]){
            count++;
            l++;
        }
    }
    return count;
    }
}