/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.TestDirveRequest;
import com.pltfhs.car.service.TestDriveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class TestDriveRequestController {

    @Autowired
    private TestDriveRequestService testDriveRequestService;

    @RequestMapping("/addRequestTestDrive")
    public GeneralResponse<TestDirveRequest> addRequestTestDrive(@RequestBody TestDirveRequest body) {
        return testDriveRequestService.addRequestTestDrive(
                body.getClientName(),
                body.getClientEmail(),
                body.getClientPhone(),
                body.getNote()
        );
    }

    @RequestMapping("/editRequestTestDrive")
    public GeneralResponse<TestDirveRequest> editRequestTestDrive(@RequestBody TestDirveRequest body) {
        return testDriveRequestService.editRequestTestDrive(
                body.getTestDriveId(),
                body.getTestDriveDate(),
                body.getClientName(),
                body.getClientEmail(),
                body.getClientPhone(),
                body.getNote()
        );

    }

    @RequestMapping(value = "/deleteRequestTestDrive/{id}", method = RequestMethod.GET)
    public GeneralResponse deleteRequestTestDrive(@PathVariable long id) {
        return testDriveRequestService.deleteRequestTestDrive(id);
    }

    @RequestMapping(value = "/findRequestTestDriveById/{id}", method = RequestMethod.GET)
    public GeneralResponse findRequestTestDrive(@PathVariable long id) {
        return testDriveRequestService.findRequestTestDrive(id);
    }

    @RequestMapping("/listRequestTestDrive")
    public GeneralResponse listRequestTestDrive(@RequestBody GeneralParameter body) {
        return testDriveRequestService.listRequestTestDrive(
                body.getPage(),
                body.getNoOfRows()
        );
    }

}
