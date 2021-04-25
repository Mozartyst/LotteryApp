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
                ArrayList<Integer> printList = new ArrayList<>();
                int first = nextWeeklyNumbers.getDrawNumbers().get(0);
                int second = nextWeeklyNumbers.getDrawNumbers().get(1);
                int third = nextWeeklyNumbers.getDrawNumbers().get(2);
                int fourth = nextWeeklyNumbers.getDrawNumbers().get(3);
                int fifth = nextWeeklyNumbers.getDrawNumbers().get(4);
                int sixth = nextWeeklyNumbers.getDrawNumbers().get(5);
                if (weeklyNumbers.getDrawNumbers().contains(first)) {
                    printList.add(first);
                }
                if (weeklyNumbers.getDrawNumbers().contains(second)) {
                    printList.add(second);
                }
                if (weeklyNumbers.getDrawNumbers().contains(third)) {
                    printList.add(third);
                }
                if (weeklyNumbers.getDrawNumbers().contains(fourth)) {
                    printList.add(fourth);
                }
                if (weeklyNumbers.getDrawNumbers().contains(fifth)) {
                    printList.add(fifth);
                }
                if (weeklyNumbers.getDrawNumbers().contains(sixth)) {
                    printList.add(sixth);
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
        fullList.forEach((x,y) -> {
            if (x.size() == manyNumbers && y.size() >= repeatedFrom) {
                System.out.println(counter[0] + " " + x + " / " + y.size());
                counter[0]++;
            }
        });
    }
}
