package threeHunter;

import entity.CombinationNumbers;
import entity.OneDraw;

import java.util.ArrayList;
import java.util.TreeMap;

public class ThreesCreator {
    private final TreeMap<CombinationNumbers, Integer> threesMap = new TreeMap<>();
    private final ArrayList<OneDraw> lotteryNumbers;

    public ThreesCreator(ArrayList<OneDraw> lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }

    public TreeMap<CombinationNumbers, Integer> get() {
        for (OneDraw week : lotteryNumbers) {
            for (Integer number1 : week.getDrawNumbers()) {
                for (Integer number2 : week.getDrawNumbers()) {
                    if (number1 >= number2) {
                        continue;
                    }
                    for (Integer number3 : week.getDrawNumbers()) {
                        if (number2 >= number3) {
                            continue;
                        }
                        CombinationNumbers com = new CombinationNumbers(number1, number2, number3);
                        if (threesMap.containsKey(com)) {
                            threesMap.replace(com, threesMap.get(com) + 1);
                        } else {
                            threesMap.put(com, 1);
                        }
                    }
                }
            }
        }
        return threesMap;
    }
}
