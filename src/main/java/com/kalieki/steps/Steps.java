package com.kalieki.steps;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kalieki.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kalieki on 11/12/16.
 */

@Entity
public class Steps {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private int steps;

    @Column
    private Date logTime;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
