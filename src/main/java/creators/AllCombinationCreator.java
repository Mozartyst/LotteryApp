package creators;

import java.util.ArrayList;
import java.util.List;

public class AllCombinationCreator {
    public List<List<Integer>> getCombinationNumbers(ArrayList<Integer> numbersList) {
        List<List<Integer>> combinationNumbers = new ArrayList<>();
        for (Integer firstNumber : numbersList) {
            List<Integer> one = new ArrayList<>();
            one.add(firstNumber);
            combinationNumbers.add(one);

            for (Integer secondNumber : numbersList) {
                if (firstNumber < secondNumber) {
                    List<Integer> two = new ArrayList<>();
                    two.add(firstNumber);
                    two.add(secondNumber);
                    combinationNumbers.add(two);
                    for (Integer thirdNumber : numbersList) {
                        if (secondNumber < thirdNumber) {
                            List<Integer> three = new ArrayList<>();
                            three.add(firstNumber);
                            three.add(secondNumber);
                            three.add(thirdNumber);
                            combinationNumbers.add(three);
                            for (Integer fourthNumber : numbersList) {
                                if (thirdNumber < fourthNumber) {
                                    List<Integer> four = new ArrayList<>();
                                    four.add(firstNumber);
                                    four.add(secondNumber);
                                    four.add(thirdNumber);
                                    four.add(fourthNumber);
                                    combinationNumbers.add(four);
                                    for (Integer fifthNumber : numbersList) {
                                        if (fourthNumber < fifthNumber) {
                                            List<Integer> five = new ArrayList<>();
                                            five.add(firstNumber);
                                            five.add(secondNumber);
                                            five.add(thirdNumber);
                                            five.add(fourthNumber);
                                            five.add(fifthNumber);
                                            combinationNumbers.add(five);
                                            for (Integer sixthNumber : numbersList) {
                                                if (fifthNumber < sixthNumber) {
                                                    List<Integer> six = new ArrayList<>();
                                                    six.add(firstNumber);
                                                    six.add(secondNumber);
                                                    six.add(thirdNumber);
                                                    six.add(fourthNumber);
                                                    six.add(fifthNumber);
                                                    six.add(sixthNumber);
                                                    combinationNumbers.add(six);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return combinationNumbers;
    }
}
