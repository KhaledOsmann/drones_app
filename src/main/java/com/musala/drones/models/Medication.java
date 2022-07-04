package com.musala.drones.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "weight")
    private int weight;
    @Column(name = "Code")
    private String code;
    @Column(name = "Image")
    private String image;
    @Column(name = "Dron_ID")
    private Long droneId;


    public Medication(String name, int weight, String code, String image, Long droneId) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
        this.droneId = droneId;
    }

    public Medication() {
 
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getDronId() {
        return droneId;
    }

    public void setDronId(Long dronId) {
        this.droneId = dronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
