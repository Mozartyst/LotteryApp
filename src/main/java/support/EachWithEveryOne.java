package support;

import java.util.ArrayList;

public class EachWithEveryOne {
    private Integer[] first = {9,10,11};
    private Integer[] second = {13,14,15};
    private Integer[] third = {44,46,47};


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
