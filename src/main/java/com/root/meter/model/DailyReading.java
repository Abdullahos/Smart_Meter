package com.root.meter.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Entity
/**
 * represent summation of readings per hour
 */
public class DailyReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "meterId")
    @JsonIgnore
    private Meter meter;
    private LocalDate date;
    private Double volt;
    private Double amber;
    private Double energy;    //kw
    private Double activePower;
    private Double reActivePower;
    private Double amount;   //cents per kW

    public DailyReading() {
    }

    public DailyReading(Meter meter, LocalDate date, Double volt, Double current, Double energy, Double amount) {
        this.meter = meter;
        this.date = date;
        this.volt = volt;
        this.amber = current;
        this.energy = energy;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
