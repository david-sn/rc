/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.ComplaintForm;
import com.pltfhs.car.service.RequestService;
import com.pltfhs.car.view.DaysOffTemplate;
import com.pltfhs.car.view.Item;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping("/approveRequest")
    public GeneralResponse approveRequest(@RequestBody Item body) {
        return requestService.changeRequestStatus(body.getId().intValue(), (short) 1);
    }

    @RequestMapping("/disApproveRequest")
    public GeneralResponse disApproveRequest(@RequestBody Item body) {
        return requestService.changeRequestStatus(body.getId().intValue(), (short) 2);
    }

    @RequestMapping("/sendComplaintForm")
    public GeneralResponse sendComplaintForm(@RequestBody ComplaintForm body, Principal principal) {
        return requestService.sendComplaintForm(
                principal.getName(),
                body.getClientEmail(),
                body.getClientName(),
                body.getClientPhone(),
                body.getDescription(),
                body.getGroupId()
        );
    }

    @RequestMapping("/contactUs")
    public GeneralResponse contactUs(@RequestBody ComplaintForm body) {
        return requestService.contactUs(
                body.getClientEmail(),
                body.getClientName(),
                body.getClientPhone(),
                body.getDescription()
        );
    }

    @RequestMapping("/createMaintainceRequesteService")
    public GeneralResponse createMaintainceRequesteService(@RequestBody GeneralParameter body, Principal principal) {
        return requestService.createMaintainceRequesteService(
                principal.getName(),
                body.getLookupServiceTypeId(),
                body.getUserCarId(),// no date 
                body.getNote(),
                body.getKiloMeter()
        //no kilometers
        //user car id
        );
    }

    @RequestMapping("/findMaintainceRequesteById/{id}/{lan}")
    public GeneralResponse findMaintainceRequesteById(@PathVariable long id, @PathVariable byte language, Principal principal) {
        return requestService.findMaintainceRequesteById(
                id,
                language
        );
    }

    @RequestMapping("/findAllMaintainceRequeste")
    public GeneralResponse findAllMaintainceRequeste(@RequestBody GeneralParameter body, Principal principal) {
        return requestService.findAllMaintainceRequeste(
                body.getPage(),
                body.getNoOfRows(),
                body.getLanguage()
        );
    }

    @RequestMapping("/getDaysOff")
    public GeneralResponse<DaysOffTemplate> getDaysOff(@RequestBody Item body) {
        return requestService.getDaysOff(body.getLanguage());
    }

    @RequestMapping("/addDaysOff")
    public GeneralResponse<DaysOffTemplate> addDaysOff(@RequestBody GeneralParameter body) {
        return requestService.addDaysOff(
                body.getDaysOff(),
                body.getServiceId(),
                body.getLanguage()
        );
    }

    @RequestMapping("/deleteDaysOff")
    public GeneralResponse<Item> deleteDaysOff(@RequestBody Item body) {
        return requestService.deleteDaysOff(body.getId().intValue());
    }

    @RequestMapping("/updateDaysOff")
    public GeneralResponse<DaysOffTemplate> updateDaysOff(@RequestBody GeneralParameter body) {
        return requestService.updateDaysOff(
                body.getDaysOff(),
                body.getServiceId(),
                body.getLanguage()
        );
    }

}
