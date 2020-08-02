import dataSupport.FileService;
import entity.CombinationNumbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<ArrayList<Integer>> lotteryNumbers = FileService.loadObject("IrishLottery/FullIrishNumbersFile");
        ArrayList<CombinationNumbers> listOfCombinations = FileService.loadObject("EuroLottery/ListOfEuroTripleCombinations");
        Collections.sort(listOfCombinations);
        listOfCombinations.forEach((combination)->{
            if (combination.getListOfIndexesWhereAppeared().size()>1) {
                System.out.println(combination + " " + combination.getListOfIndexesWhereAppeared());
            }
        });

//        ArrayList<CombinationNumbers> listOfCombinations = new ArrayList<>();
//        for (ArrayList<Integer> weekNumbers : lotteryNumbers) {
//            for (Integer firstNumber : weekNumbers) {
//                for (Integer secondNumber : weekNumbers) {
//                    if (weekNumbers.indexOf(secondNumber) <= weekNumbers.indexOf(firstNumber)) {
//                        continue;
//                    }
//                    for (Integer thirdNumber : weekNumbers) {
//                        if (weekNumbers.indexOf(thirdNumber) <= weekNumbers.indexOf(secondNumber)) {
//                            continue;
//                        }
//                        CombinationNumbers combinationNumbers = new CombinationNumbers(firstNumber,secondNumber,thirdNumber);
//                        if (listOfCombinations.contains(combinationNumbers)){
//                            listOfCombinations.get(listOfCombinations.indexOf(combinationNumbers))
//                                    .addIndexToList(lotteryNumbers.indexOf(weekNumbers));
//                        }else {
//                        combinationNumbers.addIndexToList(lotteryNumbers.indexOf(weekNumbers));
//                        listOfCombinations.add(combinationNumbers);
//                        }
//                    }
//                }
//            }
//        }
//        FileService.saveObject(listOfCombinations,"ListOfIrishTripleCombinations");
    }
}
