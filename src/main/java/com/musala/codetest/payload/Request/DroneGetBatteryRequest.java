package com.musala.codetest.payload.Request;

import com.sun.istack.NotNull;

public class DroneGetBatteryRequest {

    public DroneGetBatteryRequest() {

    }

    public DroneGetBatteryRequest(@NotNull String serialNumber) {
        super();
        this.serialNumber = serialNumber;
    }

    @NotNull
    private String serialNumber;



    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
