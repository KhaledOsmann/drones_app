package com.musala.drones.controllers;

import com.musala.drones.models.Medication;
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
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @RequestMapping(value = "/registerMedication", method = RequestMethod.POST)
    public ResponseEntity<Medication> registerMedication(@RequestBody Medication medication) {
        try {
            String response = "";
            if (medication != null ) { 
                if (medication.getName() != null && medication.getName().matches("^[A-Za-z0-9_-]*$") && medication.getCode()!= null && medication.getCode().matches("^[A-Z0-9_]+(?:_[A-Z]+)*$")) {  
                    Medication registeredMedication = medicationService.registerMedication(medication);
                    return new ResponseEntity<>(registeredMedication, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.OK);
                }
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @RequestMapping(value = "/getMedicationsByDroneId/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Medication>> getMedicationsByDroneId(@PathVariable("id") long id) {
        try {
            List<Medication> medicationList = medicationService.getMedicationsByDroneId(id);
            return new ResponseEntity<>(medicationList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
