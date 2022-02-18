package com.root.meter.DTO;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DailyReadingDTO {
    private Long meterId;
    @NotNull(message="timeStamp can't be null")
    private LocalDate date;
    @NotNull(message="volt can't be null")
    private Double volt;
    @NotNull(message="current can't be null")
    private Double amber;
    private Double energy;
    private Double activePower;
    private Double reActivePower;

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getVolt() {
        return volt;
    }

    public void setVolt(Double volt) {
        this.volt = volt;
    }

    public Double getAmber() {
        return amber;
    }

    public void setAmber(Double amber) {
        this.amber = amber;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getActivePower() {
        return activePower;
    }

    public void setActivePower(Double activePower) {
        this.activePower = activePower;
    }

    public Double getReActivePower() {
        return reActivePower;
    }

    public void setReActivePower(Double reActivePower) {
        this.reActivePower = reActivePower;
    }
}
