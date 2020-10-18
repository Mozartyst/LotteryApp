package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OneDraw implements Serializable {
    private Integer drawNumber;
    private LocalDateTime drawDate;
    private ArrayList<Integer> drawNumbers;
    private ArrayList<Integer> bonusBalls;

    public Integer getDrawNumber() {
        return drawNumber;
    }

    public void setDrawNumber(Integer drawNumber) {
        this.drawNumber = drawNumber;
    }

    public LocalDateTime getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(LocalDateTime drawDate) {
        this.drawDate = drawDate;
    }

    public ArrayList<Integer> getDrawNumbers() {
        return drawNumbers;
    }

    public void setDrawNumbers(ArrayList<Integer> drawNumbers) {
        this.drawNumbers = drawNumbers;
    }

    public ArrayList<Integer> getBonusBalls() {
        return bonusBalls;
    }

    public void setBonusBalls(ArrayList<Integer> bonusBalls) {
        this.bonusBalls = bonusBalls;
    }

    @Override
    public String toString() {
        return "OneDraw{" +
                "drawNumber=" + drawNumber +
                ", drawDate=" + drawDate +
                ", drawNumbers=" + drawNumbers +
                ", bonusBalls=" + bonusBalls +
                '}';
    }
}
