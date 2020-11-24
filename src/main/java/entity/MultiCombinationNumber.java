package entity;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

public class MultiCombinationNumber implements Comparable<MultiCombinationNumber>, Serializable, Iterable<CombinationNumbers> {

    private final int[][] keys;
    private final TreeMap<Integer, Set<Integer>> numbersAfter = new TreeMap<>();

    public MultiCombinationNumber(int[] firstKey) {
        this.keys = new int[1][0];
        this.keys[0] = firstKey;
    }

    public MultiCombinationNumber(int[] firstKey, int[] secondKey) {
        this.keys = new int[2][0];
        this.keys[0] = firstKey;
        this.keys[1] = secondKey;
        Arrays.sort(keys[0]);
    }

    public MultiCombinationNumber(int[] firstKey, int[] secondKey, int[] thirdKey) {
        this.keys = new int[3][0];
        this.keys[0] = firstKey;
        this.keys[1] = secondKey;
        this.keys[2] = thirdKey;
        Arrays.sort(keys[0]);
    }

    public MultiCombinationNumber(int[] firstKey, int[] secondKey, int[] thirdKey, int[] fourthKey) {
        this.keys = new int[4][0];
        this.keys[0] = firstKey;
        this.keys[1] = secondKey;
        this.keys[2] = thirdKey;
        this.keys[3] = fourthKey;
        Arrays.sort(keys[0]);
    }


    public int[][] getKeys() {
        return keys;
    }

    public CombinationNumbers getFirstKey() {
        CombinationNumbers com;
        if (keys[0].length == 4) {
            com = new CombinationNumbers(keys[0][0], keys[0][1], keys[0][2], keys[0][3]);
        } else if (keys[0].length == 3) {
            com = new CombinationNumbers(keys[0][0], keys[0][1], keys[0][2]);
        } else if (keys[0].length == 2) {
            com = new CombinationNumbers(keys[0][0], keys[0][1]);
        } else {
            com = new CombinationNumbers(keys[0][0]);
        }
        return com;
    }

    public CombinationNumbers getSecondKey() {
        CombinationNumbers com;
        if (keys[1].length == 4) {
            com = new CombinationNumbers(keys[1][0], keys[1][1], keys[1][2], keys[1][3]);
        } else if (keys[1].length == 3) {
            com = new CombinationNumbers(keys[1][0], keys[1][1], keys[1][2]);
        } else if (keys[1].length == 2) {
            com = new CombinationNumbers(keys[1][0], keys[1][1]);
        } else {
            com = new CombinationNumbers(keys[1][0]);
        }
        return com;
    }

    public CombinationNumbers getThirdKey() {
        CombinationNumbers com;
        if (keys[2].length == 4) {
            com = new CombinationNumbers(keys[2][0], keys[2][1], keys[2][2], keys[2][3]);
        } else if (keys[2].length == 3) {
            com = new CombinationNumbers(keys[2][0], keys[2][1], keys[2][2]);
        } else if (keys[2].length == 2) {
            com = new CombinationNumbers(keys[2][0], keys[2][1]);
        } else {
            com = new CombinationNumbers(keys[2][0]);
        }
        return com;
    }

    public CombinationNumbers getFourthKey() {
        CombinationNumbers com;
        if (keys[3].length == 4) {
            com = new CombinationNumbers(keys[3][0], keys[3][1], keys[3][2], keys[3][3]);
        } else if (keys[3].length == 3) {
            com = new CombinationNumbers(keys[3][0], keys[3][1], keys[3][2]);
        } else if (keys[3].length == 2) {
            com = new CombinationNumbers(keys[3][0], keys[3][1]);
        } else {
            com = new CombinationNumbers(keys[3][0]);
        }
        return com;
    }

    public void addWhatNumbers(ArrayList<Integer> drawNumbers, Integer index) {
        for (Integer number : drawNumbers) {
            if (numbersAfter.containsKey(number)) {
               numbersAfter.get(number).add(index);
            } else {
                Set<Integer> integers = new TreeSet<>();
                integers.add(index);
                numbersAfter.put(number, integers);
            }
        }
    }

    public TreeMap<Integer, Set<Integer>> getNumbersAfter() {
        return numbersAfter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiCombinationNumber integers = (MultiCombinationNumber) o;
        if (getKeys().length == 1 && integers.getKeys().length == 1) {
            return getFirstKey().equals(integers.getFirstKey());
        } else if (getKeys().length == 2 && integers.getKeys().length == 2) {
            return getFirstKey().equals(integers.getFirstKey())
                    && getSecondKey().equals(integers.getSecondKey());
        } else if (getKeys().length == 3 && integers.getKeys().length == 3) {
            return getFirstKey().equals(integers.getFirstKey())
                    && getSecondKey().equals(integers.getSecondKey())
                    && getThirdKey().equals(integers.getThirdKey());
        } else if (getKeys().length == 4 && integers.getKeys().length == 4) {
            return getFirstKey().equals(integers.getFirstKey())
                    && getSecondKey().equals(integers.getSecondKey())
                    && getThirdKey().equals(integers.getThirdKey())
                    && getFourthKey().equals(integers.getFourthKey());
        } else return false;
    }

    @Override
    public int hashCode() {
        if (getKeys().length == 1) {
            return Objects.hash(getFirstKey());
        } else if (getKeys().length == 2) {
            return Objects.hash(getFirstKey(), getSecondKey());
        } else if (getKeys().length == 3) {
            return Objects.hash(getFirstKey(), getSecondKey(), getThirdKey());
        } else {
            return Objects.hash(getFirstKey(), getSecondKey(), getThirdKey(), getFourthKey());
        }
    }

    @Override
    public int compareTo(MultiCombinationNumber o) {
        if (keys.length == 1) {
            if (getFirstKey().compareTo(o.getFirstKey()) == 0)
                if (o.getKeys().length > 1)
                    return -1;
                else return 0;
            else return getFirstKey().compareTo(o.getFirstKey());
        } else if (keys.length == 2) {
            if (getFirstKey().compareTo(o.getFirstKey()) == 0)
                if (o.getKeys().length > 1)
                    if (getSecondKey().compareTo(o.getSecondKey()) == 0)
                        if (o.getKeys().length > 2)
                            return -1;
                        else return 0;
                    else return getSecondKey().compareTo(o.getSecondKey());
                else return 1;
            else return getFirstKey().compareTo(o.getFirstKey());
        } else if (keys.length == 3) {
            if (getFirstKey().compareTo(o.getFirstKey()) == 0)
                if (o.getKeys().length > 1)
                    if (getSecondKey().compareTo(o.getSecondKey()) == 0)
                        if (o.getKeys().length > 2)
                            if (getThirdKey().compareTo(o.getThirdKey()) == 0)
                                if (o.getKeys().length > 3)
                                    return -1;
                                else return 0;
                            else return getThirdKey().compareTo(o.getThirdKey());
                        else return 1;
                    else return getSecondKey().compareTo(o.getSecondKey());
                else return 1;
            else return getFirstKey().compareTo(o.getFirstKey());
        } else {
            if (getFirstKey().compareTo(o.getFirstKey()) == 0)
                if (o.getKeys().length > 1)
                    if (getSecondKey().compareTo(o.getSecondKey()) == 0)
                        if (o.getKeys().length > 2)
                            if (getThirdKey().compareTo(o.getThirdKey()) == 0)
                                if (o.getKeys().length > 3)
                                    if (getFourthKey().compareTo(o.getFourthKey()) == 0)
                                        return 0;
                                    else return getFourthKey().compareTo(o.getFourthKey());
                                else return 1;
                            else return getThirdKey().compareTo(o.getThirdKey());
                        else return 1;
                    else return getSecondKey().compareTo(o.getSecondKey());
                else return 1;
            else return getFirstKey().compareTo(o.getFirstKey());
        }
    }

    @Override
    public String toString() {
        if (keys.length == 1) {
            return "Key{" +
                    getFirstKey() +
                    '}';
        } else if (keys.length == 2) {
            return "Key{" +
                    getFirstKey() +
                    "," + getSecondKey() +
                    '}';
        } else if (keys.length == 3) {
            return "Key{" +
                    getFirstKey() +
                    "," + getSecondKey() +
                    "," + getThirdKey() +
                    '}';
        } else return "Key{" +
                getFirstKey() +
                "," + getSecondKey() +
                "," + getThirdKey() +
                "," + getFourthKey() +
                '}';
    }

    @Override
    public Iterator<CombinationNumbers> iterator() {
        return new Iterator<CombinationNumbers>() {

            final int[][] list = getKeys();
            int actual = 0;

            @Override
            public boolean hasNext() {
                return actual < list.length;
            }

            @Override
            public CombinationNumbers next() {
                CombinationNumbers combinationNumbers;
                if (actual == 0) {
                    combinationNumbers = getFirstKey();
                } else if (actual == 1) {
                    combinationNumbers = getSecondKey();
                } else if (actual == 2) {
                    combinationNumbers = getThirdKey();
                } else {
                    combinationNumbers = getFourthKey();
                }
                actual++;
                return combinationNumbers;
            }

        };
    }

    @Override
    public void forEach(Consumer<? super CombinationNumbers> action) {
        Objects.requireNonNull(action);
        for (CombinationNumbers combinationNumbers : this) {
            action.accept(combinationNumbers);
        }
    }

}
