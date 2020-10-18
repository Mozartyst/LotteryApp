package lottoPropositions;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.TreeMap;

public class NumbersAppearTogether {


    public TreeMap<Integer, Integer> getMapWithNumbers(ArrayList<OneDraw> lotteryNumbers, Integer forNumber) {
        TreeMap<Integer, Integer> pairsForNumber = new TreeMap<>();

        for (OneDraw weekNumbers : lotteryNumbers) {
            for (Integer o : weekNumbers.getDrawNumbers()) {
                if (o.equals(forNumber)) {
                    for (Integer t : weekNumbers.getDrawNumbers()) {
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



