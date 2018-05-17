package com.locadora.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicles")
@EntityListeners(AuditingEntityListener.class)
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String location;

    private Integer doors;

    @NotBlank
    private String model;

    private Integer year;

    @NotBlank
    private String category;

    private BigDecimal rentalValue;

    private String characteristics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return model;
    }

    public void setName(String name) {
        this.model = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getRentalValue() {
        return rentalValue;
    }

    public void setRentalValue(BigDecimal rentalValue) {
        this.rentalValue = rentalValue;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (getId() != null ? !getId().equals(vehicle.getId()) : vehicle.getId() != null) return false;
        if (getLocation() != null ? !getLocation().equals(vehicle.getLocation()) : vehicle.getLocation() != null)
            return false;
        if (getDoors() != null ? !getDoors().equals(vehicle.getDoors()) : vehicle.getDoors() != null) return false;
        if (getModel() != null ? !getModel().equals(vehicle.getModel()) : vehicle.getModel() != null) return false;
        if (getYear() != null ? !getYear().equals(vehicle.getYear()) : vehicle.getYear() != null) return false;
        if (getCategory() != null ? !getCategory().equals(vehicle.getCategory()) : vehicle.getCategory() != null)
            return false;
        if (getRentalValue() != null ? !getRentalValue().equals(vehicle.getRentalValue()) : vehicle.getRentalValue() != null)
            return false;
        return getCharacteristics() != null ? getCharacteristics().equals(vehicle.getCharacteristics()) : vehicle.getCharacteristics() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        result = 31 * result + (getDoors() != null ? getDoors().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getRentalValue() != null ? getRentalValue().hashCode() : 0);
        result = 31 * result + (getCharacteristics() != null ? getCharacteristics().hashCode() : 0);
        return result;
    }
}
