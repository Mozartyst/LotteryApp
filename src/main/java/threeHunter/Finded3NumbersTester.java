package threeHunter;

import entity.CombinationNumbers;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Finded3NumbersTester {
    public TreeMap<KeyTripleList, Integer> getStats(TreeMap<CombinationNumbers, TreeSet<Integer>> zmapka1, TreeMap<CombinationNumbers, TreeSet<Integer>> zmapka2) {
        TreeMap<KeyTripleList, Integer> proposition = new TreeMap();
        final long[] zmapka1Size = {0};
        final long[] zmapka2Size = {0};
        AtomicLong operationSize = new AtomicLong();
        zmapka1.forEach((x, y) -> zmapka1Size[0] += y.size());
        zmapka2.forEach((x, y) -> zmapka2Size[0] += y.size());
        operationSize.set(zmapka1Size[0] * zmapka2Size[0]);
        AtomicLong counter = new AtomicLong();
        counter.addAndGet(operationSize.get());

        zmapka1.forEach((x, y) -> {
            y.forEach((index) -> {
                zmapka2.forEach((x1, x2) -> {
                    KeyTripleList keyTripleList = new KeyTripleList();
                    x2.forEach((index1) -> {
                        counter.decrementAndGet();
                        if (counter.get() == (operationSize.get()/4)*3){
                            System.out.println("75%");
                        }
                        if (counter.get() == (operationSize.get()/4)*2){
                            System.out.println("50%");
                        }
                        if (counter.get() == (operationSize.get()/4)){
                            System.out.println("25%");
                        }

                        if (index.equals(index1)) {
                            keyTripleList.setCombinationNumbers1(x);
                            keyTripleList.setCombinationNumbers2(x1);
                            if (proposition.containsKey(keyTripleList)) {
                                proposition.put(keyTripleList, proposition.get(keyTripleList) + 1);
                            } else proposition.put(keyTripleList, 1);

                        }

                    });
                });
            });
        });
        return proposition;
    }
}
