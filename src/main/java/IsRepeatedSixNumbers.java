import dataSupport.FileService;
import support.Auxiliary;

import java.util.ArrayList;

public class IsRepeatedSixNumbers {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> lotteryNumbers = Auxiliary.returnReversedListLotteryNumbers(FileService.loadObject("FullLotteryNumbersFile"));
        for (ArrayList<Integer> weeklyNumbers : lotteryNumbers) {
            for (ArrayList<Integer> nextWeeklyNumbers : lotteryNumbers) {
                if (lotteryNumbers.indexOf(nextWeeklyNumbers) <= lotteryNumbers.indexOf(weeklyNumbers)) {
                    continue;
                }
                int first = nextWeeklyNumbers.get(0);
                int second = nextWeeklyNumbers.get(1);
                int third = nextWeeklyNumbers.get(2);
                int fourth = nextWeeklyNumbers.get(3);
                int fifth = nextWeeklyNumbers.get(4);
                int sixth = nextWeeklyNumbers.get(5);
                if (weeklyNumbers.contains(first)) {
                    if (weeklyNumbers.contains(second)) {
                        if (weeklyNumbers.contains(third)) {
                            if (weeklyNumbers.contains(fourth)) {
                                System.out.println(weeklyNumbers + " " + lotteryNumbers.indexOf(weeklyNumbers) + " " + lotteryNumbers.indexOf(nextWeeklyNumbers) + " " + nextWeeklyNumbers);
                                System.out.println(lotteryNumbers.get(lotteryNumbers.indexOf(weeklyNumbers) + 1) + "        " + lotteryNumbers.get(lotteryNumbers.indexOf(nextWeeklyNumbers) + 1));
                                if (weeklyNumbers.contains(fifth)) {
                                    if (weeklyNumbers.contains(sixth)) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
