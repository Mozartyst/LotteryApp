package entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Consumer;

public class MultiCombinationKeys implements Comparable<MultiCombinationKeys>, Serializable, Iterable<CombinationNumbers> {

    private final CombinationNumbers[] keys;
    private final TreeMap<Integer, Integer> whatNumbers = new TreeMap<>();

    public MultiCombinationKeys(CombinationNumbers firstKey) {
        this.keys = new CombinationNumbers[]{firstKey};
    }

    public MultiCombinationKeys(CombinationNumbers firstKey, CombinationNumbers secondKey) {
        this.keys = new CombinationNumbers[]{firstKey, secondKey};
    }

    public MultiCombinationKeys(CombinationNumbers firstKey, CombinationNumbers secondKey, CombinationNumbers thirdKey) {
        this.keys = new CombinationNumbers[]{firstKey, secondKey, thirdKey};
    }

    public MultiCombinationKeys(CombinationNumbers firstKey, CombinationNumbers secondKey, CombinationNumbers thirdKey, CombinationNumbers fourthKey) {
        this.keys = new CombinationNumbers[]{firstKey, secondKey, thirdKey, fourthKey};
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

    public void setWhatNumbers(Integer whatNumber, Integer howMuch) {
        if (whatNumbers.containsKey(whatNumber)) {
            whatNumbers.replace(whatNumber, whatNumbers.get(whatNumber) + howMuch);
        } else {
            whatNumbers.put(whatNumber, howMuch);
        }
    }

    public TreeMap<Integer, Integer> getWhatNumbers() {
        return whatNumbers;
    }

    public String createIdForDB() {
        String id = "";
        for (int i = 0; i < getKeys().length; i++) {
            id = id + "/";
            for (int j = 0; j < getKeys()[i].getNumber().length; j++) {
                id = id + getKeys()[i].getNumber()[j] + ",";
            }
        }
        return id;
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

            CombinationNumbers[] list = getKeys();
            int actual = 0;

            @Override
            public boolean hasNext() {
                return actual < list.length;
            }

            @Override
            public CombinationNumbers next() {
                CombinationNumbers zwrot = list[actual];
                actual++;
                return zwrot;
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
