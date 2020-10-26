package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Dependencies implements Serializable {
    private TreeMap<Integer, Integer> isOverAndUnderAverageNAN;//Ile razy wystąpiła TA liczba dla wartości powyżej lub poniżej średniej afterNumber
    private TreeMap<Integer, Integer> isOverAndUnderAverageNAP;//Ile razy wystąpiła TA liczba dla wystąpień powyżej lub poniżej średniej afterPairs
    private TreeMap<Integer, Integer> isOverAndUnderAverageNAT;//Ile razy wystąpiła TA liczba dla wystąpień powyżej lub poniżej średniej afterTriple
    private TreeMap<Integer, Integer> afterNumbers;//po jakich liczbach wystepuje  TA liczba
    private TreeMap<CombinationNumbers, Occurrences> afterPairs; //po jakich parach wystepuje TA liczba
    private TreeMap<CombinationNumbers, Occurrences> afterTriple;//po jakich trojkach wystepuje  TA liczba
    private ArrayList<Duet> afterDuet;//po jakich duetach wystepuje TA liczba
    private Integer averageForNAN;//Średnia wystąpień afterNumber dla TEJ liczby.
    private Integer averageForNAP;//Średnia wystąpień afterPairs dla TEJ liczby.
    private ArrayList<Integer> distance;

    public TreeMap<Integer, Integer> getIsOverAndUnderAverageNAN() {
        return isOverAndUnderAverageNAN;
    }

    public void setIsOverAndUnderAverageNAN(TreeMap<Integer, Integer> isOverAndUnderAverageNAN) {
        this.isOverAndUnderAverageNAN = isOverAndUnderAverageNAN;
    }

    public TreeMap<Integer, Integer> getIsOverAndUnderAverageNAP() {
        return isOverAndUnderAverageNAP;
    }

    public void setIsOverAndUnderAverageNAP(TreeMap<Integer, Integer> isOverAndUnderAverageNAP) {
        this.isOverAndUnderAverageNAP = isOverAndUnderAverageNAP;
    }

    public TreeMap<Integer, Integer> getIsOverAndUnderAverageNAT() {
        return isOverAndUnderAverageNAT;
    }

    public void setIsOverAndUnderAverageNAT(TreeMap<Integer, Integer> isOverAndUnderAverageNAT) {
        this.isOverAndUnderAverageNAT = isOverAndUnderAverageNAT;
    }

    public TreeMap<Integer, Integer> getAfterNumbers() {
        return afterNumbers;
    }

    public void setAfterNumbers(TreeMap<Integer, Integer> afterNumbers) {
        this.afterNumbers = afterNumbers;
    }

    public TreeMap<CombinationNumbers, Occurrences> getAfterPairs() {
        return afterPairs;
    }

    public void setAfterPairs(TreeMap<CombinationNumbers, Occurrences> afterPairs) {
        this.afterPairs = afterPairs;
    }

    public TreeMap<CombinationNumbers, Occurrences> getAfterTriple() {
        return afterTriple;
    }

    public void setAfterTriple(TreeMap<CombinationNumbers, Occurrences> afterTriple) {
        this.afterTriple = afterTriple;
    }

    public ArrayList<Duet> getAfterDuet() {
        return afterDuet;
    }

    private void setAfterDuet(ArrayList<Duet> afterDuet) {
        this.afterDuet = afterDuet;
    }

    public void addAfterDuet(Duet duet) {
        if (this.afterDuet == null) {
            ArrayList<Duet> duets = new ArrayList<>();
            duets.add(duet);
            setAfterDuet(duets);
        } else {
            if (!this.afterDuet.contains(duet)) {
                this.afterDuet.add(duet);
            }
        }
    }

    public Integer getAverageForNAN() {
        return averageForNAN;
    }

    public void setAverageForNAN(Integer averageForNAN) {
        this.averageForNAN = averageForNAN;
    }

    public Integer getAverageForNAP() {
        return averageForNAP;
    }

    public void setAverageForNAP(Integer averageForNAP) {
        this.averageForNAP = averageForNAP;
    }

    public ArrayList<Integer> getDistance() {
        return distance;
    }

    public void setDistance(ArrayList<Integer> distance) {
        this.distance = distance;
    }
}
