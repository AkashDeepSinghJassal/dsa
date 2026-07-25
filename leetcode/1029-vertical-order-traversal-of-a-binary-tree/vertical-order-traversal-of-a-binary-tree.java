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
    class Trio {
        TreeNode node;
        int x;
        int y;
        Trio(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        int SIZE = 2000;
        List[][] index = new List[SIZE][SIZE / 2];
        index[0][0] = new ArrayList<>();
        Queue<Trio> queue = new LinkedList<>();
        queue.add(new Trio(root, SIZE / 2, 0));
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Trio t = queue.remove();
                List<Integer> tempList = null;
                if(index[t.x][t.y] == null) {
                    index[t.x][t.y] = new ArrayList<>();
                }
                tempList = index[t.x][t.y];
                
                tempList.add(t.node.val);
                
                if(t.node.left != null)
                    queue.add(new Trio(t.node.left, t.x - 1, t.y + 1));
                if(t.node.right != null)
                    queue.add(new Trio(t.node.right, t.x + 1, t.y + 1));

            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i= 0; i < SIZE; i++) {
            List<Integer> vertList = new ArrayList<>();
            for(int j = 0; j < SIZE / 2; j++) {
                if(index[i][j] != null) {
                    index[i][j].sort(Comparator.naturalOrder());
                    vertList.addAll(index[i][j]);
                }
            }
            if(!vertList.isEmpty()) {
                result.add(vertList);
            }
        }
        return result;
    }
}