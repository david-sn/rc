/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.repository;

import com.pltfhs.car.entity.DaysOff;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface DaysOffRepository extends JpaRepository<DaysOff, Integer> {

    @Query(value = "SELECT * FROM days_off d  WHERE d.service_id=:serviceId AND :targetDate BETWEEN d.date_off_from AND d.date_off_to", nativeQuery = true)
    List<DaysOff> getDayIsOff(@Param("serviceId") Integer serviceId, @Param("targetDate") Date targetDate);

    @Query(value = "SELECT * FROM days_off WHERE date_off_from >= NOW() or date_off_to > NOW()", nativeQuery = true)
    List<DaysOff> getDaysOFF();
}
