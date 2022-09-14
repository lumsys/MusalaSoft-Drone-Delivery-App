package com.musala.codetest.repository;

import com.musala.codetest.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicinePreLoadDataRepository extends JpaRepository<Medicine, String> {

}
