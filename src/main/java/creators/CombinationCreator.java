package creators;

import dataSupport.FileService;
import entity.CombinationNumbers;
import entity.OneDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class CombinationCreator {
    private final ArrayList<OneDraw> lotteryNumbers;
    private final ArrayList<CombinationNumbers> combinationNumbersArrayList = new ArrayList<>();
    private final Properties properties;

    public CombinationCreator(ArrayList<OneDraw> lotteryNumbers, Properties properties) {
        this.lotteryNumbers = lotteryNumbers;
        this.properties = properties;
    }

    public void createAllCombinationNumbers() throws IOException {

        for (int index = 0; index < lotteryNumbers.size(); index++) {

            ArrayList<Integer> currentDraw = lotteryNumbers.get(index).getDrawNumbers();
//FIRST
            for (Integer firstNumber : currentDraw) {
                CombinationNumbers keySingle = new CombinationNumbers(firstNumber);
                addCombinationNumbers(keySingle, index);
//SECOND
                for (Integer secondNumber : currentDraw) {
                    if (firstNumber < secondNumber) {
                        CombinationNumbers keyDouble = new CombinationNumbers(firstNumber, secondNumber);
                        addCombinationNumbers(keyDouble, index);
//THIRD
                        for (Integer thirdNumber : currentDraw) {
                            if (secondNumber < thirdNumber) {
                                CombinationNumbers keyTriple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                                addCombinationNumbers(keyTriple, index);
//FOURTH
                                for (Integer fourthNumber : currentDraw) {
                                    if (thirdNumber < fourthNumber) {
                                        CombinationNumbers keyQuadruple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber, fourthNumber);
                                        addCombinationNumbers(keyQuadruple, index);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        saveMulti();
    }

    private void addCombinationNumbers(CombinationNumbers combination, Integer index) {
        if (combinationNumbersArrayList.contains(combination)) {
            int i = combinationNumbersArrayList.indexOf(combination);
            combinationNumbersArrayList.get(i).addIndexToList(index);
        } else {
            combination.addIndexToList(index);
            combinationNumbersArrayList.add(combination);
        }
    }

    private void saveMulti() throws IOException {
        FileService.saveObject(combinationNumbersArrayList, properties.getProperty("combinationNumbers"));
    }
}
