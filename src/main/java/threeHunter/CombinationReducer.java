package threeHunter;

import entity.CombinationNumbers;

import java.util.ArrayList;

public class CombinationReducer {

    public ArrayList<CombinationNumbers> returnListWithoutNumbers(ArrayList<CombinationNumbers> fullCombinationNumbers
            , Integer number1
            , Integer number2
            , Integer number3) {
        ArrayList<CombinationNumbers> combinationNumbers = new ArrayList<>();
        fullCombinationNumbers.forEach((com) -> {
            boolean isUnique = true;
            for (int i = 0; i <= 2; i++) {
                if (com.getNumber()[i].equals(number1) || com.getNumber()[i].equals(number2) || com.getNumber()[i].equals(number3)) {
                    isUnique = false;
                }
            }
            if (isUnique) {
                combinationNumbers.add(com);
            }
        });

        return combinationNumbers;
    }
}
