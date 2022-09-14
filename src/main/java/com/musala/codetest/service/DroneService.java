package com.musala.codetest.service;

import com.musala.codetest.payload.Request.DroneDeliveryRequest;
import com.musala.codetest.payload.Request.DroneGetBatteryRequest;
import com.musala.codetest.payload.Request.DroneRegisterRequest;
import com.musala.codetest.payload.Request.LoadDroneRequest;
import com.musala.codetest.payload.Response.*;

public interface DroneService {

    RegisterDroneResponse register(DroneRegisterRequest drone);

    DroneBatteryDetailsResponse getBateryLevel(DroneGetBatteryRequest request) throws Exception;

    DroneMedicineLoadResponse getLoadedMedicineForADrone(String serialno);

    AvailableDroneResponse getAvailabeDrones();

    LoadDroneResponse loadDrone(LoadDroneRequest loadRequest);

    DeliverDroneResponse deliverLoad(DroneDeliveryRequest loadRequest);

    void preLoadData();


}
