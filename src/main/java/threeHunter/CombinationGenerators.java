package threeHunter;

import entity.CombinationNumbers;

import java.util.ArrayList;

public class CombinationGenerators {
    public ArrayList<CombinationNumbers> getCombinations(int min, int max) {

        if (max - min < 2) {
            System.out.println("Min 3 numbers required");
        }
        ArrayList<CombinationNumbers> listOfCombinations = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            for (int j = i + 1; j <= max; j++) {
                for (int k = j + 1; k <= max; k++) {
                    CombinationNumbers combinationNumbers = new CombinationNumbers(i,j,k);
                    listOfCombinations.add(combinationNumbers);
                }
            }
        }
        return listOfCombinations;
    }
}
