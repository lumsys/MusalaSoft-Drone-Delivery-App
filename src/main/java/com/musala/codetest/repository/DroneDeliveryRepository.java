package com.musala.codetest.repository;

import com.musala.codetest.model.MedicineDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

        public interface DroneDeliveryRepository extends JpaRepository<MedicineDelivery, String> {
}
