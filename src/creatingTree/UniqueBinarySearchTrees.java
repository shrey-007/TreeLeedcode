package creatingTree;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        return func(1,n);
    }

    public int func(int start, int end){
        if(start>end){
            return 1;
        }
        if(start==end){
            return 1;
        }

        int ans = 0;

        for (int i = start; i <=end ; i++) {
            int faith1 = func(start,i-1);
            int faith2 = func(i+1,end);

            ans = ans + faith2*faith1;
        }

        return ans;
    }
}
