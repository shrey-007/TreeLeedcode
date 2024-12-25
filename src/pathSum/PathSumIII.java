package pathSum;

import TreeNode.TreeNode;

import java.util.HashMap;

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along
 * the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from
 * parent nodes to child nodes).
 * */
public class PathSumIII {

    // It is same as-: Given an array, count number of sub-arrays with given sum
    // We are given an binary tree and asked whether a path(consecutive nodes just like subarray) has sum equal to path
    // Toh same hi approach hai
    // saare root to leaf path par iterate kro, and cuurentSum ko store kro jo ki us path mai aane vaali nodes ke prefix sum hoga

    public int pathSum(TreeNode root, int targetSum) {
        int ans[]=new int[1];
        func(root,0,new HashMap<Integer,Integer>(),targetSum,ans);
        return ans[0];
    }

    public void func(TreeNode node, int currentSum,HashMap<Integer,Integer> hashMap,int targetSum,int count[]){
        if(node==null){
            return;
        }
        // get the current sum
        currentSum = currentSum + node.val;

        // update the hashmap if current sum is target
        // means there is a path from root to this node with sum target
        if(currentSum==targetSum){
            count[0]++;
        }

        // check whether any path exists that ends here and has sum target
        if (hashMap.containsKey(currentSum-targetSum)){
            // means there is a path which does not start from node but ends here with sum target
            count[0]=count[0]+hashMap.get(currentSum-targetSum);
        }

        // store current sum in the hashmap
        hashMap.put(currentSum,hashMap.getOrDefault(currentSum,0)+1);

        // call its left and right child
        func(node.left,currentSum,hashMap,targetSum,count);
        func(node.right,currentSum,hashMap,targetSum,count);

        // remove this from hashmap
        // is node ka prefix sum sirf iske neeche vaali nodes ke liye chaiye tha
        // ab jab uper ki dono recursion calls ho jaaegi and is line par aaoge toh yaha vaapis tree mai uper jaa rhe hai
        // uper jaate time is node ko hatana padega path se coz uper jaakr jab doosra path par call jaaaye toh ye node include na ho coz ye us path ka part nhi hogi
        hashMap.put(currentSum,hashMap.get(currentSum)-1);
        if(hashMap.get(currentSum)==0){hashMap.remove(currentSum);}
    }

}
