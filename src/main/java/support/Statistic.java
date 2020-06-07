package support;

import dataSupport.FileService;

import java.util.ArrayList;
import java.util.TreeMap;

public class Statistic {
    ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("FullLotteryNumbersFile");
    TreeMap<Integer, Integer> howOftenNumbersAppeared = returnHowOftenNumbersAppeared(lotteryNumbers);
    TreeMap<Integer, ArrayList<Integer>> distanceBetweenNumbers = returnDistanceBetweenNumbers(lotteryNumbers);

    private TreeMap<Integer, Integer> returnHowOftenNumbersAppeared(ArrayList<ArrayList<Integer>> lotteryNumbers) {
        TreeMap<Integer, Integer> howOftenNumbersAppeared = new TreeMap<>();
        for (ArrayList<Integer> weekNumbers : lotteryNumbers) {
            for (Object numbers : weekNumbers) {
                if (howOftenNumbersAppeared.containsKey(numbers)) {
                    howOftenNumbersAppeared.replace((Integer) numbers, howOftenNumbersAppeared.get(numbers) + 1);
                } else {
                    howOftenNumbersAppeared.put((Integer) numbers, 1);
                }
            }
        }
        return howOftenNumbersAppeared;
    }

    private TreeMap<Integer, ArrayList<Integer>> returnDistanceBetweenNumbers(ArrayList<ArrayList<Integer>> lotteryNumbers) {
        TreeMap<Integer, ArrayList<Integer>> distanceBetweenNumbers = new TreeMap<>();
        int[] lastIndex = new int[47];

        for (ArrayList<Integer> weekNumbers : lotteryNumbers) {
            for (Object number : weekNumbers) {
                ArrayList<Integer> distanceList = new ArrayList<>();
                if (lotteryNumbers.indexOf(weekNumbers) == 0){
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
    public Integer howOftenNumbersAppeared(int forNumber){
        return howOftenNumbersAppeared.get(forNumber);
    }
    public ArrayList<Integer> distanceBetweenNumbers(int forNumber){
        return distanceBetweenNumbers.get(forNumber);
    }
}
