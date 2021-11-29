package printers;

import entity.Number;

import java.util.Map;

public class OccurredWith {
    public void print(Map<Integer, Number> listOfNumbers, Integer forNumber) {
        listOfNumbers.get(forNumber).getOccurredWith().forEach((x,y)->System.out.println(x + " - " + y));
    }
}
