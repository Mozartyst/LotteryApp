package support;

import java.util.ArrayList;

public class EachWithEveryOne {
    private Integer[] first = {11,13,14};
    private Integer[] second = {18,19,20};
    private Integer[] third = {39,41,1};


    public ArrayList<ArrayList<Integer>> returnTriple() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (Integer first : first) {
            for (Integer second : second) {
                for (Integer third : third) {
                    ArrayList<Integer> trio = new ArrayList<>();
                    trio.add(first);
                    trio.add(second);
                    trio.add(third);
                    list.add(trio);
                }
            }
        }
        return list;
    }
}
