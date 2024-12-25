package height;

import TreeNode.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given the root of a binary tree and an integer k.
 *
 * Return an integer denoting the size of the kth largest perfect binary subtree, or -1 if it doesn't exist.
 *
 * A perfect binary tree is a tree where all leaves are on the same level, and every parent has two children
 * */
public class KthLargestPerfectSubtreeSizeInBinaryTree {
    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        PriorityQueue<NodeWithHeight> priorityQueue = new PriorityQueue<>();
        func(root,priorityQueue,k);
        int height = priorityQueue.poll().height;
        return (int)Math.pow(2,height)-1;
    }
    public int func(TreeNode root,PriorityQueue<NodeWithHeight> priorityQueue,int k){
        if(root==null) return 0;

        // find whether current node can be root of perfect tree or not
        int faith1 = func(root.left,priorityQueue,k);
        int faith2 = func(root.right,priorityQueue,k);

        if(faith1==-1 || faith2==-1) return -1;


        if(faith1==faith2){
            // means both subtree have same height, so this can be the root of prefct tree
            priorityQueue.offer(new NodeWithHeight(root.val,faith1+1));
            if(priorityQueue.size()>k) priorityQueue.poll();
            // return height of any subtree faith1+1=faith2+1
            return faith1+1;
        }
        else{
            // means subtrees height are different so return -1
            return -1;
        }
    }

    class NodeWithHeight implements Comparable<NodeWithHeight>{
        int node;
        int height;

        public NodeWithHeight(int node, int height) {
            this.node = node;
            this.height = height;
        }

        @Override
        public int compareTo(NodeWithHeight o) {
            return this.height-o.height;
        }
    }

}
