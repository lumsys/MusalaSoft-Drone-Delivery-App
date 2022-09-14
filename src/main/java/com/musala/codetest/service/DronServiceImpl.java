package com.musala.codetest.service;

import com.musala.codetest.model.Drone;
import com.musala.codetest.model.Enum.DroneState;
import com.musala.codetest.model.LoadMedicine;
import com.musala.codetest.model.Medicine;
import com.musala.codetest.model.MedicineDelivery;
import com.musala.codetest.payload.Request.DroneDeliveryRequest;
import com.musala.codetest.payload.Request.DroneGetBatteryRequest;
import com.musala.codetest.payload.Request.DroneRegisterRequest;
import com.musala.codetest.payload.Request.LoadDroneRequest;
import com.musala.codetest.payload.Response.*;
import com.musala.codetest.repository.DroneDeliveryRepository;
import com.musala.codetest.repository.DroneRepository;
import com.musala.codetest.repository.MedicineRepository;
import com.musala.codetest.repository.LoadDroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class DronServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private LoadDroneRepository loadDroneRepository;
    @Autowired
    private DroneDeliveryRepository droneDeliveryRepository;

    @Override
    public RegisterDroneResponse register(DroneRegisterRequest droneRequest) {
        Drone newdrone = new Drone();
        newdrone.setSerialNumber(droneRequest.getSerialNumber());
        newdrone.setModel(droneRequest.getModel());
        newdrone.setWeightLimit(droneRequest.getWeightLimit());
        newdrone.setBattery(droneRequest.getBattery());
        newdrone.setState(droneRequest.getState());
        droneRepository.save(newdrone);


        RegisterDroneResponse droneResponse = new RegisterDroneResponse();
        droneResponse.setResult("success");
        droneResponse.setSerialNumber(newdrone.getSerialNumber());
        droneResponse.setMessage("New Drone created successfully");
        droneResponse.setTimestamp(java.time.LocalDateTime.now());

        return droneResponse;
    }

    @Override
    public AvailableDroneResponse getAvailabeDrones() {
        DroneState state = DroneState.IDLE;
        List<Drone> drones = droneRepository.findAllByState(state);
        return new AvailableDroneResponse("status", java.time.LocalDateTime.now(), drones);
    }


    @Override
    public DroneBatteryDetailsResponse getBateryLevel(DroneGetBatteryRequest request) {
        Drone newdrone = new Drone();
        DecimalFormat decFormat = new DecimalFormat("#%");
        DroneBatteryDetailsResponse batteryDetailsResponse = new DroneBatteryDetailsResponse();
        newdrone.setSerialNumber(request.getSerialNumber());
        Drone droneBattery = droneRepository.findBySerialNumber(newdrone.getSerialNumber());
        if (droneBattery == null) {
            throw new RuntimeException("Drone battery level details not found");
        }

        batteryDetailsResponse.setStatus("success");
        batteryDetailsResponse.setSerialNumber(droneBattery.getSerialNumber());
        batteryDetailsResponse.setBattery(decFormat.format(droneBattery.getBattery()));
        batteryDetailsResponse.setTimestamp(java.time.LocalDateTime.now());

        return batteryDetailsResponse;
    }



    @Override
    public DroneMedicineLoadResponse getLoadedMedicineForADrone(String serialno) {
        LoadMedicine loadMedicine = loadDroneRepository.findByDrone(serialno);
        if(loadMedicine==null) {
            throw new RuntimeException("No load Medicine details for the specified drone");
        }
        DroneMedicineLoadResponse droneLoad = new DroneMedicineLoadResponse();
        droneLoad.setResult("success");
        droneLoad.setSerialNumber(loadMedicine.getDrone().getSerialNumber());
        droneLoad.setTimestamp(java.time.LocalDateTime.now());
        droneLoad.setMedicine(loadMedicine.getMedicine());

        return droneLoad;
    }

    @Override
    public LoadDroneResponse loadDrone(LoadDroneRequest loadRequest) {
        //preload data
        Medicine medicine1 = new Medicine("OGE123455","Pelicilin",250,"Lumzyxt223");
        Medicine medicine2 = new Medicine("OGE123456","Amocilin",100,"luMxt3432");
        Medicine medicine3 = new Medicine("OGE123457","Becombium",150,"Luzys848b");
        Medicine medicine4 = new Medicine("OGE123458","Babyrex",200,"LUMxt3839");
        Medicine medicine5 = new Medicine("OGE123459","Paracetamol",300,"Lumgte398");

        medicineRepository.save(medicine1);
        medicineRepository.save(medicine2);
        medicineRepository.save(medicine3);
        medicineRepository.save(medicine4);
        medicineRepository.save(medicine5);


        droneRepository.setUpdateState(DroneState.LOADING, loadRequest.getSerialNumber());
        Drone drone = droneRepository.findBySerialNumber(loadRequest.getSerialNumber());
        Medicine medicine = medicineRepository.findByCode(loadRequest.getCode());
        LoadMedicine checkLoad = loadDroneRepository.findByCode(loadRequest.getCode());

        if(checkLoad != null) {
            throw new RuntimeException("Medicine code has already been loaded, try another one");

        }

        // validate before loading
        if (drone == null) {
            throw new RuntimeException("Drone specified does not exist");
        }

        if (medicine == null) {
            throw new RuntimeException("Medicine specified does not exist");
        }

        if (drone.getWeightLimit() < medicine.getWeight()) {
            throw new RuntimeException("The Drone cannot load more than the weight limit");
        }
        // check battery
        if( drone.getBattery().compareTo(new BigDecimal(0.25)) < 0  ){
            throw new RuntimeException("The Drone cannot be loaded, battery below 25%");
        }
        // load
        LoadMedicine loadMedicine = new LoadMedicine();
        loadMedicine.setDrone(drone);
        loadMedicine.setMedicine(medicine);
        loadMedicine.setSource(loadRequest.getSource());
        loadMedicine.setDestination(loadRequest.getDestination());
        loadMedicine.setCreatedon(java.time.LocalDateTime.now());
        loadDroneRepository.save(loadMedicine);
        droneRepository.setUpdateState(DroneState.LOADED, loadRequest.getSerialNumber());

        LoadDroneResponse loadDroneResponse = new LoadDroneResponse();
        loadDroneResponse.setResult("success");
        loadDroneResponse.setSerialNumber(loadRequest.getSerialNumber());
        loadDroneResponse.setMessage("Drone Loaded successfully");
        loadDroneResponse.setTimestamp(java.time.LocalDateTime.now());

        return loadDroneResponse;
    }

    @Override
    public DeliverDroneResponse deliverLoad(DroneDeliveryRequest loadRequest) {
        droneRepository.setUpdateState(DroneState.DELIVERING, loadRequest.getSerialNumber());
        LoadMedicine loadMedicine = loadDroneRepository.findByDrone(loadRequest.getSerialNumber());

        if(loadMedicine==null) {
            throw new RuntimeException("Drone specifies is not loaded or does not exist");
        }

        MedicineDelivery newdelivery = new MedicineDelivery();
        newdelivery.setLoadMedicine(loadMedicine);
        newdelivery.setDeliveryTime(java.time.LocalDateTime.now());
        droneDeliveryRepository.save(newdelivery);
        droneRepository.setUpdateState(DroneState.DELIVERED, loadRequest.getSerialNumber());

        DeliverDroneResponse deliverDroneResponse = new DeliverDroneResponse();
        deliverDroneResponse.setResult("success");
        deliverDroneResponse.setSerialNumber(loadRequest.getSerialNumber());
        deliverDroneResponse.setMessage("Delivered successfully");
        deliverDroneResponse.setTimestamp(java.time.LocalDateTime.now());

        return deliverDroneResponse;
    }

    @Override
    public void preLoadData() {

        Medicine medicine1 = new Medicine("OGE123455","Pelicilin",250,"Lumzyxt223");
        Medicine medicine2 = new Medicine("OGE123456","Amocilin",100,"luMxt3432");
        Medicine medicine3 = new Medicine("OGE123457","Becombium",150,"Luzys848b");
        Medicine medicine4 = new Medicine("OGE123458","Babyrex",200,"LUMxt3839");
        Medicine medicine5 = new Medicine("OGE123459","Paracetamol",300,"Lumgte398");

        medicineRepository.save(medicine1);
        medicineRepository.save(medicine2);
        medicineRepository.save(medicine3);
        medicineRepository.save(medicine4);
        medicineRepository.save(medicine5);

    }






}
