package com.musala.codetest.repository;

import com.musala.codetest.model.LoadMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoadDroneRepository extends JpaRepository<LoadMedicine, String> {

@Query(value = "SELECT * from table_drone_load e where e.fk_serial_no =:serialno ", nativeQuery = true) // using @query with
LoadMedicine findByDrone(@Param("serialno") String serialno);

@Query(value = "SELECT e from LoadMedicine e where e.medicine.code =:code ")
LoadMedicine findByCode(@Param("code") String code);
}
