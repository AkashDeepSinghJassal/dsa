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
    public List<Integer> postorderTraversal(TreeNode root) {
        TreeNode curr = root;
        Stack<TreeNode> st = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while(!st.isEmpty() || curr != null) {
            if(curr != null) {
                st.push(curr);
                curr = curr.left;
            } else { // think about right tree
                // if right is null
                TreeNode temp = st.peek().right;
                if(temp == null) {
                    // keep poping from right backtrack
                    while(!st.isEmpty() && st.peek().right == temp) {
                        temp = st.pop();
                        list.add(temp.val);
                    }
                }
                // push right tree
                else 
                    curr = temp;
            }
        }
        return list;
    }
}