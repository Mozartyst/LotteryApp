package support;

import java.io.Serializable;

public class Settings implements Serializable {
    long lastUpdate;
    long nextLottery;

    public Settings(long lastUpdate, long nextLottery) {
        this.lastUpdate = lastUpdate;
        this.nextLottery = nextLottery;
    }

    public long getDateLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public long getNextUpdate() {
        return nextLottery;
    }

    public void setNextUpdate(long nextLottery) {
        this.nextLottery = nextLottery;
    }
}
