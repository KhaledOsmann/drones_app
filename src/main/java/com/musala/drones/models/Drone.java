package com.musala.drones.models;

import javax.persistence.*;

@Entity
@Table(name = "Drone")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "Serial_Number")
    private String serialNumber;
    @Column(name = "Model")
    private String model;
    @Column(name = "Weight_Limit")
    private int weightLimit;
    @Column(name = "Battery_Capacity")
    private double batteryCapacity;
    @Column(name = "State")
    private String state;

    public Drone(String serialNumber, String model, int weightLimit, double batteryCapacity, String state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public Drone() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
