package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Dependencies implements Serializable {
    private ArrayList<MultiCombinationKeys> afterMulti;
    private ArrayList<Duet> afterDuet;
    private ArrayList<Integer> distance;


    public ArrayList<MultiCombinationKeys> getAfterMulti() {
        return afterMulti;
    }

    public void setAfterMulti(ArrayList<MultiCombinationKeys> afterMulti) {
        this.afterMulti = afterMulti;
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
    public ArrayList<Integer> getDistance() {
        return distance;
    }

    public void setDistance(ArrayList<Integer> distance) {
        this.distance = distance;
    }
}
