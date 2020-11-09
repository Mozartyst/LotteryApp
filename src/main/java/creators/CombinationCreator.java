package creators;

import entity.CombinationNumbers;

import java.util.ArrayList;

public class CombinationCreator {
    private final ArrayList<Integer> weekNumbers;
    private final int index;
    private final ArrayList<CombinationNumbers> combinationNumbers = new ArrayList<>();

    public CombinationCreator(ArrayList<Integer> weekNumbers, int index) {
        this.weekNumbers = weekNumbers;
        this.index = index;
    }

    public ArrayList<CombinationNumbers> getCombinationNumbers() {
//FIRST
        for (Integer firstNumber : weekNumbers) {
            CombinationNumbers keySingle = new CombinationNumbers(firstNumber);
            keySingle.addIndexToList(index);
            combinationNumbers.add(keySingle);
//SECOND
            for (Integer secondNumber : weekNumbers) {
                if (firstNumber < secondNumber) {
                    CombinationNumbers keyDouble = new CombinationNumbers(firstNumber, secondNumber);
                    keyDouble.addIndexToList(index);
                    combinationNumbers.add(keyDouble);
//THIRD
                    for (Integer thirdNumber : weekNumbers) {
                        if (secondNumber < thirdNumber) {
                            CombinationNumbers keyTriple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber);
                            keyTriple.addIndexToList(index);
                            combinationNumbers.add(keyTriple);
//FOURTH
                            for (Integer fourthNumber : weekNumbers) {
                                if (thirdNumber < fourthNumber) {
                                    CombinationNumbers keyQuadruple = new CombinationNumbers(firstNumber, secondNumber, thirdNumber, fourthNumber);
                                    keyQuadruple.addIndexToList(index);
                                    combinationNumbers.add(keyQuadruple);
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
