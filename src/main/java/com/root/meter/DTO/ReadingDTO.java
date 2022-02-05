package com.root.meter.DTO;


import java.time.LocalDateTime;

public class ReadingDTO {
    private Long meterId;
    private LocalDateTime timeStamp;
    private Long volt;
    private Long current;
    private Long energy;
    private Long activePower;
    private Long reActivePower;

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
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
