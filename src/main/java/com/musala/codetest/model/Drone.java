package com.musala.codetest.model;


import com.musala.codetest.model.Enum.DroneModel;
import com.musala.codetest.model.Enum.DroneState;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "table_drone")
public class Drone {

    public Drone() {

    }

    public Drone(String serialNumber, DroneModel model, double weightLimit, BigDecimal battery, DroneState state) {
        super();
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.battery = battery;
        this.state = state;
    }

    @Id
    @Column(name = "serial_no", columnDefinition = "VARCHAR(16) NOT NULL")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private DroneModel model;


    @Column(name = "weight_limit", columnDefinition = "VARCHAR(10) NOT NULL")
    private double weightLimit;

    @Column(name = "battery",precision = 3, scale = 2)
    private BigDecimal battery;

    @Enumerated(EnumType.STRING)
    @Column(name = "drone_state")
    private DroneState state;



    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }


    public BigDecimal getBattery() {
        return battery;
    }

    public void setBattery(BigDecimal battery) {
        this.battery = battery;
    }
}

