/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.CarModels;
import com.pltfhs.car.service.CarModelsService;
import com.pltfhs.car.view.CarModelTemplate;
import com.pltfhs.car.view.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
public class CarModelController {

    @Autowired
    private CarModelsService carModelsService;

    @RequestMapping("/add-car-model")
    public GeneralResponse<Item> addCarModel(@RequestBody GeneralParameter body) {
        return carModelsService.addCarModel(body.getCarModel(), body.getCarBrandId());
    }

    @RequestMapping("/update-car-model")
    public GeneralResponse<Item> updateCarModel(@RequestBody GeneralParameter body) {
        return carModelsService.updateCarModel(body.getCarModel(), body.getCarBrandId());
    }

    @RequestMapping("/deleteModel")
    public GeneralResponse<Item> deleteMode(@RequestBody Item body) {
        return carModelsService.deleteMode(body.getId());
    }

    @RequestMapping("/find-all-models")
    public GeneralResponse<List<CarModelTemplate>> findAllModels(@RequestBody GeneralParameter body) {
        return carModelsService.findAllModels(body.getPage(), body.getNoOfRows(), body.getTypeId(), body.getLanguage());
    }

    @RequestMapping("/find-by-model-id")
    public GeneralResponse<CarModels> findByModelId(@RequestBody Item body) {
        return carModelsService.findByModelId(body.getId());
    }

    @RequestMapping("/find-model-by-name")
    public GeneralResponse<List<CarModelTemplate>> findByModelName(@RequestBody GeneralParameter body) {
        return carModelsService.findByModelName(body.getValue(), body.getTypeId(), body.getLanguage());
    }

    @RequestMapping("/findModelByBrandId")
    public GeneralResponse findModelByBrandId(@RequestBody GeneralParameter body) {
    	
        return carModelsService.findModelByBrandId(body.getId().intValue(), body.getTypeId(), body.getLanguage());
    }
}
