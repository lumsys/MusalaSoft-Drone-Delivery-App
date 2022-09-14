package com.musala.codetest.repository;

import com.musala.codetest.model.Drone;
import com.musala.codetest.model.Enum.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DroneRepository extends JpaRepository<Drone, String> {

    List<Drone> findAllByState(@Param("drone_state") DroneState state); // using method

    @Query(value = "SELECT e from Drone e where e.serialNumber =:id ") // using @query with native
    Drone findBySerialNumber(@Param("id") String id);

    @Modifying
    @Query(value = "update Drone d set d.state = :state where  d.serialNumber= :serialno") //using query
    void setUpdateState (@Param("state") DroneState status, @Param("serialno") String serialno);
}
