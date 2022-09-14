package com.musala.codetest.JobScheduler;


import com.musala.codetest.model.Drone;
import com.musala.codetest.repository.DroneRepository;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ScheduleJobToCheckDroneBattery {

    @Autowired
    private DroneRepository droneRepository;

    static final Logger log = LoggerFactory.getLogger(ScheduleJobToCheckDroneBattery.class);

    @SneakyThrows
    @Scheduled(fixedRate = 5000)
    public void cronJobSch() {

        List<Drone> droneBatteryLevels = droneRepository.findAll();

        DecimalFormat decFormat = new DecimalFormat("#%");
        for (int i = 0; i < droneBatteryLevels.size(); i++) {

            log.info("Battery level--: SerialNumber  " + droneBatteryLevels.get(i).getSerialNumber() + "  " +
                    "Battery Level " + decFormat.format(droneBatteryLevels.get(i).getBattery()));
        }

        Thread.sleep(5000);
    }

}
