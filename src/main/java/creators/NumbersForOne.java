package creators;

import entity.OneDraw;
import support.Auxiliary;

import java.util.*;

public class NumbersForOne {

    public List<Integer> getNumbersForOne(Integer first, ArrayList<OneDraw> lotteryNumbers) {
        List<Integer> thirdNumbers = new ArrayList<>();
        Map<Integer, Integer> numbers = new HashMap<>();
        lotteryNumbers.stream()
                .filter((x) -> x.getDrawNumbers().contains(first))
                .forEach((x) -> x.getDrawNumbers()
                        .forEach((number) -> {
                            if (!number.equals(first)) {
                                if (numbers.containsKey(number)) {
                                    numbers.replace(number, numbers.get(number) + 1);
                                } else {
                                    numbers.put(number, 1);
                                }
                            }
                        }));
        System.out.println(Auxiliary.returnMaxKey(numbers));
        return thirdNumbers;
    }
}
