package support;

import java.util.ArrayList;
import java.util.HashMap;

public class RepeatNumbers {
    private HashMap<ArrayList<Integer>, Integer> listsWithNumbersThatRepeated = new HashMap<>();

    public HashMap<ArrayList<Integer>, Integer> repeatNumbers(ArrayList<ArrayList<Integer>> lotteryNumber) {
        for (ArrayList<Integer> weekNumbers : lotteryNumber) {
            for (ArrayList<Integer> otherWeekNumbers : lotteryNumber) {
                if (lotteryNumber.indexOf(weekNumbers) < lotteryNumber.indexOf(otherWeekNumbers)) {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (Object number : weekNumbers) {
                        for (Object otherNumber : otherWeekNumbers) {
                            if (number.equals(otherNumber)) {
                                numbers.add((Integer) number);
                            }
                        }
                    }
                    if (listsWithNumbersThatRepeated.containsKey(numbers)) {
                        listsWithNumbersThatRepeated.replace(numbers, listsWithNumbersThatRepeated.get(numbers) + 1);
                    } else
                        listsWithNumbersThatRepeated.put(numbers, 1);
                }
            }
        }
//        listsWithNumbersThatRepeated.keySet().removeIf(arrayList -> arrayList.size() < 1);
//        listsWithNumbersThatRepeated.values().removeIf(integer -> integer < 2);
        return listsWithNumbersThatRepeated;
    }

}
