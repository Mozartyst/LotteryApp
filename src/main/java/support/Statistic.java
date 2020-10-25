package support;

import entity.OneDraw;

import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class Statistic {

    private TreeMap<Integer, ArrayList<Integer>> returnDistanceBetweenNumbers(ArrayList<OneDraw> lotteryNumbers, Properties properties) {
        TreeMap<Integer, ArrayList<Integer>> distanceBetweenNumbers = new TreeMap<>();
        int[] lastIndex = new int[Integer.parseInt(properties.getProperty("range"))];

        for (OneDraw weekNumbers : lotteryNumbers) {
            for (Integer number : weekNumbers.getDrawNumbers()) {
                ArrayList<Integer> distanceList;
                if (distanceBetweenNumbers.containsKey(number)) {
                    distanceList = distanceBetweenNumbers.get(number);
                    distanceList.add((lotteryNumbers.indexOf(weekNumbers) - lastIndex[number - 1]) - 1);
                    distanceBetweenNumbers.replace(number, distanceList);
                } else {
                    distanceList = new ArrayList<>();
                    distanceBetweenNumbers.put(number, distanceList);
                }
                lastIndex[number - 1] = lotteryNumbers.indexOf(weekNumbers);
            }
        }
        return distanceBetweenNumbers;
    }
}
