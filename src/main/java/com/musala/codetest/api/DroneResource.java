package com.musala.codetest.api;


import com.musala.codetest.payload.Request.DroneDeliveryRequest;
import com.musala.codetest.payload.Request.DroneGetBatteryRequest;
import com.musala.codetest.payload.Request.DroneRegisterRequest;
import com.musala.codetest.payload.Request.LoadDroneRequest;
import com.musala.codetest.payload.Response.*;
import com.musala.codetest.service.DronServiceImpl;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/api/drone")
@Validated
public class DroneResource {
    @Autowired
    private DronServiceImpl droneService;

    @PostMapping(path="/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegisterDroneResponse> registerDrone(
            @NotNull @RequestBody DroneRegisterRequest dronerequest) {
        RegisterDroneResponse newDrone = droneService.register(dronerequest);
        return new ResponseEntity<RegisterDroneResponse>(newDrone, HttpStatus.CREATED);
    }

    @GetMapping(path= "/available", produces = "application/json")
    public ResponseEntity<AvailableDroneResponse> getAvailableDroneForLoading() {
        AvailableDroneResponse drones = droneService.getAvailabeDrones();
        return new ResponseEntity<AvailableDroneResponse>(drones, HttpStatus.OK);
    }

    @PostMapping(path ="/battery", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DroneBatteryDetailsResponse> checkDroneBattery(
            @NotNull @RequestBody(required = true) DroneGetBatteryRequest request) {
        if (request.getSerialNumber() == null || request.getSerialNumber().isEmpty()) {
            throw new RuntimeException("SerialNumber is Required");
        }
        DroneBatteryDetailsResponse droneBattery = droneService.getBateryLevel(request);
        return new ResponseEntity<DroneBatteryDetailsResponse>(droneBattery, HttpStatus.CREATED);
    }

    @PostMapping(path = "/load", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoadDroneResponse> loadDroneWithMedicine(@Validated @NotNull @RequestBody LoadDroneRequest loadrequest) {
        LoadDroneResponse loadDrone = droneService.loadDrone(loadrequest);
        return new ResponseEntity<LoadDroneResponse>(loadDrone, HttpStatus.CREATED);
    }

    @GetMapping(path = "details/{serialNumber}", produces = "application/json")
    public ResponseEntity<DroneMedicineLoadResponse> checkLoadedMedicineItem(@PathVariable("serialNumber") String serialNumber) {
        DroneMedicineLoadResponse droneLoads = droneService.getLoadedMedicineForADrone(serialNumber);
        return new ResponseEntity<DroneMedicineLoadResponse>(droneLoads, HttpStatus.OK);
    }

    @PostMapping(path = "/deliver", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DeliverDroneResponse> droneMedicineLoadDelivery(@NotNull @RequestBody DroneDeliveryRequest deliverRequest) {
        DeliverDroneResponse delivery = droneService.deliverLoad(deliverRequest);
        return new ResponseEntity<DeliverDroneResponse>(delivery, HttpStatus.CREATED);
    }
}
