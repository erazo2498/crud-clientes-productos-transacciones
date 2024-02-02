package com.quind.pruebatecnica.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer {
    private Long id;
    private String identificationNumber;
    private String identificationType;
    private String name;
    private String surname;
    private String mail;
    private LocalDate birthday;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Customer(Long id, String identificationNumber, String identificationType, String name, String surname, String mail, LocalDate birthday, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.birthday = birthday;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
