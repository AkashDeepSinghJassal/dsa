/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int sum = -1000; // integer given min value
    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return sum;
    }

    int maxSum(TreeNode root) { 
        if(root == null) {
            return 0;
        }
        int lh = maxSum(root.left);
        int rh = maxSum(root.right);
        if (lh < 0) 
            lh = 0;
        if (rh < 0) 
            rh = 0;
        sum = Math.max(sum, lh + rh + root.val);
        return Math.max(lh + root.val, rh + root.val);
    }
}