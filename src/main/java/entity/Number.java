package entity;

import java.io.Serializable;

public class Number implements Serializable {
    private final int value;
    private Integer occurred;
    private Dependencies dependencies;


    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Integer getOccurred() {
        return occurred;
    }

    public void setOccurred(Integer occurred) {
        this.occurred = occurred;
    }

    public Dependencies getDependency() {
        return dependencies;
    }

    public void setDependency(Dependencies dependencies) {
        this.dependencies = dependencies;
    }


}
