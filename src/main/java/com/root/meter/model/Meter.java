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
    private User user;
    @OneToMany(mappedBy = "meter")  //meter must be the same spelling as the property in DailyReading class
    private List<DailyReading> dailyReadingList = new ArrayList<>();

    public Meter(User user) {
        this.user = user;
    }

    public Meter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
