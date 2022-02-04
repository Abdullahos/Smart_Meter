package com.root.meter.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meterId")
    private Meter meterId;

    private LocalDateTime timeStamp;
    private Long volt;
    private Long current;
    private Long energy;
    private Long activePower;
    private Long reActivePower;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Meter getMeterId() {
        return meterId;
    }

    public void setMeterId(Meter meterId) {
        this.meterId = meterId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getVolt() {
        return volt;
    }

    public void setVolt(Long volt) {
        this.volt = volt;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getEnergy() {
        return energy;
    }

    public void setEnergy(Long energy) {
        this.energy = energy;
    }

    public Long getActivePower() {
        return activePower;
    }

    public void setActivePower(Long activePower) {
        this.activePower = activePower;
    }

    public Long getReActivePower() {
        return reActivePower;
    }

    public void setReActivePower(Long reActivePower) {
        this.reActivePower = reActivePower;
    }
}
