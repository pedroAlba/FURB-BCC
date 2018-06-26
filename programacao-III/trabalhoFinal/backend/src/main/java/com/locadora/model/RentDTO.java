
package com.locadora.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class RentDTO {

    private Long vehicleId;

    private String userName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
