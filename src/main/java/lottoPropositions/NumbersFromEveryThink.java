package lottoPropositions;

import entity.Number;
import entity.OneDraw;

import java.util.*;

public class NumbersFromEveryThink {
    public Set<Integer> get(ArrayList<OneDraw> lotteryNumbers
            , Map<Integer, Number> listOfNumbers
            , Properties properties
            , int index) {

        Set<Integer> numbers = new TreeSet<>();
        Set<Integer> numbers1 = new TreeSet<>();
        Set<Integer> numbersFinal = new TreeSet<>();

        for (int i = 1; i < Integer.parseInt(properties.getProperty("range")); i++) {
            Number number = listOfNumbers.get(i);
            int currentGap = number.getCurrentGap(index + 1);
            Set<Integer> fourHighestGap = number.getFourHighestGap();
            if (fourHighestGap.contains(currentGap)) {
                numbers.add(number.getValue());
            }
            for (Integer num : lotteryNumbers.get(index).getDrawNumbers()) {
                Set<Integer> fiveHighestNumbersOccurredWith = number.getTwoHighestNumbersOccurredWith();
                if (fiveHighestNumbersOccurredWith.contains(num)) {
                    numbers1.add(i);
                }
            }
        }
        for (Integer n : numbers) {
            numbersFinal.add(n);
            if (numbers1.contains(n)) {
                numbersFinal.add(n);
            }
            if (numbers1.contains(n + 1)) {
                numbersFinal.add(n + 1);
            }
            if (numbers1.contains(n - 1)) {
                numbersFinal.add(n - 1);
            }
        }

        System.out.println(numbersFinal);
        System.out.println(numbers);
        System.out.println(numbers1);
        System.out.println(lotteryNumbers.get(index + 1));

        return numbers;
    }
}
