package entity;

import java.io.Serializable;
import java.util.Objects;

public class Slant implements Comparable<Slant>, Serializable {
    private Integer firstNumber;
    private Integer secondNumber;
    private boolean isRepeated;
    private Integer occurred;

    public Slant(Integer firstNumber, Integer secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slant slant = (Slant) o;
        return firstNumber.equals(slant.firstNumber) &&
                secondNumber.equals(slant.secondNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumber, secondNumber);
    }

    @Override
    public String toString() {
        return "Slant{" +
                "firstNumber=" + firstNumber +
                ", secondNumber=" + secondNumber +
                '}';
    }

    @Override
    public int compareTo(Slant o) {
        if (this.firstNumber.compareTo(o.firstNumber) == 0)
            if (this.secondNumber.compareTo(o.secondNumber) == 0)
                return 0;
            else
                return this.secondNumber.compareTo(o.secondNumber);
        else
            return this.firstNumber.compareTo(o.firstNumber);
    }
}
