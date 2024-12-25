package creatingTree;

import TreeNode.TreeNode;

import java.util.ArrayList;

/**
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced
 * binary search tree.
 *  */
public class ConvertSortedListToBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {

        // first convert this linked list to array list
        ArrayList<Integer> arrayList = new ArrayList<>();

        ListNode temp = head;
        while (temp!=null){
            arrayList.add(temp.val);
            temp = temp.next;
        }

        // now we have to convert this arraylist from 0 to n-1, into a tree
        return func(0,arrayList.size()-1,arrayList);

    }

    // suppose list-{2,5,6,89,422,456,900,956,987,993,10005}
    // now we have choices ki kisko root maane, agar 993 ko root maana toh left subtree banega {2,5,6,89,422,456,900,956,987}
    // and right subtree banega {10005}.
    // But since apan ko height balanced bst chaiye toh always middle node ko root maano joki is case mai 456 hogi.

    public TreeNode func(int start,int end,ArrayList<Integer> nodes){
        if(start>end){return null;}
        // take the middle node and make it root
        int mid = (start+end)/2;
        TreeNode middleNode = new TreeNode(nodes.get(mid));
        // now the left subtree of the root is-: all elements on left of this element in arraylist(456 ko root maano toh {2,5,6,89,422} uske left subtree mai jaaege)
        // toh start se mid-1 vale elements left subtree mai jaaege
        middleNode.left=func(start,mid-1,nodes);
        // mid+1 to end vaale elements right subtree mai jaaege
        middleNode.right=func(mid+1,end,nodes);
        // middle node root bani hai toh usi ko return kro, coz jab apan ne call kra hai middleNode.left=func(start,mid-1,nodes);
        // toh usme apan expect kr rhe hai ki func() root return krega left subtree ka toh har call mai root return kro
        return middleNode;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
