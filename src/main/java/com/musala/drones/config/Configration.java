package com.musala.drones.config;

import com.musala.drones.dao.DronesDao;
import com.musala.drones.models.Drone;
import com.musala.drones.models.Medication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configration {

    @Bean
    public Drone drone() {
        return new Drone();
    }

    @Bean
    public Medication medication() {
        return new Medication();
    }

    @Bean
    public CommandLineRunner loadData(DronesDao dronesDao) {
        return (args) -> {
            dronesDao.save(new Drone("test1", "test1_model", 500, 0.5,  "test1"));
            dronesDao.save(new Drone("test2", "test2_model", 400, 0.25, "test2"));
            dronesDao.save(new Drone("test3", "test3_model", 400, 0.6,  "test3"));
            dronesDao.save(new Drone("test4", "test4_model", 300, 0.7,  "test4"));
            dronesDao.save(new Drone("test5", "test5_model", 200, 0.8,  "test5"));
        };
    }

}
