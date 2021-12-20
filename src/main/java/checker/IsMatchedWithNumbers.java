package checker;


import entity.Number;

public class IsMatchedWithNumbers {
    public boolean isMatched(Number number, Integer checkedNumber) {
        boolean isMatched = false;
        if (number.getThreeHighestNumbersOccurredWith().contains(checkedNumber)) {
            isMatched = true;
        }
        return isMatched;
    }
}
