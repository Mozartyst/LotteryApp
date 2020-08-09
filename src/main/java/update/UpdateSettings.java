package update;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UpdateSettings implements Serializable {
    LocalDateTime irishLastUpdate;
    LocalDateTime irishNextUpdate;
    LocalDateTime euroLastUpdate;
    LocalDateTime euroNextUpdate;
    LocalDateTime polishLastUpdate;
    LocalDateTime polishNextUpdate;

    public UpdateSettings(LocalDateTime irishLastUpdate, LocalDateTime irishNextUpdate, LocalDateTime euroLastUpdate, LocalDateTime euroNextUpdate, LocalDateTime polishLastUpdate, LocalDateTime polishNextUpdate) {
        this.irishLastUpdate = irishLastUpdate;
        this.irishNextUpdate = irishNextUpdate;
        this.euroLastUpdate = euroLastUpdate;
        this.euroNextUpdate = euroNextUpdate;
        this.polishLastUpdate = polishLastUpdate;
        this.polishNextUpdate = polishNextUpdate;
    }

    public LocalDateTime getIrishLastUpdate() {
        return irishLastUpdate;
    }

    public void setIrishLastUpdate(LocalDateTime irishLastUpdate) {
        this.irishLastUpdate = irishLastUpdate;
    }

    public LocalDateTime getIrishNextUpdate() {
        return irishNextUpdate;
    }

    public void setIrishNextUpdate(LocalDateTime irishNextUpdate) {
        this.irishNextUpdate = irishNextUpdate;
    }

    public LocalDateTime getEuroLastUpdate() {
        return euroLastUpdate;
    }

    public void setEuroLastUpdate(LocalDateTime euroLastUpdate) {
        this.euroLastUpdate = euroLastUpdate;
    }

    public LocalDateTime getEuroNextUpdate() {
        return euroNextUpdate;
    }

    public void setEuroNextUpdate(LocalDateTime euroNextUpdate) {
        this.euroNextUpdate = euroNextUpdate;
    }

    public LocalDateTime getPolishLastUpdate() {
        return polishLastUpdate;
    }

    public void setPolishLastUpdate(LocalDateTime polishLastUpdate) {
        this.polishLastUpdate = polishLastUpdate;
    }

    public LocalDateTime getPolishNextUpdate() {
        return polishNextUpdate;
    }

    public void setPolishNextUpdate(LocalDateTime polishNextUpdate) {
        this.polishNextUpdate = polishNextUpdate;
    }
}
