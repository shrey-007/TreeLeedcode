package creatingTree;

import TreeNode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of
 * unique values from 1 to n. Return the answer in any order.
 * */
public class UniqueBinarySearchTreesII {

    // suppose n=7
    // 1 2 3 4 5 6 7
    // toh isme 1 to 7 kisi ko bhi root bana stke hai(start=1, end=7)
    // agar 6 ko root maana toh left subtree mai {1,2,3,4,5} jaaege toh unme se koi bhi root ban skta hai toh left call mai start to i-1 tak element jaaege

    public List<TreeNode> generateTrees(int n) {
        return func(1,n);
    }

    public List<TreeNode> func(int start,int end){
        if(start>end){
            List<TreeNode> ans = new ArrayList<>();
            ans.add(null);
            return ans;
        }
        if(start==end){
            List<TreeNode> ans = new ArrayList<>();
            ans.add(new TreeNode(start));
            return ans;
        }

        List<TreeNode> ans =new ArrayList<>();

        // we have to choose ki start to end kisko root banaye

        for (int i = start; i <=end ; i++) {
            // i is root
            // left subtree mai start to i-1 elements jaega
            // right subtree mai i+1 to end elements jaega
            List<TreeNode> faith1 = func(start,i-1);
            List<TreeNode> faith2 = func(i+1,end);

            for (TreeNode leftBST: faith1){
                for(TreeNode rightBST: faith2){
                    TreeNode root = new TreeNode(i);
                    root.left=leftBST;
                    root.right=rightBST;
                    ans.add(root);
                }
            }
        }

        return ans;
    }

}
