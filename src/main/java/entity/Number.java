package entity;

import java.io.Serializable;

public class Number implements Serializable {
    private int value;
    private Integer occurred;
    private Boolean isHot;
    private Dependency dependency;


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

    public Boolean isHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }


}
