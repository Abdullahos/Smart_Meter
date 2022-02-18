package com.root.meter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Entity
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "userId")
    @JsonIgnore //to avoid infinite recursion meter->user->meter->user->....
    private Users users;
    @OneToMany(mappedBy = "meter",fetch = FetchType.LAZY)  //meter must be the same spelling as the property in DailyReading class
    private List<DailyReading> dailyReadingList = new ArrayList<>();
    @OneToMany(mappedBy = "meter",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MonthlyConsumption> monthlyConsumptionList = new ArrayList<>();
    public Meter(Users user) {
        this.users = user;
    }

    public Meter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<DailyReading> getDailyReadingList() {
        return dailyReadingList;
    }

    public void setDailyReadingList(List<DailyReading> dailyReadingList) {
        this.dailyReadingList = dailyReadingList;
    }

    public void addToReadingList(DailyReading dailyReading) {
        this.dailyReadingList.add(dailyReading);
    }

    public List<MonthlyConsumption> getMonthlyConsumptionList() {
        return monthlyConsumptionList;
    }

    public void setMonthlyConsumptionList(List<MonthlyConsumption> monthlyConsumptionList) {
        this.monthlyConsumptionList = monthlyConsumptionList;
    }
}
