package creatingTree;

import TreeNode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleFullBinaryTrees {

    // we have to return list of root of all trees
    public List<TreeNode> allPossibleFBT(int n) {
        // if n is even then koi full binary tree bhi ban skta, sirf odd ke case mai banega
        if(n%2==0){return new ArrayList<>();}
        // if n==1 , toh ek hi node hogi and voh khud hi root hogi
        if(n==1){
            // n=1 mai ek hi node banegi toh vahi banake return krdo
            List<TreeNode> ans = new ArrayList<>();
            ans.add(new TreeNode(0));
            return ans;
        }

        // if n is something else, toh right,left ke saare possible subtree lekar aao
        // let left call ne return kra ki {l1,l2,l3} subtrees ban skte hai, and right call ne return kra ki {r1,r2} subtrees ban skte hai
        // toh total 6 trees banaege jisme root current hoga anf left,right subtree hoge-:{l1,r1},{l2,r1},{l3,r1},{l1,r2},{l2,r2},{l3,r2}
        // toh ese r*l combinations banege

        List<TreeNode> ans = new ArrayList<>();

        // suppose n=5
        // 1 2 3 4 5 toh isme tum 1 to 5 kisi ko bhi root maan skte ho, agar 4 ko maans toh left subtree {1,2,3} se banega and right subtree {5} se banega
        // so run a loop to choose root, i is for root

        for (int i = 1; i <n ; i=i+2) {

            // i is root node

            List<TreeNode> faith1 = allPossibleFBT(i);   // all possible left subtrees
            List<TreeNode> faith2 = allPossibleFBT(n-i-1);  // all possible right subtrees

            // now iterate over them so to create r*l combinations
            for(TreeNode leftSubtrees: faith1){
                for(TreeNode rightSubtrees: faith2){
                    // current node ko root banao. i is current node. But value of node will still be 0 according to question
                    TreeNode root = new TreeNode(0);
                    // current node ka left ko koi ek subtree assign kro {l1,l2,l3} mai se
                    root.left=leftSubtrees;
                    // current node ka right ko koi ek subtree assign kro {r1,r2} mai se
                    root.right=rightSubtrees;
                    // a new tree is formed so add its root to the ans list, root is current node
                    ans.add(root);
                }
            }
        }

        return ans;
    }

}
