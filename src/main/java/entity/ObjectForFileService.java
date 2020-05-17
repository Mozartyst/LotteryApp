package entity;

import java.io.Serializable;

public class ObjectForFileService<R> implements Serializable {
    private R object;

    public R getObject() {
        return object;
    }

    public void setObject(R object) {
        this.object = object;
    }
}

