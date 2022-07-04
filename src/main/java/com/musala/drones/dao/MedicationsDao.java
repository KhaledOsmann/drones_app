package com.musala.drones.dao;

import com.musala.drones.models.Medication;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationsDao extends JpaRepository<Medication, Long> {

    Medication findById(long id);

   @Query(value = "select * from Medication m where Medication.Dron_ID =:droneId", nativeQuery = true)
    List<Medication> getMedicationsByDroneId(@Param("droneId") Long droneId);

}
