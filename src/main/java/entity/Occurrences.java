package entity;

import java.io.Serializable;

public class Occurrences implements Serializable {
    private Integer occurrence;
    private Integer hit;

    public Occurrences() {
    }

    public Occurrences(Integer occurrence, Integer hit) {
        this.occurrence = occurrence;
        this.hit = hit;
    }

    public Integer getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Integer occurrence) {
        this.occurrence = occurrence;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }
}
