package entity;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

public class MultiCombinationNumber implements Comparable<MultiCombinationNumber>, Serializable, Iterable<CombinationNumbers> {

    private final Integer[][] complexNumber;
    private final Map<Integer, Integer> numbersAfter = new TreeMap<>();
    private final Set<Integer> indexes = new TreeSet<>();

    public MultiCombinationNumber(Integer[] firstComplex) {
        this.complexNumber = new Integer[1][0];
        this.complexNumber[0] = firstComplex;
    }

    public MultiCombinationNumber(Integer[] firstComplex, Integer[] secondComplex) {
        this.complexNumber = new Integer[2][0];
        this.complexNumber[0] = firstComplex;
        this.complexNumber[1] = secondComplex;
    }

    public MultiCombinationNumber(Integer[] firstComplex, Integer[] secondComplex, Integer[] thirdComplex) {
        this.complexNumber = new Integer[3][0];
        this.complexNumber[0] = firstComplex;
        this.complexNumber[1] = secondComplex;
        this.complexNumber[2] = thirdComplex;
    }

    public MultiCombinationNumber(Integer[] firstComplex, Integer[] secondComplex, Integer[] thirdComplex, Integer[] fourthComplex) {
        this.complexNumber = new Integer[4][0];
        this.complexNumber[0] = firstComplex;
        this.complexNumber[1] = secondComplex;
        this.complexNumber[2] = thirdComplex;
        this.complexNumber[3] = fourthComplex;

    }


    public Integer[][] getComplexNumber() {
        return complexNumber;
    }

    public CombinationNumbers getFirstKey() {
        CombinationNumbers com;
        if (complexNumber[0].length == 4) {
            com = new CombinationNumbers(complexNumber[0][0], complexNumber[0][1], complexNumber[0][2], complexNumber[0][3]);
        } else if (complexNumber[0].length == 3) {
            com = new CombinationNumbers(complexNumber[0][0], complexNumber[0][1], complexNumber[0][2]);
        } else if (complexNumber[0].length == 2) {
            com = new CombinationNumbers(complexNumber[0][0], complexNumber[0][1]);
        } else {
            com = new CombinationNumbers(complexNumber[0][0]);
        }
        return com;
    }

    public CombinationNumbers getSecondKey() {
        CombinationNumbers com;
        if (complexNumber[1].length == 4) {
            com = new CombinationNumbers(complexNumber[1][0], complexNumber[1][1], complexNumber[1][2], complexNumber[1][3]);
        } else if (complexNumber[1].length == 3) {
            com = new CombinationNumbers(complexNumber[1][0], complexNumber[1][1], complexNumber[1][2]);
        } else if (complexNumber[1].length == 2) {
            com = new CombinationNumbers(complexNumber[1][0], complexNumber[1][1]);
        } else {
            com = new CombinationNumbers(complexNumber[1][0]);
        }
        return com;
    }

    public CombinationNumbers getThirdKey() {
        CombinationNumbers com;
        if (complexNumber[2].length == 4) {
            com = new CombinationNumbers(complexNumber[2][0], complexNumber[2][1], complexNumber[2][2], complexNumber[2][3]);
        } else if (complexNumber[2].length == 3) {
            com = new CombinationNumbers(complexNumber[2][0], complexNumber[2][1], complexNumber[2][2]);
        } else if (complexNumber[2].length == 2) {
            com = new CombinationNumbers(complexNumber[2][0], complexNumber[2][1]);
        } else {
            com = new CombinationNumbers(complexNumber[2][0]);
        }
        return com;
    }

    public CombinationNumbers getFourthKey() {
        CombinationNumbers com;
        if (complexNumber[3].length == 4) {
            com = new CombinationNumbers(complexNumber[3][0], complexNumber[3][1], complexNumber[3][2], complexNumber[3][3]);
        } else if (complexNumber[3].length == 3) {
            com = new CombinationNumbers(complexNumber[3][0], complexNumber[3][1], complexNumber[3][2]);
        } else if (complexNumber[3].length == 2) {
            com = new CombinationNumbers(complexNumber[3][0], complexNumber[3][1]);
        } else {
            com = new CombinationNumbers(complexNumber[3][0]);
        }
        return com;
    }

    public void addWhatNumbers(ArrayList<Integer> drawNumbers) {
        for (Integer number : drawNumbers) {
            if (numbersAfter.containsKey(number)) {
                numbersAfter.replace(number, numbersAfter.get(number) + 1);
            } else {
                numbersAfter.put(number, 1);
            }
        }
    }

    public Map<Integer, Integer> getNumbersAfter() {
        return numbersAfter;
    }

    public Set<Integer> getIndexesWhereAppeared() {
        return indexes;
    }

    public void addIndex(Integer index) {
        indexes.add(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiCombinationNumber integers = (MultiCombinationNumber) o;
        if (getComplexNumber().length == 1 && integers.getComplexNumber().length == 1) {
            return getFirstKey().equals(integers.getFirstKey());
        } else if (getComplexNumber().length == 2 && integers.getComplexNumber().length == 2) {
            return getFirstKey().equals(integers.getFirstKey())
                    && getSecondKey().equals(integers.getSecondKey());
        } else if (getComplexNumber().length == 3 && integers.getComplexNumber().length == 3) {
            return getFirstKey().equals(integers.getFirstKey())
                    && getSecondKey().equals(integers.getSecondKey())
                    && getThirdKey().equals(integers.getThirdKey());
        } else if (getComplexNumber().length == 4 && integers.getComplexNumber().length == 4) {
            return getFirstKey().equals(integers.getFirstKey())
                    && getSecondKey().equals(integers.getSecondKey())
                    && getThirdKey().equals(integers.getThirdKey())
                    && getFourthKey().equals(integers.getFourthKey());
        } else return false;
    }

    @Override
    public int hashCode() {
        if (getComplexNumber().length == 1) {
            return Objects.hash(getFirstKey());
        } else if (getComplexNumber().length == 2) {
            return Objects.hash(getFirstKey(), getSecondKey());
        } else if (getComplexNumber().length == 3) {
            return Objects.hash(getFirstKey(), getSecondKey(), getThirdKey());
        } else {
            return Objects.hash(getFirstKey(), getSecondKey(), getThirdKey(), getFourthKey());
        }
    }

    @Override
    public int compareTo(MultiCombinationNumber o) {
        if (complexNumber.length == 1) {
            if (getFirstKey().compareTo(o.getFirstKey()) == 0)
                if (o.getComplexNumber().length > 1)
                    return -1;
                else return 0;
            else return getFirstKey().compareTo(o.getFirstKey());
        } else if (complexNumber.length == 2) {
            if (getFirstKey().compareTo(o.getFirstKey()) == 0)
                if (o.getComplexNumber().length > 1)
                    if (getSecondKey().compareTo(o.getSecondKey()) == 0)
                        if (o.getComplexNumber().length > 2)
                            return -1;
                        else return 0;
                    else return getSecondKey().compareTo(o.getSecondKey());
                else return 1;
            else return getFirstKey().compareTo(o.getFirstKey());
        } else if (complexNumber.length == 3) {
            if (getFirstKey().compareTo(o.getFirstKey()) == 0)
                if (o.getComplexNumber().length > 1)
                    if (getSecondKey().compareTo(o.getSecondKey()) == 0)
                        if (o.getComplexNumber().length > 2)
                            if (getThirdKey().compareTo(o.getThirdKey()) == 0)
                                if (o.getComplexNumber().length > 3)
                                    return -1;
                                else return 0;
                            else return getThirdKey().compareTo(o.getThirdKey());
                        else return 1;
                    else return getSecondKey().compareTo(o.getSecondKey());
                else return 1;
            else return getFirstKey().compareTo(o.getFirstKey());
        } else {
            if (getFirstKey().compareTo(o.getFirstKey()) == 0)
                if (o.getComplexNumber().length > 1)
                    if (getSecondKey().compareTo(o.getSecondKey()) == 0)
                        if (o.getComplexNumber().length > 2)
                            if (getThirdKey().compareTo(o.getThirdKey()) == 0)
                                if (o.getComplexNumber().length > 3)
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
        if (complexNumber.length == 1) {
            return "Key{" +
                    getFirstKey() +
                    '}';
        } else if (complexNumber.length == 2) {
            return "Key{" +
                    getFirstKey() +
                    "," + getSecondKey() +
                    '}';
        } else if (complexNumber.length == 3) {
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

            final Integer[][] list = getComplexNumber();
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

    public MultiCombinationNumber getSorted() {
        MultiCombinationNumber sortedMulti;
        if (complexNumber.length == 2) {
            if (complexNumber[0][0] < complexNumber[1][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[0], complexNumber[1]);
            }  else {
                sortedMulti = new MultiCombinationNumber(complexNumber[1], complexNumber[0]);

            }
        } else if (complexNumber.length == 3) {
            if (complexNumber[0][0] < complexNumber[1][0] && complexNumber[1][0] < complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[0], complexNumber[1], complexNumber[2]);

            } else if (complexNumber[0][0] < complexNumber[1][0] && complexNumber[1][0] > complexNumber[2][0] && complexNumber[0][0] < complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[0], complexNumber[2], complexNumber[1]);

            } else if (complexNumber[0][0] < complexNumber[1][0] && complexNumber[1][0] > complexNumber[2][0] && complexNumber[0][0] > complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[2], complexNumber[0], complexNumber[1]);

            } else if (complexNumber[0][0] > complexNumber[1][0] && complexNumber[1][0] < complexNumber[2][0] && complexNumber[0][0] < complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[1], complexNumber[0], complexNumber[2]);

            } else if (complexNumber[0][0] > complexNumber[1][0] && complexNumber[1][0] < complexNumber[2][0] && complexNumber[0][0] > complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[1], complexNumber[2], complexNumber[0]);

            } else {
                sortedMulti = new MultiCombinationNumber(complexNumber[2], complexNumber[1], complexNumber[0]);

            }
        }else { // 1,2,3,4 / 1,2,4,3 / 1,3,2,4 / 1,3,4,2 / 1,4,2,3 / 1,4,3,2 /
            // 2,3,4,1 / 2,3,1,4 / 2,4,1,3 / 2,4,3,1 / 2,1,3,4 / 2,1,4,3 /
            // 3,4,1,2 / 3,4,2,1 / 3,2,1,4 / 3,2,4,1 / 3,1,2,4 / 3,1,4,2 /
            // 4,3,2,1 / 4,3,1,2 / 4,2,1,3 / 4,2,3,1 / 4,1,2,3 / 4,1,3,2 /
            if (complexNumber[0][0] < complexNumber[1][0] && complexNumber[1][0] < complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[0], complexNumber[1], complexNumber[2]);
            } else if (complexNumber[0][0] < complexNumber[1][0] && complexNumber[1][0] > complexNumber[2][0] && complexNumber[0][0] < complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[0], complexNumber[2], complexNumber[1]);
            } else if (complexNumber[0][0] > complexNumber[1][0] && complexNumber[1][0] < complexNumber[2][0] && complexNumber[0][0] < complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[1], complexNumber[0], complexNumber[2]);
            } else if (complexNumber[0][0] < complexNumber[1][0] && complexNumber[1][0] > complexNumber[2][0] && complexNumber[0][0] > complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[2], complexNumber[0], complexNumber[1]);
            } else if (complexNumber[0][0] > complexNumber[1][0] && complexNumber[1][0] < complexNumber[2][0] && complexNumber[0][0] > complexNumber[2][0]) {
                sortedMulti = new MultiCombinationNumber(complexNumber[1], complexNumber[2], complexNumber[0]);
            } else {
                sortedMulti = new MultiCombinationNumber(complexNumber[2], complexNumber[1], complexNumber[0]);
            }
        }

        return sortedMulti;
    }

}
