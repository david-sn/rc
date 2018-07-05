/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.repository;

import com.pltfhs.car.entity.ServiceRequests;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Client 1
 */
public interface ServiceRequestsRepository extends JpaRepository<ServiceRequests, Integer> {

    List<ServiceRequests> findByTargetDateAndRequestStatusAndServiceId(Date targetDate, short requestStatus, Integer serviceId);
    
    long countByIsReadedIsFalse();
}
