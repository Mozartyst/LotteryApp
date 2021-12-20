package checker;

import entity.Number;

public class IsMatchedWithGaps {
    public boolean isMatched(Number number, Integer currentIndex) {
        boolean isMatched = false;
        Integer currentGap = null;
        if (number.getIndexesWhereAppeared().get(number.getIndexesWhereAppeared().size() - 1) < currentIndex) {
            currentGap = currentIndex - number.getIndexesWhereAppeared().get(number.getIndexesWhereAppeared().size() - 1);
        } else {
            for (Integer index : number.getIndexesWhereAppeared()) {
                if (index < currentIndex
                        && number.getIndexesWhereAppeared().get(number.getIndexesWhereAppeared().indexOf(index) + 1) > currentIndex) {
                    currentGap = currentIndex - index;
                    break;
                }
            }
        }
        if (number.getThreeHighestGap().contains(currentGap)) {
            isMatched = true;
        }
        return isMatched;
    }
}
