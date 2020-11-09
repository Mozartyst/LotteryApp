package creators;

import entity.*;
import dataSupport.FileService;
import entity.Number;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

public class NumberCreator {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final Properties properties;
    private final TreeMap<Integer, Number> listOfNumbers = new TreeMap<>();
    private final ArrayList<MultiCombinationKeys> multiCombinationList;

    public NumberCreator(ArrayList<OneDraw> lotteryNumbers, Properties properties) throws IOException, ClassNotFoundException {
        this.lotteryNumbers = lotteryNumbers;
        this.properties = properties;
        this.multiCombinationList = FileService.loadObject(properties.getProperty("afterMulti"));
    }

    public void createNumbers() throws IOException {
        TreeMap<Integer, ArrayList<Integer>> distance = returnDistanceBetweenNumbers();
        for (int i = 1; i <= Integer.parseInt(properties.getProperty("range")); i++) {
            Dependencies dependencies = new Dependencies();
            Number number = new Number(i);
            listOfNumbers.put(i, number);
            dependencies.setAfterMulti(NumberAfterMulti(i));
            dependencies.setDistance(distance.get(i));
            listOfNumbers.get(i).setDependency(dependencies);
            listOfNumbers.get(i).setOccurred(valueOfAppeared(i));
        }
        FileService.saveObject(listOfNumbers, properties.getProperty("listOfNumbers"));
    }

    private ArrayList<MultiCombinationKeys> NumberAfterMulti(int number) {
        ArrayList<MultiCombinationKeys> afterMulti = new ArrayList<>();
        for (MultiCombinationKeys m: multiCombinationList) {
            m.getWhatNumbers().forEach((num,value)->{
                if (num.equals(number)){
                    afterMulti.add(m);
                }
            });
        }
        return afterMulti;
    }

    private Integer valueOfAppeared(int forNumber) {
        int value = 0;
        for (OneDraw currentDraw : lotteryNumbers) {
            if (currentDraw.getDrawNumbers().contains(forNumber)) {
                value += 1;
            }
        }
        return value;
    }

    private TreeMap<Integer, ArrayList<Integer>> returnDistanceBetweenNumbers() {
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
