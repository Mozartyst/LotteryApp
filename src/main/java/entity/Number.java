package entity;

import support.Auxiliary;

import java.io.Serializable;
import java.util.*;

public class Number implements Serializable, Comparable<Number> {
    private final int value;
    private int lastIndex;
    private final Map<Integer, Integer> occurredGaps = new TreeMap<>();
    private final Map<Integer, Integer> occurredWith = new TreeMap<>();
    private final List<Integer> indexesWhereAppeared = new ArrayList<>();

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getCurrentGap(int currentIndex) {
        return currentIndex - this.lastIndex;
    }

    public Set<Integer> getFourHighestGap() {
        return Auxiliary.returnFourHighestKey(occurredGaps);
    }

    public Set<Integer> getTwoHighestNumbersOccurredWith() {
        return Auxiliary.returnTwoHighestKey(occurredWith);
    }

    public Map<Integer, Integer> getOccurredWith() {
        return this.occurredWith;
    }

    public Map<Integer, Integer> getOccurredGaps() {
        return this.occurredGaps;
    }

    public void addIndex(Integer index) {
        indexesWhereAppeared.add(index);
        if (indexesWhereAppeared.size() > 1) {
            int gap = index - lastIndex;
            if (occurredGaps.containsKey(gap)) {
                occurredGaps.replace(gap, occurredGaps.get(gap) + 1);
            } else {
                occurredGaps.put(gap, 1);
            }
        }
        lastIndex = index;
    }

    public List<Integer> getIndexesWhereAppeared() {
        return this.indexesWhereAppeared;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Number o) {
        return value - o.value;
    }

}
