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
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // build root
        int i = 0;
        int j = inorder.length - 1;
        for(int a = 0; a < inorder.length; a++) {
            map.put(inorder[a], a);
        }
        return build(inorder, postorder, i, j, i, j);
    }

    public TreeNode build(int[] inorder, int[] postorder, int i, int j, int m, int n) {
        if(j - i < 0){
            return null;
        }
        int currRootVal = postorder[n];
        TreeNode root = new TreeNode(currRootVal);
        if(i ==j ){
            return root;
        }
        // find index of curr root in inorder
        // int idx = -1;
        // for(int a = i; a <= j; a++) {
        //     if(inorder[a] == currRootVal) {
        //         idx = a;
        //         break;
        //     }
        // }
        // use map
        int idx = map.get(currRootVal);
        int shift = j - idx + 1;
        // build left sub tree
        // build right sub tree
        // add pointers
        root.left = build(inorder, postorder, i, idx - 1, m, n - shift);
        root.right = build(inorder, postorder, idx + 1, j, shift + 1, n - 1);
        return root;
    }
}