package threeHunter;

import entity.CombinationNumbers;

import java.util.Objects;

public class KeyTripleList implements Comparable<KeyTripleList>{
    private CombinationNumbers combinationNumbers1;
    private CombinationNumbers combinationNumbers2;

    public CombinationNumbers getCombinationNumbers1() {
        return combinationNumbers1;
    }

    public void setCombinationNumbers1(CombinationNumbers combinationNumbers1) {
        this.combinationNumbers1 = combinationNumbers1;
    }

    public CombinationNumbers getCombinationNumbers2() {
        return combinationNumbers2;
    }

    public void setCombinationNumbers2(CombinationNumbers combinationNumbers2) {
        this.combinationNumbers2 = combinationNumbers2;
    }

    @Override
    public int compareTo(KeyTripleList o) {
        if (combinationNumbers1.compareTo(o.combinationNumbers1) == 0) {
            if (combinationNumbers2.compareTo(o.combinationNumbers2)==0){
                return 0;
            }else return combinationNumbers2.compareTo(o.combinationNumbers2);
        }else return combinationNumbers1.compareTo(o.combinationNumbers1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyTripleList that = (KeyTripleList) o;
        return combinationNumbers1.equals(that.combinationNumbers1) &&
                combinationNumbers2.equals(that.combinationNumbers2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(combinationNumbers1, combinationNumbers2);
    }

    @Override
    public String toString() {
        return "KeyTripleList{" +
                combinationNumbers1 +
                combinationNumbers2 +
                '}';
    }
}
