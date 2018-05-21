package com.locadora.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    
    private String email;

    @NotBlank
    private String password;

    
    private String address;

    
    private String phone;

    @OneToMany
    private List<Rent> rents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof User)) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(getId(), user.getId())
                .append(getUsername(), user.getUsername())
                .append(getName(), user.getName())
                .append(getEmail(), user.getEmail())
                .append(getPassword(), user.getPassword())
                .append(getAddress(), user.getAddress())
                .append(getPhone(), user.getPhone())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getUsername())
                .append(getName())
                .append(getEmail())
                .append(getPassword())
                .append(getAddress())
                .append(getPhone())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("name", name)
                .append("email", email)
                .append("password", password)
                .append("address", address)
                .append("phone", phone)
                .toString();
    }
}
