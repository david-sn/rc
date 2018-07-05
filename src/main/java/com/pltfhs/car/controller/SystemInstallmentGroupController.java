/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.SystemInstallment;
import com.pltfhs.car.service.SystemInstallmentGroupService;
import com.pltfhs.car.view.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
public class SystemInstallmentGroupController {

    @Autowired
    private SystemInstallmentGroupService systemInstallmentGroupService;

    @RequestMapping("/addSytemInsallmentGroup")
    public GeneralResponse addSytemInsallmentGroup(@RequestBody SystemInstallment installment) {
        return systemInstallmentGroupService.addSytemInsallmentGroup(installment);
    }

    @RequestMapping("/deleteSytemInsallmentGroup")
    public GeneralResponse deleteSytemInsallmentGroup(@RequestBody Item body) {
        return systemInstallmentGroupService.deleteSytemInsallmentGroup(body.getId().intValue());
    }

    @RequestMapping("/updateSytemInsallmentGroup")
    public GeneralResponse updateSytemInsallmentGroup(@RequestBody SystemInstallment installment) {
        return systemInstallmentGroupService.updateSytemInsallmentGroup(installment);
    }

    @RequestMapping("/findAllSytemInsallmentGroup")
    public GeneralResponse findAllSytemInsallmentGroup(@RequestBody GeneralParameter body) {
        return systemInstallmentGroupService.findAllSytemInsallmentGroup(body.getPage(), body.getNoOfRows(), body.getLanguage());
    }

}
