package entity;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

public class MultiCombinationKeys implements Comparable<MultiCombinationKeys>, Serializable, Iterable<CombinationNumbers> {

    private final CombinationNumbers[] keys;
    private final TreeMap<Integer, Set<Integer>> numbersAfter = new TreeMap<>();

    public MultiCombinationKeys(CombinationNumbers firstKey) {
        this.keys = new CombinationNumbers[]{firstKey};
    }

    public MultiCombinationKeys(CombinationNumbers firstKey, CombinationNumbers secondKey) {
        this.keys = new CombinationNumbers[]{firstKey, secondKey};
        Arrays.sort(keys);
    }

    public MultiCombinationKeys(CombinationNumbers firstKey, CombinationNumbers secondKey, CombinationNumbers thirdKey) {
        this.keys = new CombinationNumbers[]{firstKey, secondKey, thirdKey};
        Arrays.sort(keys);
    }

    public MultiCombinationKeys(CombinationNumbers firstKey, CombinationNumbers secondKey, CombinationNumbers thirdKey, CombinationNumbers fourthKey) {
        this.keys = new CombinationNumbers[]{firstKey, secondKey, thirdKey, fourthKey};
        Arrays.sort(keys);
    }


    public CombinationNumbers[] getKeys() {
        return keys;
    }

    public CombinationNumbers getFirstKey() {
        return keys[0];
    }

    public CombinationNumbers getSecondKey() {
        return keys[1];
    }

    public CombinationNumbers getThirdKey() {
        return keys[2];
    }

    public CombinationNumbers getFourthKey() {
        return keys[3];
    }

    public void addWhatNumbers(ArrayList<Integer> drawNumbers, Integer index) {
        for (Integer number:drawNumbers) {
            if (numbersAfter.containsKey(number)) {
                Set<Integer> integers = numbersAfter.get(number);
                numbersAfter.replace(number, integers);
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

    public String createIdForDB() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < getKeys().length; i++) {
            id.append("/");
            for (int j = 0; j < getKeys()[i].getNumbers().length; j++) {
                id.append(getKeys()[i].getNumbers()[j]).append(",");
            }
        }
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiCombinationKeys integers = (MultiCombinationKeys) o;
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
    public int compareTo(MultiCombinationKeys o) {
        if (keys.length == 1) {
            if (keys[0].compareTo(o.keys[0]) == 0)
                if (o.getKeys().length > 1)
                    return -1;
                else return 0;
            else return keys[0].compareTo(o.keys[0]);
        } else if (keys.length == 2) {
            if (keys[0].compareTo(o.keys[0]) == 0)
                if (o.getKeys().length > 1)
                    if (keys[1].compareTo(o.keys[1]) == 0)
                        if (o.getKeys().length > 2)
                            return -1;
                        else return 0;
                    else return keys[1].compareTo(o.keys[1]);
                else return 1;
            else return keys[0].compareTo(o.keys[0]);
        } else if (keys.length == 3) {
            if (keys[0].compareTo(o.keys[0]) == 0)
                if (o.getKeys().length > 1)
                    if (keys[1].compareTo(o.keys[1]) == 0)
                        if (o.getKeys().length > 2)
                            if (keys[2].compareTo(o.keys[2]) == 0)
                                if (o.getKeys().length > 3)
                                    return -1;
                                else return 0;
                            else return keys[2].compareTo(o.keys[2]);
                        else return 1;
                    else return keys[1].compareTo(o.keys[1]);
                else return 1;
            else return keys[0].compareTo(o.keys[0]);
        } else {
            if (keys[0].compareTo(o.keys[0]) == 0)
                if (o.getKeys().length > 1)
                    if (keys[1].compareTo(o.keys[1]) == 0)
                        if (o.getKeys().length > 2)
                            if (keys[2].compareTo(o.keys[2]) == 0)
                                if (o.getKeys().length > 3)
                                    if (keys[3].compareTo(o.keys[3]) == 0)
                                        return 0;
                                    else return keys[3].compareTo(o.keys[3]);
                                else return 1;
                            else return keys[2].compareTo(o.keys[2]);
                        else return 1;
                    else return keys[1].compareTo(o.keys[1]);
                else return 1;
            else return keys[0].compareTo(o.keys[0]);
        }
    }

    @Override
    public String toString() {
        if (keys.length == 1) {
            return "Key{" +
                    keys[0] +
                    '}';
        } else if (keys.length == 2) {
            return "Key{" +
                    keys[0] +
                    "," + keys[1] +
                    '}';
        } else if (keys.length == 3) {
            return "Key{" +
                    keys[0] +
                    "," + keys[1] +
                    "," + keys[2] +
                    '}';
        } else return "Key{" +
                keys[0] +
                "," + keys[1] +
                "," + keys[2] +
                "," + keys[3] +
                '}';
    }

    @Override
    public Iterator<CombinationNumbers> iterator() {
        return new Iterator<CombinationNumbers>() {

            final CombinationNumbers[] list = getKeys();
            int actual = 0;

            @Override
            public boolean hasNext() {
                return actual < list.length;
            }

            @Override
            public CombinationNumbers next() {
                CombinationNumbers combinationNumbers = list[actual];
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
