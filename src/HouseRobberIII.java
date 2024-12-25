import TreeNode.TreeNode;

import java.util.HashMap;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses
 * in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken
 * into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 * */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        return func(root,true);
    }
    public int func(TreeNode node,boolean canRob){
        if(node==null){return 0;}
        // rob this house

        // since we robbed this house so we can't rob its child houses so pass false
        // Important thing is ki right and left call ka sum return krna hai, isko ese socho ki tum ne ek esa ghar rob kra
        // jiske left and right dono pa galiya hai toh tum dono galiyo ke ghar ko rob karoge na and dono se jo total rob
        // kra voh hoga tumhara ans, since tumne abhi is node ko rob kra toh dono galiyo ke kisi bhi ghar ko rob krlo
        // except start ghar of dono galiyan
        // yaha choices ye nhi hai ki right call krna hai ki left, instead rob and not rob hai choices
        int faith1 = 0;
        if(canRob){
            faith1 = func(node.left,false)+func(node.right,false)+node.val;
        }

        // don't rob the house
        int faith2 = func(node.left,true)+func(node.right,true);

        return Math.max(faith1,faith2);
    }

    // Apply memoization

    public int funcDP(TreeNode node, boolean canRob, HashMap<TreeNode,Integer> dp){
        if(node==null){return 0;}
        if(dp.containsKey(node)){return dp.get(node);}
        // rob this house
        // since we robbed this house so we can't rob its child houses so pass false
        int faith1 = 0;
        if(canRob){
            faith1 = funcDP(node.left,false,dp)+funcDP(node.right,false,dp)+node.val;
        }

        // don't rob the house
        int faith2 = funcDP(node.left,true,dp)+funcDP(node.right,true,dp);

        dp.put(node,Math.max(faith1,faith2));

        return Math.max(faith1,faith2);
    }

}
