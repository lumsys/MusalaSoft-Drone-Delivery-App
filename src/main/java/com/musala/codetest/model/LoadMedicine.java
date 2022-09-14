package com.musala.codetest.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "table_drone_load")
public class LoadMedicine {

    public LoadMedicine() {

    }
    public LoadMedicine(Integer trackingId, String source, String destination, LocalDateTime createdon, Drone drone,
                          Medicine medicine) {
        super();
        this.trackingId = trackingId;
        this.source = source;
        this.destination = destination;
        this.createdon = createdon;
        this.drone = drone;
        this.medicine = medicine;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trackingid")
    private Integer trackingId;

    @Column(name = "source", columnDefinition = "VARCHAR(30) NOT NULL")
    private String source;

    @Column(name = "destination", columnDefinition = "VARCHAR(30) NOT NULL")
    private String destination;

    @Column(name = "createdon", columnDefinition = "VARCHAR(30) NOT NULL")
    private LocalDateTime createdon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_serial_no", referencedColumnName = "serial_no")
    private Drone drone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_code", referencedColumnName = "code", unique = true)
    private Medicine medicine;

    public Integer getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Integer trackingId) {
        this.trackingId = trackingId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getCreatedon() {
        return createdon;
    }

    public void setCreatedon(LocalDateTime createdon) {
        this.createdon = createdon;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
