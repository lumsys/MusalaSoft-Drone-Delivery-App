package com.musala.codetest.payload.Response;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
public class DroneBatteryDetailsResponse {

    public DroneBatteryDetailsResponse() {

    }
    public DroneBatteryDetailsResponse(String status, String serialNumber, String battery, LocalDateTime timestamp) {
        super();
        this.status = status;
        this.serialNumber = serialNumber;
        this.battery = battery;
        this.timestamp = timestamp;
    }
    private String status;
    private String serialNumber;
    private String battery;
    private LocalDateTime timestamp;



    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime localDateTime) {
        this.timestamp = localDateTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getBattery() {
        return battery;
    }
    public void setBattery(String battery) {
        this.battery = battery;
    }
}
