package com.musala.codetest.repository;

import com.musala.codetest.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MedicineRepository extends JpaRepository<Medicine, String> {

    @Query(value = "SELECT * from table_medicine e where e.code =:id ", nativeQuery = true) // using @query with
    Medicine findByCode(@Param("id") String id);

}
