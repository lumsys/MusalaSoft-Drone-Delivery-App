package com.musala.codetest.payload.Response;

import com.musala.codetest.model.Medicine;

import java.time.LocalDateTime;

public class DroneMedicineLoadResponse {

    private String result;
    private String serialNumber;
    private LocalDateTime timestamp;
    Medicine medicine;

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public Medicine getMedicine() {
        return medicine;
    }
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }


}
