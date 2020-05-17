package lottoPropositions;

import java.util.ArrayList;
import java.util.HashMap;

public class NumbersAppearTogether {


    public HashMap<Integer, Integer> numbersThatAppearTogether(ArrayList<ArrayList<Integer>> lotteryNumbers, Integer forNumber) {
        HashMap<Integer, Integer> pairsForNumber = new HashMap<>();

        for (ArrayList weekNumbers : lotteryNumbers) {
            for (Object o : weekNumbers) {
                if (o == forNumber) {
                    for (Object t : weekNumbers) {
                        if (t != o) {
                            if (pairsForNumber.containsKey(t)) {
                                pairsForNumber.replace((Integer) t, pairsForNumber.get(t) + 1);
                            } else {
                                pairsForNumber.put((Integer) t, 1);
                            }
                        }
                    }
                }
            }
        }
        return pairsForNumber;
    }
}



