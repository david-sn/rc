/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.UserCar;
import com.pltfhs.car.service.UserCarService;
import com.pltfhs.car.view.Item;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
public class UserCarController {

    @Autowired
    private UserCarService userCarService;

    @PostMapping("/addUserCar")
    public GeneralResponse addUserCar(Principal principal, @RequestBody GeneralParameter body) {
        return userCarService.addUserCar(principal.getName(), body.getCarBrandId().intValue(),
                body.getCarModelId().intValue(), body.getModelYear(), body.getChaseNumber(),
                body.getNameInLicence(), null, null,
                body.getCarNumber(), body.getNote());
    }//name in licience not icenseNumber
    //KiloMeter is changable value is require in requist maination service

    @PostMapping("/deleteUserCar")
    public GeneralResponse deleteUserCar(Principal principal, @RequestBody Item body) {
        return userCarService.deleteUserCar(principal.getName(), body.getId().intValue());
    }

    @PostMapping("/updateUserCar")
    public GeneralResponse updateUserCar(Principal principal, @RequestBody UserCar body) {
        return userCarService.updateUserCar(principal.getName(), body);
    }

    @PostMapping("/getUserCarById")
    public GeneralResponse getUserCarById(Principal principal, @RequestBody Item body) {
        return userCarService.getUserCarById(principal.getName(), body.getId().intValue(), body.getLanguage());
    }

    @PostMapping("/getAllUserCar")
    public GeneralResponse getAllUserCar(Principal principal, @RequestBody GeneralParameter body) {
        return userCarService.getAllUserCar(principal.getName(), body.getPage(), body.getNoOfRows(), body.getLanguage());
    }

}
