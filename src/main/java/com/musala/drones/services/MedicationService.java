package com.musala.drones.services;

import com.musala.drones.dao.MedicationsDao;
import com.musala.drones.models.Drone;
import com.musala.drones.models.Medication;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {

    @Autowired
    public Drone drone;
    @Autowired
    public Medication medication;
    @Autowired
    public MedicationsDao medicationsDao;

    public MedicationService() {
    }

    public MedicationService(Medication medication) {
        this.medication = medication;
    }

    @Transactional
    public Medication registerMedication(Medication medication) {
        try {
            Medication registerMedication = medicationsDao.save(new Medication(medication.getName(),medication.getWeight(),medication.getCode(), medication.getImage(),medication.getDronId()));
            return registerMedication;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public Medication loadMedicationOnDrons(Medication medication, Long dronId) {
        try {
            Medication medicationItem = (Medication) medicationsDao.findById(medication.getId());
            if (medicationItem != null) {
                medicationItem.setDronId(dronId);
                return medicationsDao.save(new Medication(medication.getName(),medication.getWeight(),medication.getCode(), medication.getImage(),medication.getDronId()));
            } else {
                medication.setDronId(dronId);
                return registerMedication(medication);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public List<Medication> getMedicationsByDroneId(Long droneId) {
        try {
            List<Medication> medicationItem = medicationsDao.getMedicationsByDroneId(droneId);
            return medicationItem;
        } catch (Exception e) {
            throw e;
        }
    }

}
