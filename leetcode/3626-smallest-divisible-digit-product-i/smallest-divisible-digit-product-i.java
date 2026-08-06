class Solution {
/**
    27 3


    38 / 5

    24 
    if and of these is equal to low then yes it's a product


 */

    public int smallestNumber(int n, int t) {
        while(digitProd(n) % t != 0)
            n++;
        return n;
    }
    public int digitProd(int n) {
        if(n == 0) {
            return 1;
        }
        return n % 10 * (digitProd(n / 10));
    }
}