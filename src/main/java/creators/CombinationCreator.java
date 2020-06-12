package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.ObjectForFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class CombinationCreator {
    private final ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("LotteryNumbersForAlgorithm");
    private final TreeMap<Integer, ArrayList<CombinationNumbers>> combinationMap = new TreeMap<>();

    public CombinationCreator() throws IOException, ClassNotFoundException {
    }

    public void createAllCombinationNumbers() throws IOException {
        ArrayList<CombinationNumbers> combinationNumbersArrayList = new ArrayList<>();
        for (int index = 0; index < lotteryNumbers.size(); index++) {

            if (index + 1 < lotteryNumbers.size()) {
                ArrayList<Integer> gamesEarlier = lotteryNumbers.get(index + 1);
//FIRST
                for (Integer firstNumber : gamesEarlier) {
                    CombinationNumbers keySingle = new CombinationNumbers(firstNumber);
                    addCombinationNumbers(keySingle, index, combinationNumbersArrayList);
//SECOND
                    for (Integer secondNumber : gamesEarlier) {
                        if (secondNumber <= firstNumber) {
                            continue;
                        }
                        CombinationNumbers keyDouble = new CombinationNumbers(firstNumber, secondNumber);
                        addCombinationNumbers(keyDouble, index, combinationNumbersArrayList);
//THIRD
                        for (Integer thirdNumber : gamesEarlier) {
                            if (thirdNumber <= secondNumber) {
                                continue;
                            }
                            CombinationNumbers keyTriple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                            addCombinationNumbers(keyTriple, index, combinationNumbersArrayList);
//FOURTH
                            for (Integer fourthNumber : gamesEarlier) {
                                if (fourthNumber <= thirdNumber) {
                                    continue;
                                }
                                CombinationNumbers keyQuadruple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber, fourthNumber);
                                addCombinationNumbers(keyQuadruple, index, combinationNumbersArrayList);
                            }
                        }
                    }
                }
            }
        }
        combinationNumbersArrayList.removeIf(con -> con.getListOfIndexesWhereAppeared().size() == 1);
        addToMap(combinationNumbersArrayList);
        saveMulti();
    }


    private void addCombinationNumbers(CombinationNumbers combination, Integer index, ArrayList<CombinationNumbers> combinationNumbersArrayList) {
        if (combinationNumbersArrayList.contains(combination)) {
            int i = combinationNumbersArrayList.indexOf(combination);
            combinationNumbersArrayList.get(i).addIndexToList(index + 1);
        } else {
            combination.addIndexToList(index + 1);
            combinationNumbersArrayList.add(combination);
        }
    }

    private void saveMulti() throws IOException {
        FileService.saveObject(combinationMap, "CombinationNumbers");
    }
    private void addToMap(ArrayList<CombinationNumbers> combinationNumbersArrayList){
        combinationNumbersArrayList.forEach((combination)->{
            combination.getListOfIndexesWhereAppeared().forEach((index)->{
                if (combinationMap.containsKey(index)){
                    combinationMap.get(index).add(combination);
                }else {
                    ArrayList<CombinationNumbers> combinationList = new ArrayList<>();
                    combinationList.add(combination);
                    combinationMap.put(index,combinationList);
                }
            });
        });
    }
}
