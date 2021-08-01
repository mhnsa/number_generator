package com.example.number_generator.model;


import java.util.Objects;

public class CarNumber {

    private Long id;
    private String fullNumber;
    private Boolean isIssued;

    public CarNumber(Long id, String fullNumber, Boolean isIssued) {
        this.id = id;
        this.fullNumber = fullNumber;
        this.isIssued = isIssued;
    }

    public CarNumber() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public void setFullNumber(String fullNumber) {
        this.fullNumber = fullNumber;
    }

    public Boolean getIssued() {
        return isIssued;
    }

    public void setIssued(Boolean issued) {
        isIssued = issued;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarNumber carNumber = (CarNumber) o;
        return id.equals(carNumber.id) && fullNumber.equals(carNumber.fullNumber) && isIssued.equals(carNumber.isIssued);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
