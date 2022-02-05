package com.root.meter.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Meter {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
