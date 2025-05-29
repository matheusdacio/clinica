package support.core.entity;

import java.io.Serializable;

public interface BaseEntity extends Serializable {
    Object getId();
    void setId(Object id);
} 