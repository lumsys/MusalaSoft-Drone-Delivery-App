package com.musala.codetest.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "table_medicine_delivery")
public class MedicineDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "delivery_time", columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime deliveryTime;

    @OneToOne(targetEntity = LoadMedicine.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_trackingid", referencedColumnName = "trackingid")
    private LoadMedicine loadMedicine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public LoadMedicine getLoadMedicine() {
        return loadMedicine;
    }

    public void setLoadMedicine(LoadMedicine loadMedicine) {
        this.loadMedicine = loadMedicine;
    }
}
