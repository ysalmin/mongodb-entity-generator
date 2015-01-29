package com.salm.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by ysalmin on 06.08.2014.
 */
public abstract class BaseEntity {
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
