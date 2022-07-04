package com.musala.drones.dao;

import com.musala.drones.models.Drone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DronesDao extends JpaRepository<Drone, Long> {

    Drone findById(long id);

}
