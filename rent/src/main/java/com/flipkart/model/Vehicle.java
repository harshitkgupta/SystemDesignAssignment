package com.flipkart.model;

import java.util.Objects;

public abstract class Vehicle {
    private String type;
    private Integer id;
    private String regNo;

    public String getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", regNo='" + regNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return regNo.equals(vehicle.regNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo);
    }
}