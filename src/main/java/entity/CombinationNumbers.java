package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class CombinationNumbers implements Comparable<CombinationNumbers>, Serializable, Iterable<Integer> {
    private final ArrayList<Integer> listOfIndexesWhereAppeared = new ArrayList<>();
    private Integer[] number;

    public CombinationNumbers(Integer firstNumber) {
        this.number = new Integer[1];
        this.number[0] = firstNumber;
    }

    public CombinationNumbers(Integer firstNumber, Integer secondNumber) {
        this.number = new Integer[2];
        this.number[0] = firstNumber;
        this.number[1] = secondNumber;
    }

    public CombinationNumbers(Integer firstNumber, Integer secondNumber, Integer thirdNumber) {
        this.number = new Integer[3];
        this.number[0] = firstNumber;
        this.number[1] = secondNumber;
        this.number[2] = thirdNumber;
    }

    public CombinationNumbers(Integer firstNumber, Integer secondNumber, Integer thirdNumber, Integer fourthNumber) {
        this.number = new Integer[4];
        this.number[0] = firstNumber;
        this.number[1] = secondNumber;
        this.number[2] = thirdNumber;
        this.number[3] = fourthNumber;
    }

    public ArrayList<Integer> getListOfIndexesWhereAppeared() {
        return listOfIndexesWhereAppeared;
    }

    public void addIndexToList(Integer index) {
        this.listOfIndexesWhereAppeared.add(index);
    }

    public Integer[] getNumber() {
        return number;
    }

    public void setNumber(Integer[] number) {
        this.number = number;
    }

    public Integer getFirstNumber() {
        return number[0];
    }

    public Integer getSecondNumber() {
        return number[1];
    }

    public Integer getThirdNumber() {
        return number[2];
    }

    public Integer getFourthNumber() {
        return number[3];
    }

    public boolean containsIndex(Integer index) {
        return listOfIndexesWhereAppeared.contains(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CombinationNumbers integers = (CombinationNumbers) o;
        if (getNumber().length == 1 && integers.getNumber().length == 1) {
            return getFirstNumber().equals(integers.getFirstNumber());
        } else if (getNumber().length == 2 && integers.getNumber().length == 2) {
            return getFirstNumber().equals(integers.getFirstNumber())
                    && getSecondNumber().equals(integers.getSecondNumber());
        } else if (getNumber().length == 3 && integers.getNumber().length == 3) {
            return getFirstNumber().equals(integers.getFirstNumber())
                    && getSecondNumber().equals(integers.getSecondNumber())
                    && getThirdNumber().equals(integers.getThirdNumber());
        } else if (getNumber().length == 4 && integers.getNumber().length == 4) {
            return getFirstNumber().equals(integers.getFirstNumber())
                    && getSecondNumber().equals(integers.getSecondNumber())
                    && getThirdNumber().equals(integers.getThirdNumber())
                    && getFourthNumber().equals(integers.getFourthNumber());
        } else return false;
    }

    @Override
    public int hashCode() {
        if (getNumber().length == 1) {
            return Objects.hash(getFirstNumber());
        } else if (getNumber().length == 2) {
            return Objects.hash(getFirstNumber(), getSecondNumber());
        } else if (getNumber().length == 3) {
            return Objects.hash(getFirstNumber(), getSecondNumber(), getThirdNumber());
        } else {
            return Objects.hash(getFirstNumber(), getSecondNumber(), getThirdNumber(), getFourthNumber());
        }
    }

    @Override
    public int compareTo(CombinationNumbers o) {
        if (number.length == 1) {
            if (number[0].compareTo(o.number[0]) == 0)
                if (o.getNumber().length > 1)
                    return -1;
                else return 0;
            else return number[0].compareTo(o.number[0]);
        } else if (number.length == 2) {
            if (number[0].compareTo(o.number[0]) == 0)
                if (o.getNumber().length > 1)
                    if (number[1].compareTo(o.number[1]) == 0)
                        if (o.getNumber().length > 2)
                            return -1;
                        else return 0;
                    else return number[1].compareTo(o.number[1]);
                else return 1;
            else return number[0].compareTo(o.number[0]);
        } else if (number.length == 3) {
            if (number[0].compareTo(o.number[0]) == 0)
                if (o.getNumber().length > 1)
                    if (number[1].compareTo(o.number[1]) == 0)
                        if (o.getNumber().length > 2)
                            if (number[2].compareTo(o.number[2]) == 0)
                                if (o.getNumber().length > 3)
                                    return -1;
                                else return 0;
                            else return number[2].compareTo(o.number[2]);
                        else return 1;
                    else return number[1].compareTo(o.number[1]);
                else return 1;
            else return number[0].compareTo(o.number[0]);
        } else {
            if (number[0].compareTo(o.number[0]) == 0)
                if (o.getNumber().length > 1)
                    if (number[1].compareTo(o.number[1]) == 0)
                        if (o.getNumber().length > 2)
                            if (number[2].compareTo(o.number[2]) == 0)
                                if (o.getNumber().length > 3)
                                    if (number[3].compareTo(o.number[3]) == 0)
                                        return 0;
                                    else return number[3].compareTo(o.number[3]);
                                else return 1;
                            else return number[2].compareTo(o.number[2]);
                        else return 1;
                    else return number[1].compareTo(o.number[1]);
                else return 1;
            else return number[0].compareTo(o.number[0]);
        }
    }

    @Override
    public String toString() {
        if (number.length == 1) {
            return "Key{" +
                    number[0] +
                    '}';
        } else if (number.length == 2) {
            return "Key{" +
                    number[0] +
                    "," + number[1] +
                    '}';
        } else if (number.length == 3) {
            return "Key{" +
                    number[0] +
                    "," + number[1] +
                    "," + number[2] +
                    '}';
        } else return "Key{" +
                number[0] +
                "," + number[1] +
                "," + number[2] +
                "," + number[3] +
                '}';
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            Integer[] list = getNumber();
            int actual = 0;

            @Override
            public boolean hasNext() {
                return actual < list.length;
            }

            @Override
            public Integer next() {
                int zwrot = list[actual];
                actual++;
                return zwrot;
            }

        };
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Objects.requireNonNull(action);
        for (Integer integer : this) {
            action.accept(integer);
        }
    }

}
