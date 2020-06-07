package support;

import java.util.ArrayList;

public class EachWithEveryOne {
    private final Integer[] first;
    private final Integer[] second;
    private final Integer[] third;

    public EachWithEveryOne(Integer first, Integer second, Integer third,Integer fourth,Integer fifth, Integer sixth, Integer seventh, Integer eight, Integer ninth) {
        this.first = new Integer[]{first, second, third};
        this.second = new Integer[]{fourth, fifth, sixth};
        this.third = new Integer[]{seventh, eight, ninth};
    }

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
