package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class CombinationNumbers implements Comparable<CombinationNumbers>, Serializable, Iterable<Integer> {
    private final ArrayList<Integer> listOfIndexesWhereAppeared = new ArrayList<>();
    private final Integer[] numbers;

    public CombinationNumbers(Integer firstNumber) {
        this.numbers = new Integer[1];
        this.numbers[0] = firstNumber;
    }

    public CombinationNumbers(Integer firstNumber, Integer secondNumber) {
        this.numbers = new Integer[2];
        this.numbers[0] = firstNumber;
        this.numbers[1] = secondNumber;
    }

    public CombinationNumbers(Integer firstNumber, Integer secondNumber, Integer thirdNumber) {
        this.numbers = new Integer[3];
        this.numbers[0] = firstNumber;
        this.numbers[1] = secondNumber;
        this.numbers[2] = thirdNumber;
    }

    public CombinationNumbers(Integer firstNumber, Integer secondNumber, Integer thirdNumber, Integer fourthNumber) {
        this.numbers = new Integer[4];
        this.numbers[0] = firstNumber;
        this.numbers[1] = secondNumber;
        this.numbers[2] = thirdNumber;
        this.numbers[3] = fourthNumber;
    }

    public ArrayList<Integer> getListOfIndexesWhereAppeared() {
        return listOfIndexesWhereAppeared;
    }

    public void addIndexToList(Integer index) {
        this.listOfIndexesWhereAppeared.add(index);
    }

    public Integer[] getNumbers() {
        return numbers;
    }

    public Integer getFirstNumber() {
        return numbers[0];
    }

    public Integer getSecondNumber() {
        return numbers[1];
    }

    public Integer getThirdNumber() {
        return numbers[2];
    }

    public Integer getFourthNumber() {
        return numbers[3];
    }

    public boolean containsIndex(Integer index) {
        return listOfIndexesWhereAppeared.contains(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CombinationNumbers integers = (CombinationNumbers) o;
        if (getNumbers().length == 1 && integers.getNumbers().length == 1) {
            return getFirstNumber().equals(integers.getFirstNumber());
        } else if (getNumbers().length == 2 && integers.getNumbers().length == 2) {
            return getFirstNumber().equals(integers.getFirstNumber())
                    && getSecondNumber().equals(integers.getSecondNumber());
        } else if (getNumbers().length == 3 && integers.getNumbers().length == 3) {
            return getFirstNumber().equals(integers.getFirstNumber())
                    && getSecondNumber().equals(integers.getSecondNumber())
                    && getThirdNumber().equals(integers.getThirdNumber());
        } else if (getNumbers().length == 4 && integers.getNumbers().length == 4) {
            return getFirstNumber().equals(integers.getFirstNumber())
                    && getSecondNumber().equals(integers.getSecondNumber())
                    && getThirdNumber().equals(integers.getThirdNumber())
                    && getFourthNumber().equals(integers.getFourthNumber());
        } else return false;
    }

    @Override
    public int hashCode() {
        if (getNumbers().length == 1) {
            return Objects.hash(getFirstNumber());
        } else if (getNumbers().length == 2) {
            return Objects.hash(getFirstNumber(), getSecondNumber());
        } else if (getNumbers().length == 3) {
            return Objects.hash(getFirstNumber(), getSecondNumber(), getThirdNumber());
        } else {
            return Objects.hash(getFirstNumber(), getSecondNumber(), getThirdNumber(), getFourthNumber());
        }
    }

    @Override
    public int compareTo(CombinationNumbers o) {
        if (numbers.length == 1) {
            if (numbers[0].compareTo(o.numbers[0]) == 0)
                if (o.getNumbers().length > 1)
                    return -1;
                else return 0;
            else return numbers[0].compareTo(o.numbers[0]);
        } else if (numbers.length == 2) {
            if (numbers[0].compareTo(o.numbers[0]) == 0)
                if (o.getNumbers().length > 1)
                    if (numbers[1].compareTo(o.numbers[1]) == 0)
                        if (o.getNumbers().length > 2)
                            return -1;
                        else return 0;
                    else return numbers[1].compareTo(o.numbers[1]);
                else return 1;
            else return numbers[0].compareTo(o.numbers[0]);
        } else if (numbers.length == 3) {
            if (numbers[0].compareTo(o.numbers[0]) == 0)
                if (o.getNumbers().length > 1)
                    if (numbers[1].compareTo(o.numbers[1]) == 0)
                        if (o.getNumbers().length > 2)
                            if (numbers[2].compareTo(o.numbers[2]) == 0)
                                if (o.getNumbers().length > 3)
                                    return -1;
                                else return 0;
                            else return numbers[2].compareTo(o.numbers[2]);
                        else return 1;
                    else return numbers[1].compareTo(o.numbers[1]);
                else return 1;
            else return numbers[0].compareTo(o.numbers[0]);
        } else {
            if (numbers[0].compareTo(o.numbers[0]) == 0)
                if (o.getNumbers().length > 1)
                    if (numbers[1].compareTo(o.numbers[1]) == 0)
                        if (o.getNumbers().length > 2)
                            if (numbers[2].compareTo(o.numbers[2]) == 0)
                                if (o.getNumbers().length > 3)
                                    if (numbers[3].compareTo(o.numbers[3]) == 0)
                                        return 0;
                                    else return numbers[3].compareTo(o.numbers[3]);
                                else return 1;
                            else return numbers[2].compareTo(o.numbers[2]);
                        else return 1;
                    else return numbers[1].compareTo(o.numbers[1]);
                else return 1;
            else return numbers[0].compareTo(o.numbers[0]);
        }
    }

    @Override
    public String toString() {
        if (numbers.length == 1) {
            return "Key{" +
                    numbers[0] +
                    '}';
        } else if (numbers.length == 2) {
            return "Key{" +
                    numbers[0] +
                    "," + numbers[1] +
                    '}';
        } else if (numbers.length == 3) {
            return "Key{" +
                    numbers[0] +
                    "," + numbers[1] +
                    "," + numbers[2] +
                    '}';
        } else return "Key{" +
                numbers[0] +
                "," + numbers[1] +
                "," + numbers[2] +
                "," + numbers[3] +
                '}';
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            final Integer[] list = getNumbers();
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
