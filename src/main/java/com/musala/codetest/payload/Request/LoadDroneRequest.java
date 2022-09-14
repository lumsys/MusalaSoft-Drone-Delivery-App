package com.musala.codetest.payload.Request;

import com.sun.istack.NotNull;

public class LoadDroneRequest {



    public LoadDroneRequest(@NotNull String serialNumber, @NotNull String source,
                            @NotNull String destination, @NotNull String code) {
        super();
        this.serialNumber = serialNumber;
        this.source = source;
        this.destination = destination;
        this.code = code;
    }

    @NotNull
    private String serialNumber;

    @NotNull
    private String source;

    @NotNull
    private String destination;

    @NotNull
    private String code;



    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
