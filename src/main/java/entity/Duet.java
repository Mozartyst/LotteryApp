package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.TreeMap;

public class Duet implements Comparable<Duet>, Serializable {
    private final CombinationNumbers combinationNumbers;
    private boolean isRepeated;
    private Integer numberOfOccurred;
    private boolean isWin;
    private Integer numberOfWinnings;
    private TreeMap<Integer, Integer> goUp;
    private TreeMap<Integer, Integer> goDown;


    public Duet(CombinationNumbers combinationNumbers) {
        this.combinationNumbers = combinationNumbers;
    }

    public CombinationNumbers getCombinationNumbers() {
        return combinationNumbers;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public Integer getNumberOfOccurred() {
        return numberOfOccurred;
    }

    public void addOccurrence(Integer occurred) {
        if (this.numberOfOccurred == null) {
            this.numberOfOccurred = occurred;
        } else {
            this.numberOfOccurred = this.numberOfOccurred + occurred;
            if (this.numberOfOccurred > 1 && !isRepeated) {
                isRepeated = true;
            }
        }
    }

    public boolean isWin() {
        return isWin;
    }

    public Integer getNumberOfWinnings() {
        return numberOfWinnings;
    }

    public void addNumberOfWinnings(Integer numberOfWinnings) {
        if (this.numberOfWinnings == null) {
            this.numberOfWinnings = numberOfWinnings;
        } else {
            this.numberOfWinnings = this.numberOfWinnings + numberOfWinnings;
            if (numberOfWinnings > 0 && !isWin) {
                isWin = true;
            }
        }
    }

    public TreeMap<Integer, Integer> getGoUp() {
        return goUp;
    }

    public void setGoUp(TreeMap<Integer, Integer> goUp) {
        this.goUp = goUp;
    }

    public TreeMap<Integer, Integer> getGoDown() {
        return goDown;
    }

    public void setGoDown(TreeMap<Integer, Integer> goDown) {
        this.goDown = goDown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duet duet = (Duet) o;
        return combinationNumbers.equals(duet.combinationNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(combinationNumbers);
    }

    @Override
    public String toString() {
        return "Duet{" +
                combinationNumbers +
                '}';
    }

    @Override
    public int compareTo(Duet o) {
        if (this.combinationNumbers.compareTo(o.combinationNumbers) == 0) {
            return 0;
        } else {
            return this.combinationNumbers.compareTo(o.combinationNumbers);
        }
    }
}
