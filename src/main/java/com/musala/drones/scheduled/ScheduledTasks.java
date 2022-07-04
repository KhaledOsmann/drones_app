package com.musala.drones.scheduled;

import com.musala.drones.models.Drone;
import com.musala.drones.services.DroneService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private DroneService droneServie;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        List<Drone> drones = droneServie.getAvaliableDrones();
        log.info("The time is now {}", dateFormat.format(new Date()));

        for (Drone drone : drones) {
            log.info("Drone ID: " +drone.getId()+" Drone Battery Level: "+ drone.getBatteryCapacity(), dateFormat.format(new Date()));
        }
    }
}
