package com.locadora.model;

import com.sun.istack.internal.NotNull;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.DateTimeException;

@Entity
@Table(name = "rent")
@EntityListeners(AuditingEntityListener.class)
public class Rent {

    @Id
    @NotEmpty
    private Long id;

    @OneToOne
    @NotEmpty
    private Vehicle vehicle;

    @OneToOne
    @NotEmpty
    private User user;

    @NotEmpty
    private DateTime startDate;

    @NotEmpty
    private DateTime endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }
}
