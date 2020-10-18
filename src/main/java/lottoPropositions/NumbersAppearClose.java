package lottoPropositions;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.TreeMap;

public class NumbersAppearClose {
    public TreeMap<Integer, Integer> getMapWithNumbers(ArrayList<OneDraw> lotteryNumbers, Integer forNumber) {
        TreeMap<Integer, Integer> pairsForNumber = new TreeMap<>();

        for (OneDraw weekNumbers : lotteryNumbers) {
            if (lotteryNumbers.indexOf(weekNumbers) != 0 && lotteryNumbers.indexOf(weekNumbers) != lotteryNumbers.size()-1) {
                if (weekNumbers.getDrawNumbers().contains(forNumber)) {
                    ArrayList<Integer> nextWeek = lotteryNumbers.get(lotteryNumbers.indexOf(weekNumbers) + 1).getDrawNumbers();
                    ArrayList<Integer> thisWeek = lotteryNumbers.get(lotteryNumbers.indexOf(weekNumbers)).getDrawNumbers();
                    ArrayList<Integer> previousWeek = lotteryNumbers.get(lotteryNumbers.indexOf(weekNumbers) - 1).getDrawNumbers();

                    for (Integer t : nextWeek) {
                        if (!t.equals(forNumber)) {
                            if (pairsForNumber.containsKey(t)) {
                                pairsForNumber.replace(t, pairsForNumber.get(t) + 1);
                            } else {
                                pairsForNumber.put(t, 1);
                            }
                        }
                    }
                    for (Integer t : thisWeek) {
                        if (!t.equals(forNumber)) {
                            if (pairsForNumber.containsKey(t)) {
                                pairsForNumber.replace(t, pairsForNumber.get(t) + 1);
                            } else {
                                pairsForNumber.put(t, 1);
                            }
                        }
                    }
                    for (Integer t : previousWeek) {
                        if (!t.equals(forNumber)) {
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
