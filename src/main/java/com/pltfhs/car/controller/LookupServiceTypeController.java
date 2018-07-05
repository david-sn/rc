/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.LookupServiceType;
import com.pltfhs.car.service.LookupServiceTypeService;
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
public class LookupServiceTypeController {

    @Autowired
    LookupServiceTypeService lookupServiceTypeService;

    @RequestMapping("/addServiceType")
    public GeneralResponse<Item> addServiceType(@RequestBody LookupServiceType lookupServiceType) {
    	lookupServiceType.setIsAvailiable(true);
        return lookupServiceTypeService.addServiceType(lookupServiceType);
    }

    @RequestMapping("/updateServiceType")
    public GeneralResponse updateServiceType(@RequestBody LookupServiceType lookupServiceType) {
        return lookupServiceTypeService.updateServiceType(lookupServiceType);
    }

    @RequestMapping("/deleteServiceType")
    public GeneralResponse deleteServiceType(@RequestBody Item body) {
        return lookupServiceTypeService.deleteServiceType(body.getId().intValue());
    }

    @RequestMapping("/findAllService")
    public GeneralResponse findAllService(@RequestBody GeneralParameter body) {
        return lookupServiceTypeService.findAllService(body.getLanguage(), body.getPage(), body.getNoOfRows());
    }
    
    @RequestMapping("/findAllServiceAdmin")
    public GeneralResponse findAllServiceAdmin(@RequestBody GeneralParameter body) {
        return lookupServiceTypeService.findAllServiceAdmin(body.getLanguage(), body.getPage(), body.getNoOfRows());
    }

}
