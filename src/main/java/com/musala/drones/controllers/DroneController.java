package com.musala.drones.controllers;

import com.musala.drones.models.Drone;
import com.musala.drones.models.Medication;
import com.musala.drones.services.DroneService;
import com.musala.drones.services.MedicationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class DroneController {

    @Autowired
    private DroneService droneServie;

    @Autowired
    private MedicationService medicationService;

    @RequestMapping(value = "/registerDrone", method = RequestMethod.POST)
    public ResponseEntity<String> registerDrone(@RequestBody Drone drone) {
        try {
            String response = "";
            if (drone.getSerialNumber() != null && drone.getWeightLimit() >= 500) {
                if (drone.getSerialNumber().length() <= 100) {
                    Drone registeredDrone = droneServie.registerDrone(drone);
                    return new ResponseEntity<>("Registered Drone Successfuly!!", HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>("Failed to Register Drone, Please Check Drone Serial Number Lenght!", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Failed to Register Drone, Please Check Drone weight!", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Register Drone!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/updateDrone/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateDrone(@PathVariable("id") long id, @RequestBody Drone drone) {
        Drone updatedDrone = droneServie.updateDrone(drone, id);
        if (updatedDrone != null) {
            return new ResponseEntity<>("Updated Drone Successfuly", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to Update Drone!",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getDroneById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Drone> getDroneById(@PathVariable("id") long id) {
        Drone dronData = droneServie.getDroneById(id);
        if (dronData != null) {
            return new ResponseEntity<>(dronData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getAvaliableDrones/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Drone>> getAvaliableDrones() {
        try {
            List<Drone> drones = droneServie.getAvaliableDrones();
            if (drones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(drones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getbatteryDrone/{id}", method = RequestMethod.GET)
    public double getbatteryDrone(@PathVariable("id") long id) {
        try {
            Drone drone = droneServie.getDroneById(id);
            return drone.getBatteryCapacity();
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/loadDrone/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Drone> loadDrone(@PathVariable("id") long id, @RequestBody List<Medication> medicationItems) {
        Drone drone = droneServie.getDroneById(id);
        if (drone != null) {
            for (Medication medication : medicationItems) {
                if (drone.getWeightLimit() >= medication.getWeight()) {
                    Medication updatedMedication = medicationService.loadMedicationOnDrons(medication, id);
                    int newDronWegith = drone.getWeightLimit() - updatedMedication.getWeight();
                    drone.setWeightLimit(newDronWegith);
                    droneServie.updateDrone(drone, id);
                }
            }
            return new ResponseEntity<>(drone, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
