package org.example.simplewebappspring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "pollution")
public class PollutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pollution;

    @Column(name = "pollution_level")
    private String pollution_level;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonBackReference
    private RegionEntity region;

    // Геттеры и сеттеры

    public int getId_pollution() {
        return id_pollution;
    }

    public void setId_pollution(int id_pollution) {
        this.id_pollution = id_pollution;
    }

    public String getPollution_level() {
        return pollution_level;
    }

    public void setPollution_level(String pollution_level) {
        this.pollution_level = pollution_level;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }
}
