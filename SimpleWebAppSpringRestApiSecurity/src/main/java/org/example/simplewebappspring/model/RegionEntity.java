package org.example.simplewebappspring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "region")
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_region;

    @Column(name = "region_name")
    private String region_name;

    @OneToMany(mappedBy = "region")
    @JsonManagedReference
    private List<PollutionEntity> pollutions;

    // Геттеры и сеттеры

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public List<PollutionEntity> getPollutions() {
        return pollutions;
    }

    public void setPollutions(List<PollutionEntity> pollutions) {
        this.pollutions = pollutions;
    }
}
