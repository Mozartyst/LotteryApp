package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.Number;
import entity.ObjectForFileService;
import entity.Occurrences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;

public class AlgorithmCreator1 {
    private final TreeMap<Integer, Number> listOfNumbers = FileService.loadObject("ListOfNumbers");
    private final ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LotteryNumbersForAlgorithm");
    private TreeMap<Integer, TreeMap<CombinationNumbers, Boolean>> algorithmFinished = new TreeMap<>();

    public AlgorithmCreator1() throws IOException, ClassNotFoundException {
    }

    public void createAlgorithm() {
        TreeMap<Integer, TreeMap<CombinationNumbers, TreeMap<Boolean, Integer>>> algorithm = new TreeMap<>();
        for (int i = 1; i <= 47; i++) {

            TreeMap<CombinationNumbers, TreeMap<Boolean, Integer>> algForNumber = new TreeMap<>();
            TreeMap<CombinationNumbers, Occurrences> afterNumbers = listOfNumbers.get(i).getDependency().getAfterPairs();
            int finalI = i;

            afterNumbers.forEach((combinationNumbers, value) -> {
                for (ArrayList<Integer> weekNumbers : lotteryNumbers) {

                    int index = lotteryNumbers.indexOf(weekNumbers);
                    if (index == 0) {
                        continue;
                    }
                    ArrayList<Integer> nextLottery = lotteryNumbers.get(index - 1);
                    if (weekNumbers.contains(combinationNumbers.getFirstNumber())&&weekNumbers.contains(combinationNumbers.getSecondNumber())) {
                        if (nextLottery.contains(finalI)) {
                            if (algForNumber.containsKey(combinationNumbers)) {
                                TreeMap<Boolean, Integer> booleanIntegerTreeMap = algForNumber.get(combinationNumbers);
                                if (booleanIntegerTreeMap.containsKey(true)) {
                                    booleanIntegerTreeMap.put(true, booleanIntegerTreeMap.get(true) + 1);
                                } else {
                                    booleanIntegerTreeMap.put(true, 1);
                                }
                                algForNumber.put(combinationNumbers, booleanIntegerTreeMap);
                            } else {
                                TreeMap<Boolean, Integer> booleanIntegerTreeMap = new TreeMap<>();
                                booleanIntegerTreeMap.put(true, 1);
                                algForNumber.put(combinationNumbers, booleanIntegerTreeMap);
                            }
                        } else {
                            if (algForNumber.containsKey(combinationNumbers)) {
                                TreeMap<Boolean, Integer> booleanIntegerTreeMap = algForNumber.get(combinationNumbers);
                                if (booleanIntegerTreeMap.containsKey(false)) {
                                    booleanIntegerTreeMap.put(false, booleanIntegerTreeMap.get(false) + 1);
                                } else {
                                    booleanIntegerTreeMap.put(false, 1);
                                }
                                algForNumber.put(combinationNumbers, booleanIntegerTreeMap);
                            } else {
                                TreeMap<Boolean, Integer> booleanIntegerTreeMap = new TreeMap<>();
                                booleanIntegerTreeMap.put(false, 1);
                                algForNumber.put(combinationNumbers, booleanIntegerTreeMap);
                            }
                        }
                    }
                }
            });
            algorithm.put(i, algForNumber);
        }
        findTrueAlgorithm(algorithm);
        try {
            saveObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findTrueAlgorithm(TreeMap<Integer, TreeMap<CombinationNumbers, TreeMap<Boolean, Integer>>> algorithm) {
        algorithm.forEach((number, afterNumberMap) -> {
            TreeMap<CombinationNumbers, Boolean> algorithmFinal = new TreeMap<>();
            TreeMap<CombinationNumbers, Integer> tempMap = new TreeMap<>();
            afterNumberMap.forEach((afterNumber, value) -> {
                Integer trueValue = value.get(true);
                Integer falseValue = value.get(false);
                if (trueValue == null) {
                    trueValue = falseValue;
                }else if (falseValue == null){
                    falseValue = 1;
                }
                tempMap.put(afterNumber, (falseValue / trueValue));

            });
            Collection<Integer> values1 = tempMap.values();
            ArrayList<Integer> values = new ArrayList<>(values1);
            Collections.sort(values);
            Collections.reverse(values);

            Integer first = values.get(0);
            Integer second = values.get(1);

            tempMap.forEach((x, y) -> {
                if (y == first || y == second) {
                    algorithmFinal.put(x, true);
                }
            });
            algorithmFinished.put(number, algorithmFinal);
        });
    }

    private void saveObject() throws IOException {
        FileService.saveObject(algorithmFinished, "AlgorithmFile1");
    }
}

