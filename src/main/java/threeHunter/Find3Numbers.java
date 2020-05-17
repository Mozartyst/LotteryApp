package threeHunter;

import entity.CombinationNumbers;

import java.util.*;
import java.util.stream.Collectors;

public class Find3Numbers {
    public ArrayList<Integer> getIndexesForNumber(Integer number, ArrayList<ArrayList<Integer>> lotteryNumbers) {

        ArrayList<Integer> arrayListOfIndexes;

        arrayListOfIndexes = (ArrayList<Integer>) lotteryNumbers.stream()
                .filter(x -> x.contains(number))
                .map(x -> lotteryNumbers.indexOf(x))
                .collect(Collectors.toList());

        return arrayListOfIndexes;
    }

    public Map<ArrayList<Integer>, Integer> getMapOfQuantity(ArrayList<ArrayList<Integer>> threeList, ArrayList<ArrayList<Integer>> lotteryNumbers) {

        Set<Integer> tempSet = new HashSet<>();
        Map<ArrayList<Integer>, Integer> mapOfThrees = new HashMap<>();

        for (int i = 0; i < threeList.size(); i++) {

            for (int j = 0; j < 3; j++) {
                tempSet.addAll(getIndexesForNumber(threeList.get(i).get(j), lotteryNumbers));
            }
            mapOfThrees.put(threeList.get(i), tempSet.size());
            tempSet = new HashSet<>();
        }
        return mapOfThrees;
    }

    public Map<Integer, ArrayList<Integer>> getMapOfIndexes(int min, int max, ArrayList<ArrayList<Integer>> lotteryNumbers) {

        Map<Integer, ArrayList<Integer>> mapOfIndexes = new LinkedHashMap<>();

        for (int i = min; i <= max; i++) {
            mapOfIndexes.put(i, getIndexesForNumber(i, lotteryNumbers));
        }
        return mapOfIndexes;
    }

    public TreeMap<CombinationNumbers, TreeSet<Integer>> get3NumbersIndexes(ArrayList<CombinationNumbers> combinationNumbers, ArrayList<ArrayList<Integer>> lotteryNumbers) {

        TreeMap<CombinationNumbers, TreeSet<Integer>> mapOfThrees = new TreeMap<>();

        for (CombinationNumbers combinationKey : combinationNumbers) {
            TreeSet<Integer> tempSet = new TreeSet<>();
            for (Integer number : combinationKey) {
                tempSet.addAll(getIndexesForNumber(number, lotteryNumbers));
            }
            mapOfThrees.put(combinationKey, tempSet);
        }
        return mapOfThrees;
    }
}
