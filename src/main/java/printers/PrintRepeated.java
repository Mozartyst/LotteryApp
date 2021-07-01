package printers;

import creators.AllCombinationCreator;
import entity.OneDraw;

import java.util.*;


public class PrintRepeated {

    public void print(ArrayList<OneDraw> lotteryNumbers, int manyNumbers, int repeatedFrom) {
        Map<List<Integer>, Set<Integer>> fullList = new HashMap<>();
        for (OneDraw weeklyNumbers : lotteryNumbers) {
            for (OneDraw nextWeeklyNumbers : lotteryNumbers) {
                if (lotteryNumbers.indexOf(nextWeeklyNumbers) <= lotteryNumbers.indexOf(weeklyNumbers)) {
                    continue;
                }
                List<Integer> printList = new ArrayList<>();
                for (int i = 0; i < nextWeeklyNumbers.getDrawNumbers().size(); i++) {
                    if (weeklyNumbers.getDrawNumbers().contains(nextWeeklyNumbers.getDrawNumbers().get(i))) {
                        printList.add(nextWeeklyNumbers.getDrawNumbers().get(i));
                    }
                }
                List<List<Integer>> allList = new AllCombinationCreator().getCombinationNumbers(printList);
                if (!allList.isEmpty()) {
                    for (List<Integer> a : allList) {
                        if (fullList.containsKey(a)) {
                            fullList.get(a).add(lotteryNumbers.indexOf(nextWeeklyNumbers));
                        } else {
                            Set<Integer> indexes = new TreeSet<>();
                            indexes.add(lotteryNumbers.indexOf(weeklyNumbers));
                            indexes.add(lotteryNumbers.indexOf(nextWeeklyNumbers));
                            fullList.put(a, indexes);
                        }
                    }
                }
            }
        }
        final int[] counter = {1};
        fullList.forEach((x, y) -> {
            if (x.size() == manyNumbers && y.size() >= repeatedFrom) {
                System.out.println(counter[0] + " " + x + " / " + y.size());
                counter[0]++;
            }
        });
    }
}
