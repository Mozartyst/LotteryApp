package entity;
import java.io.Serializable;
import java.util.TreeMap;

public class Dependency implements Serializable {
    private TreeMap<Integer,Integer> isOverAndUnderAverageNAN;//Ile razy wystąpiła TA liczba dla wartości powyżej lub poniżej średniej afterNumber
    private TreeMap<Integer,Integer> isOverAndUnderAverageNAP;//Ile razy wystąpiła TA liczba dla wystąpień powyżej lub poniżej średniej afterPairs
    private TreeMap<Integer,Integer> isOverAndUnderAverageNAT;//Ile razy wystąpiła TA liczba dla wystąpień powyżej lub poniżej średniej afterTriple
    private TreeMap<Integer,Integer> afterNumbers;//po jakich liczbach wystepuje  TA liczba
    private TreeMap<CombinationNumbers, Occurrences> afterPairs; //po jakich parach wystepuje TA liczba
    private TreeMap<CombinationNumbers, Occurrences> afterTriple;//po jakich trojkach wystepuje  TA liczba
    private TreeMap<Duet, Integer> afterDuet;//po jakich duetach wystepuje TA liczba
    private Integer averageForNAN;//Średnia wystąpień afterNumber dla TEJ liczby.
    private Integer averageForNAP;//Średnia wystąpień afterPairs dla TEJ liczby.

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

    public TreeMap<Duet, Integer> getAfterDuet() {
        return afterDuet;
    }

    private void setAfterDuet(TreeMap<Duet, Integer> afterDuet) {
        this.afterDuet = afterDuet;
    }
    public void addAfterDuet(TreeMap<Duet, Integer> afterDuet){
        if (this.afterDuet == null){
            setAfterDuet(afterDuet);
        }else {
            if (this.afterDuet.containsKey(afterDuet.firstKey())){
                this.afterDuet.replace(afterDuet.firstKey(),this.afterDuet.get(afterDuet.firstKey())+1);
            }else this.afterDuet.putAll(afterDuet);
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
}
