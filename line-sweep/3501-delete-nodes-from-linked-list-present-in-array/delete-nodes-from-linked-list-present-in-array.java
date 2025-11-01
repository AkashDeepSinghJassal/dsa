/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        // set head
        // remove node point to next
        // keep removing until null
        HashSet<Integer> set = new HashSet<>();
        for(int n : nums) {
                set.add(n);
            }
        ;
        ListNode newHead = null;
        ListNode temp = head;
        ListNode initNode = new ListNode(0, head);
        newHead = initNode;
        while(initNode.next != null) {
            if(set.contains(initNode.next.val)) {
                initNode.next = initNode.next.next;
            } else {
                initNode = initNode.next;
            }
        }
        return newHead.next;
    }
}