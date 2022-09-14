package com.musala.codetest.payload.Request;

import com.sun.istack.NotNull;

public class DroneDeliveryRequest {

    @NotNull
    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
