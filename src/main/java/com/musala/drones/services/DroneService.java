package com.musala.drones.services;

import com.musala.drones.dao.DronesDao;
import com.musala.drones.models.Drone;
import com.musala.drones.models.Medication;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {

    @Autowired
    public Drone drone;
    @Autowired
    public Medication medication;
    @Autowired
    public DronesDao droneDao;

    public DroneService(Drone droneModel) {
        this.drone = droneModel;
    }

    @Transactional
    public Drone registerDrone(Drone drone) {
        try {
            Drone registeredDrone = droneDao.save(new Drone(drone.getSerialNumber(), drone.getModel(), drone.getWeightLimit(), drone.getBatteryCapacity(), drone.getState()));
            return registeredDrone;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public Drone getDroneById(long id) {
        try {

            Drone drone = (Drone) droneDao.findById(id);
            return drone;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public Drone updateDrone(Drone drone, long id) {
        try {

            Drone existingDrone = (Drone) droneDao.findById(id);
            if (existingDrone != null) {
                Drone updatedDrone = new Drone();
                updatedDrone.setSerialNumber(drone.getSerialNumber());
                updatedDrone.setModel(drone.getModel());
                updatedDrone.setBatteryCapacity(drone.getBatteryCapacity());
                updatedDrone.setWeightLimit(drone.getWeightLimit());
                updatedDrone.setState(drone.getState());
                return droneDao.save(updatedDrone);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    @Transactional
    public List<Drone> getAvaliableDrones() {
        List<Drone> dronesList = new ArrayList<Drone>();
        List<Drone> avaliableDronesList = new ArrayList<Drone>();
        try {
            droneDao.findAll().forEach(dronesList::add);

            for (Drone drone : dronesList) {
                if (drone.getWeightLimit() <= 500 && drone.getBatteryCapacity() > 0.25) {
                    avaliableDronesList.add(drone);
                }
            }
            return avaliableDronesList;

        } catch (Exception e) {
            throw e;
        }
    }
}
