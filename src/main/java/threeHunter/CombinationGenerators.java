package threeHunter;

import entity.CombinationNumbers;

import java.util.ArrayList;

public class CombinationGenerators {

    public ArrayList<CombinationNumbers> getCombination(int indexStart, int indexStopFirst, int indexStopSecond, int indexStopThird) {

        ArrayList<CombinationNumbers> listOfCombinations = new ArrayList<>();
        for (int i = indexStart; i <= indexStopFirst; i++) {
            for (int j = i + 1; j <= indexStopSecond; j++) {
                for (int k = j + 1; k <= indexStopThird; k++) {
                    listOfCombinations.add(new CombinationNumbers(i, j, k));
                }
            }
        }
        return listOfCombinations;
    }
}
