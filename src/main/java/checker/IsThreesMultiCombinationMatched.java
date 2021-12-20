package checker;

import entity.CombinationNumbers;
import entity.MultiCombinationNumber;
import entity.OneDraw;

public class IsThreesMultiCombinationMatched {
    public boolean isMatched(OneDraw oneDraw, MultiCombinationNumber multiCombinationNumber) {
        boolean isMatched = false;
        CombinationNumbers firstKey = multiCombinationNumber.getFirstKey();
        CombinationNumbers secondKey = multiCombinationNumber.getSecondKey();
        CombinationNumbers thirdKey = multiCombinationNumber.getThirdKey();
        if (oneDraw.getDrawNumbers().contains(firstKey.getFirstNumber())
                || oneDraw.getDrawNumbers().contains(firstKey.getSecondNumber())
                || oneDraw.getDrawNumbers().contains(firstKey.getThirdNumber())) {
            if (oneDraw.getDrawNumbers().contains(secondKey.getFirstNumber())
                    || oneDraw.getDrawNumbers().contains(secondKey.getSecondNumber())
                    || oneDraw.getDrawNumbers().contains(secondKey.getThirdNumber())) {
                if (oneDraw.getDrawNumbers().contains(thirdKey.getFirstNumber())
                        || oneDraw.getDrawNumbers().contains(thirdKey.getSecondNumber())
                        || oneDraw.getDrawNumbers().contains(thirdKey.getThirdNumber())) {
                    isMatched = true;
                }
            }
        }
        return isMatched;
    }
}
