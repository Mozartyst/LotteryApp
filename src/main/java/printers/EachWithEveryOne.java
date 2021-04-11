package printers;

import entity.CombinationNumbers;

import java.util.Set;
import java.util.TreeSet;

public class EachWithEveryOne {

    public void printList(Set<Integer> numbersList) {
        Set<CombinationNumbers> list = new TreeSet<>();
        for (Integer first : numbersList) {
            for (Integer second : numbersList) {
                if (!second.equals(first)) {
                    for (Integer third : numbersList) {
                        if (!third.equals(first) && !third.equals(second)) {
                            list.add(new CombinationNumbers(first, second, third));
                        }
                    }
                }
            }
        }
        list.forEach(System.out::println);
        System.out.println(list.size());
    }
}
