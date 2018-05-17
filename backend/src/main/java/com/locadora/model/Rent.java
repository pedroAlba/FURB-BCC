package com.locadora.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "rent")
@EntityListeners(AuditingEntityListener.class)
public class Rent {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Vehicle vehicle;

    @OneToOne
    private User user;

    @Basic
    private LocalDate startDate;

    @Basic
    private LocalDate endDate;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rent)) return false;
        Rent rent = (Rent) o;
        return Objects.equals(getId(), rent.getId()) &&
                Objects.equals(getVehicle(), rent.getVehicle()) &&
                Objects.equals(getUser(), rent.getUser()) &&
                Objects.equals(getStartDate(), rent.getStartDate()) &&
                Objects.equals(getEndDate(), rent.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVehicle(), getUser(), getStartDate(), getEndDate());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("vehicle", vehicle)
                .append("user", user)
                .append("startDate", startDate)
                .append("endDate", endDate)
                .toString();
    }
}
