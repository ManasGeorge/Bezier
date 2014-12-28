import java.util.ArrayList;
import java.util.Arrays;

public class Utilities {
    static private ArrayList<ArrayList<Integer>> pascal = new ArrayList<>();
    static {
        pascal.add(new ArrayList<>(Arrays.asList(1)));
        pascal.add(new ArrayList<>(Arrays.asList(1,1)));
        pascal.add(new ArrayList<>(Arrays.asList(1,2,1)));
        pascal.add(new ArrayList<>(Arrays.asList(1,3,3,1)));
        pascal.add(new ArrayList<>(Arrays.asList(1,4,6,4,1)));
    }

    static int binomial(int n, int k){
        while(n >= pascal.size()){
            int s = pascal.size();
            ArrayList<Integer> next = new ArrayList<>(s+1);
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

    static double bezier(int n,double t){
        double sum = 0;
        for (int i = 0; i <= n; i++){
            sum += binomial(n,i) * Math.pow(1-t,i) * Math.pow(t,n-i);
        }
        return sum;
    }

    static double bezier2(double t){
        return t*t + 2*t*(1-t) + (1-t) * (1-t);
    }

    static double bezier3(double t){
        return t*t*t + 3*t*t*(1-t) + 3*t*(1-t)*(1-t) + (1-t)*(1-t)*(1-t);
    }

    static double weightedBezier(int n, double t, ArrayList<Double> weights){
        double sum = 0;
        for (int i = 0; i <= n; i++){
            sum += binomial(n,i) * Math.pow(1-t,i) * Math.pow(t,n-i) * weights.get(i);
        }
        return sum;
    }
}
