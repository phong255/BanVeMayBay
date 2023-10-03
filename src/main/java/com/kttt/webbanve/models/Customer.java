package com.kttt.webbanve.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "customer")

public class Customer {
    @Valid
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private int customerID;

    @NotBlank(message = "Full name is required!")
    @Column(name = "fullname")
    private String fullname;

    @Size(max = 3)
    @Column(name = "sex")
    private String sex;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Column(name = "birthday")
    private String birthday;

    @NotBlank(message = "Nationality is required!")
    @Column(name = "nationality")
    private String nationality;

    @NotBlank(message = "Citizen identification is required!")
    @Column(name = "citizen_identification")
    private  String citizenIdentification;

    @Column(name = "phone")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+@[a-zA-Z0-9-.]+$")
    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    public Customer(){}

    public String getSex() {
        return sex;
    }

    public String getNationality() {
        return nationality;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCitizenIdentification() {
        return citizenIdentification;
    }

    public String getFullname() {
        return fullname;
    }

    public User getUser() {
        return user;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCitizenIdentification(String citizenIdentification) {
        this.citizenIdentification = citizenIdentification;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
