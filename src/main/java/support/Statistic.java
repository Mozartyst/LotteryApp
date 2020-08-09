package support;

import java.util.ArrayList;
import java.util.TreeMap;

public class Statistic {
    private final ArrayList<ArrayList<Integer>> lotteryNumbers; //FullIrishNumbersFile
    //TreeMap<Integer, ArrayList<Integer>> distanceBetweenNumbers = returnDistanceBetweenNumbers(lotteryNumbers);

    public Statistic(ArrayList<ArrayList<Integer>> lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }


    private TreeMap<Integer, ArrayList<Integer>> returnDistanceBetweenNumbers(ArrayList<ArrayList<Integer>> lotteryNumbers) {
        TreeMap<Integer, ArrayList<Integer>> distanceBetweenNumbers = new TreeMap<>();
        int[] lastIndex = new int[47];

        for (ArrayList<Integer> weekNumbers : lotteryNumbers) {
            for (Object number : weekNumbers) {
                ArrayList<Integer> distanceList = new ArrayList<>();
                if (lotteryNumbers.indexOf(weekNumbers) == 0) {
                    distanceList.add(0);
                    distanceBetweenNumbers.put((Integer) number, distanceList);
                    continue;
                }
                if (distanceBetweenNumbers.containsKey(number)) {
                    distanceList = distanceBetweenNumbers.get(number);
                    distanceList.add((lotteryNumbers.indexOf(weekNumbers) - lastIndex[(int) number - 1]) - 1);
                    distanceBetweenNumbers.replace((Integer) number, distanceList);
                } else {
                    distanceList.add((lotteryNumbers.indexOf(weekNumbers) - lastIndex[(int) number - 1]));
                    distanceBetweenNumbers.put((int) number, distanceList);
                }
                lastIndex[(int) number - 1] = lotteryNumbers.indexOf(weekNumbers);
            }
        }
        return distanceBetweenNumbers;
    }
}
