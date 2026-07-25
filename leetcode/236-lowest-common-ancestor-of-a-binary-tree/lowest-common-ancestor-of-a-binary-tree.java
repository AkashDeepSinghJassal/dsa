/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pList = new ArrayList<>();
        List<TreeNode> qList = new ArrayList<>();
        path(root, p, pList);
        path(root, q, qList);
        int i = 0, j = 0;
        int result = -1;
        while(i < pList.size() && j < qList.size() && pList.get(i).val == qList.get(j).val) {
            i++;
            j++;
        }
        return pList.get(i - 1);
    }

    public boolean path(TreeNode root, TreeNode node, List<TreeNode> list ) {
        if(root == null) {
            return false;
        }
        list.add(root);
        if(root.val == node.val) {
            return true;
        }

        if(path(root.left, node, list) || path(root.right, node, list)) {
            return true;
        }
        // remove value and backtract
        list.remove(list.size() - 1);
        return false;
    }
}