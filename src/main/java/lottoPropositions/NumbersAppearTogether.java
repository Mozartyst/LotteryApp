package lottoPropositions;

import java.util.ArrayList;
import java.util.TreeMap;

public class NumbersAppearTogether {


    public TreeMap<Integer, Integer> getMapWithNumbers(ArrayList<ArrayList<Integer>> lotteryNumbers, Integer forNumber) {
        TreeMap<Integer, Integer> pairsForNumber = new TreeMap<>();

        for (ArrayList<Integer> weekNumbers : lotteryNumbers) {
            for (Integer o : weekNumbers) {
                if (o.equals(forNumber)) {
                    for (Integer t : weekNumbers) {
                        if (!t.equals(o)) {
                            if (pairsForNumber.containsKey(t)) {
                                pairsForNumber.replace(t, pairsForNumber.get(t) + 1);
                            } else {
                                pairsForNumber.put(t, 1);
                            }
                        }
                    }
                }
            }
        }
        return pairsForNumber;
    }
}



