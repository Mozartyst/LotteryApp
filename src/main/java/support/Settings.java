package support;

import java.io.Serializable;

public class Settings implements Serializable {
    long irishLastUpdate;
    long irishNextLottery;
    long euroLastUpdate;
    long euroNextLottery;

    public Settings(long irishLastUpdate, long irishNextLottery, long euroLastUpdate, long euroNextLottery) {
        this.irishLastUpdate = irishLastUpdate;
        this.irishNextLottery = irishNextLottery;
        this.euroLastUpdate = euroLastUpdate;
        this.euroNextLottery = euroNextLottery;
    }

    public long getIrishLastUpdate() {
        return irishLastUpdate;
    }

    public void setIrishLastUpdate(long irishLastUpdate) {
        this.irishLastUpdate = irishLastUpdate;
    }

    public long getIrishNextUpdate() {
        return irishNextLottery;
    }

    public void setIrishNextUpdate(long nextLottery) {
        this.irishNextLottery = nextLottery;
    }

    public long getEuroLastUpdate() {
        return euroLastUpdate;
    }

    public void setEuroLastUpdate(long euroLastUpdate) {
        this.euroLastUpdate = euroLastUpdate;
    }

    public long getEuroNextUpdate() {
        return euroNextLottery;
    }

    public void setEuroNextUpdate(long euroNextLottery) {
        this.euroNextLottery = euroNextLottery;
    }
}
