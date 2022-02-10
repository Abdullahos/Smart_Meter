package com.root.meter.model;



import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Transactional
@Entity
/**
 * represent summation of readings per hour
 */
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meterId")
    private Meter meterId;
    private Date timeStamp;
    private Long volt;
    private Long current;
    private Long energy;    //kw
    private Long activePower;
    private Long reActivePower;
    private Double amount;   //cents per kW

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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
