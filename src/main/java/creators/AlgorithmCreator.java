package creators;

import dataSupport.FileService;
import entity.Number;

import java.io.IOException;
import java.util.*;


public class AlgorithmCreator {
    private final TreeMap<Integer, TreeMap<Integer, Boolean>> algorithmFinished = new TreeMap<>();
    private final TreeMap<Integer, Number> listOfNumbers;
    private final ArrayList<ArrayList<Integer>> lotteryNumbers;
    private final Properties properties;

    public AlgorithmCreator(ArrayList<ArrayList<Integer>> lotteryNumbers, TreeMap<Integer, Number> listOfNumbers, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.listOfNumbers = listOfNumbers;
        this.properties = properties;
    }

    public void createAlgorithm() {
        TreeMap<Integer, TreeMap<Integer, TreeMap<Boolean, Integer>>> algorithm = new TreeMap<>();
        for (int i = 1; i <= Integer.parseInt(properties.getProperty("range")); i++) {
            TreeMap<Integer, TreeMap<Boolean, Integer>> algForNumber = new TreeMap<>();
            TreeMap<Integer, Integer> afterNumbers = listOfNumbers.get(i).getDependency().getAfterNumbers();
            int finalI = i;
            afterNumbers.forEach((number, value) -> {
                for (ArrayList<Integer> weekNumbers : lotteryNumbers) {
                    int index = lotteryNumbers.indexOf(weekNumbers);
                    if (index == 0) {
                        continue;
                    }
                    ArrayList<Integer> nextLottery = lotteryNumbers.get(index - 1);
                    if (weekNumbers.contains(number)) {
                        if (nextLottery.contains(finalI)) {
                            if (algForNumber.containsKey(number)) {
                                TreeMap<Boolean, Integer> booleanIntegerTreeMap = algForNumber.get(number);
                                if (booleanIntegerTreeMap.containsKey(true)) {
                                    booleanIntegerTreeMap.put(true, booleanIntegerTreeMap.get(true) + 1);
                                } else {
                                    booleanIntegerTreeMap.put(true, 1);
                                }
                                algForNumber.put(number, booleanIntegerTreeMap);
                            } else {
                                TreeMap<Boolean, Integer> booleanIntegerTreeMap = new TreeMap<>();
                                booleanIntegerTreeMap.put(true, 1);
                                algForNumber.put(number, booleanIntegerTreeMap);
                            }
                        } else {
                            if (algForNumber.containsKey(number)) {
                                TreeMap<Boolean, Integer> booleanIntegerTreeMap = algForNumber.get(number);
                                if (booleanIntegerTreeMap.containsKey(false)) {
                                    booleanIntegerTreeMap.put(false, booleanIntegerTreeMap.get(false) + 1);
                                } else {
                                    booleanIntegerTreeMap.put(false, 1);
                                }
                                algForNumber.put(number, booleanIntegerTreeMap);
                            } else {
                                TreeMap<Boolean, Integer> booleanIntegerTreeMap = new TreeMap<>();
                                booleanIntegerTreeMap.put(false, 1);
                                algForNumber.put(number, booleanIntegerTreeMap);
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

    private void findTrueAlgorithm(TreeMap<Integer, TreeMap<Integer, TreeMap<Boolean, Integer>>> algorithm) {
        algorithm.forEach((number, afterNumberMap) -> {
            TreeMap<Integer, Boolean> algorithmFinal = new TreeMap<>();
            TreeMap<Integer, Integer> tempMap = new TreeMap<>();
            afterNumberMap.forEach((afterNumber, value) -> {
                Integer trueValue = value.get(true);
                Integer falseValue = value.get(false);
                if (trueValue == null) {
                    trueValue = falseValue;
                }
                tempMap.put(afterNumber, (falseValue / trueValue));

            });
            Collection<Integer> values1 = tempMap.values();
            ArrayList<Integer> values = new ArrayList<>(values1);
            Collections.sort(values);
            Collections.reverse(values);

            Integer first = values.get(0);
            Integer second = values.get(1);
            Integer third = values.get(2);


            tempMap.forEach((x, y) -> {
                if (y == first || y == second) {
                    algorithmFinal.put(x, true);
                }
            });
            algorithmFinished.put(number, algorithmFinal);
        });
    }

    private void saveObject() throws IOException {
        FileService.saveObject(algorithmFinished, properties.getProperty("algorithmFile"));
    }
}
