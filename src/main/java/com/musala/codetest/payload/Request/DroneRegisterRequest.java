package com.musala.codetest.payload.Request;

import com.musala.codetest.model.Enum.DroneModel;
import com.musala.codetest.model.Enum.DroneState;
import com.sun.istack.NotNull;

import java.math.BigDecimal;

public class DroneRegisterRequest {

    public DroneRegisterRequest() {

    }

    public DroneRegisterRequest(String serialNumber, DroneModel model, double weightLimit, BigDecimal battery,
                                DroneState state) {
        super();
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.battery = battery;
        this.state = state;
    }

    @NotNull
    private String serialNumber;

    @NotNull
    private DroneModel model;

    @NotNull
    private double weightLimit;

    @NotNull
    private BigDecimal battery;

    @NotNull
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

    public BigDecimal getBattery() {
        return battery;
    }

    public void setBattery(BigDecimal battery) {
        this.battery = battery;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }
}
