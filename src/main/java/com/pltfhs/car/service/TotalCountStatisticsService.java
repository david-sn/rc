/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import com.pltfhs.car.repository.ServiceRequestsRepository;
import com.pltfhs.car.repository.TestDriveRequestRepository;
import com.pltfhs.car.repository.UsersRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Client 1
 */
@Service
public class TotalCountStatisticsService {

    @Autowired
    private TestDriveRequestRepository testDriveRequestRepository;
    @Autowired
    private ServiceRequestsRepository serviceRequestsRepository;
    @Autowired
    private UsersRepository usersRepository;

    private long totalTestDriveRequests() {
        return testDriveRequestRepository.countByIsReadedIsFalse();
    }

    private long totalMaintainceRequests() {
        return serviceRequestsRepository.countByIsReadedIsFalse();
    }

    public Map<String, Long> getAllCount() {
        Map<String, Long> data = new HashMap<>();
        data.put("totalTestDriveRequests", totalTestDriveRequests());
        data.put("totalMaintainceRequests", totalMaintainceRequests());
        data.put("totalCustomers", usersRepository.countBySystemRolesSet_RoleId((short) 1));
        return data;
    }
}
