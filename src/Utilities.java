import java.util.ArrayList;
import java.util.Arrays;

public class Utilities {
    static private ArrayList<ArrayList<Integer>> pascal = new ArrayList<ArrayList<Integer>>();
    static {
        pascal.add(new ArrayList<Integer>(Arrays.asList(1)));
        pascal.add(new ArrayList<Integer>(Arrays.asList(1,1)));
        pascal.add(new ArrayList<Integer>(Arrays.asList(1,2,1)));
        pascal.add(new ArrayList<Integer>(Arrays.asList(1,3,3,1)));
        pascal.add(new ArrayList<Integer>(Arrays.asList(1,4,6,4,1)));
    }

    static int binomial(int n, int k){
        while(n >= pascal.size()){
            int s = pascal.size();
            ArrayList<Integer> next = new ArrayList<Integer>(s+1);
            ArrayList<Integer> prev = pascal.get(s - 1);
            next.add(1);
            for(int i = 1; i < prev.size(); i++){
                next.add(prev.get(i - 1) + prev.get(i));
            }
            next.add(1);
            pascal.add(next);
        }

        return pascal.get(n).get(k);
    }
}
