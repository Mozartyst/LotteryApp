package entity;

import java.io.Serializable;

public class ObjectForFileService<R> implements Serializable {
    private R object;

    public ObjectForFileService(R object) {
        this.object = object;
    }

    public R getObject() {
        return object;
    }

}

