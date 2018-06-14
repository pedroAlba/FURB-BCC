package com.locadora.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    private String imageURL;

    private String brand;

    @OneToMany
    private List<Rent> rents;

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

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getId(), vehicle.getId()) &&
                Objects.equals(getLocation(), vehicle.getLocation()) &&
                Objects.equals(getDoors(), vehicle.getDoors()) &&
                Objects.equals(getModel(), vehicle.getModel()) &&
                Objects.equals(getYear(), vehicle.getYear()) &&
                Objects.equals(getCategory(), vehicle.getCategory()) &&
                Objects.equals(getRentalValue(), vehicle.getRentalValue()) &&
                Objects.equals(getImageURL(), vehicle.getImageURL()) &&
                Objects.equals(getCharacteristics(), vehicle.getCharacteristics());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLocation(), getDoors(), getModel(), getYear(), getCategory(), getRentalValue(), getCharacteristics(), getImageURL());
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang.builder.ToStringBuilder(this)
                .append("id", id)
                .append("location", location)
                .append("doors", doors)
                .append("model", model)
                .append("year", year)
                .append("category", category)
                .append("rentalValue", rentalValue)
                .append("characteristics", characteristics)
                .append("imageURL", imageURL)
                .toString();
    }
}
