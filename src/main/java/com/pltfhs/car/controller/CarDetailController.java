/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.service.CarDetailsService;
import com.pltfhs.car.view.CarDetailTemplate;
import com.pltfhs.car.view.Item;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Client 1
 */
@RestController
@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
public class CarDetailController {

    @Autowired
    private CarDetailsService carDetailsService;

    @RequestMapping(value = "/add-car", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneralResponse<Item> addCar(@RequestParam("carNameAr") String carNameAr,
            @RequestParam("carNameEn") String carNameEn, @RequestParam("curbWeight") String curbWeight,
            @RequestParam("fuelTankCapacity") String fuelTankCapacity, @RequestParam("grossVehicleWeight") String grossVehicleWeight,
            @RequestParam("groundClearance") String groundClearance, @RequestParam("height") String height,
            @RequestParam("length") String length, @RequestParam("width") String width,
            @RequestBody MultipartFile mainPic, @RequestParam("maxPower") String maxPower,
            @RequestParam("maxTorque") String maxTorque, @RequestParam("transmission") String transmission,
            @RequestParam("trunkCapacity") String trunkCapacity, @RequestParam("engineCapacity") String engineCapacity, @RequestParam("carModelId") Long carModelId,
            @RequestParam(value = "carPrice", required = false) String price, @RequestBody MultipartFile internalImage1,
            @RequestBody MultipartFile internalImage2, @RequestBody MultipartFile internalImage3,
            @RequestBody MultipartFile externalImage1, @RequestBody MultipartFile externalImage2,
            @RequestBody MultipartFile externalImage3,
            @RequestBody(required = false) MultipartFile carCatalog
    ) throws IOException {

        return carDetailsService.addCar(carNameAr, carNameEn, curbWeight, fuelTankCapacity, grossVehicleWeight,
                groundClearance, height, width, mainPic, maxPower, maxTorque, transmission, trunkCapacity, carModelId,
                price, internalImage1, internalImage2, internalImage3, externalImage1, externalImage2, externalImage3, engineCapacity, length, carCatalog);

    }

    @RequestMapping("/find-all-car")
    public GeneralResponse<List<CarDetailTemplate>> findAllCars(@RequestBody GeneralParameter body) {
        return carDetailsService.findAllCars(body.getPage(), body.getNoOfRows(), body.getLanguage());
    }

    @RequestMapping(value = "/find-by-car-id/{id}/{language}", method = RequestMethod.GET)
    public GeneralResponse<CarDetailTemplate> findBycarId(@PathVariable("id") long id, @PathVariable byte language, @RequestHeader(required = false, value = "Authorization") String token) {
        return carDetailsService.findBycarId(id, token, language);
    }

    @RequestMapping(value = "/update-car", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GeneralResponse updateCar(
            @RequestParam("carId") long carId,
            @RequestParam("carNameAr") String carNameAr,
            @RequestParam("carNameEn") String carNameEn, @RequestParam("curbWeight") String curbWeight,
            @RequestParam("fuelTankCapacity") String fuelTankCapacity, @RequestParam("grossVehicleWeight") String grossVehicleWeight,
            @RequestParam("groundClearance") String groundClearance, @RequestParam("height") String height,
            @RequestParam("length") String length, @RequestParam("width") String width,
            @RequestBody MultipartFile mainPic, @RequestParam("maxPower") String maxPower,
            @RequestParam("maxTorque") String maxTorque, @RequestParam("transmission") String transmission,
            @RequestParam("trunkCapacity") String trunkCapacity, @RequestParam("engineCapacity") String engineCapacity, @RequestParam("carModelId") Long carModelId,
            @RequestParam(value = "carPrice", required = false) String price, @RequestBody MultipartFile internalImage1,
            @RequestBody MultipartFile internalImage2, @RequestBody MultipartFile internalImage3,
            @RequestBody MultipartFile externalImage1, @RequestBody MultipartFile externalImage2,
            @RequestBody MultipartFile externalImage3,
            @RequestBody(required = false) MultipartFile carCatalog) {

        return carDetailsService.updateCar(carId, carNameAr, carNameEn, curbWeight,
                fuelTankCapacity, grossVehicleWeight, groundClearance, height, width,
                mainPic, maxPower, maxTorque, transmission, trunkCapacity, carModelId,
                price, internalImage1, internalImage2, internalImage3, externalImage1,
                externalImage2, externalImage3, engineCapacity, length, carCatalog);
    }

    @RequestMapping("/delete-car")
    public GeneralResponse deleteCar(@RequestBody Item body) {
        return carDetailsService.deleteCar(body.getId());
    }

    @RequestMapping("/find-cars-by-model")
    public GeneralResponse<List<CarDetailTemplate>> findByModel(@RequestBody GeneralParameter body) {
        return carDetailsService.findByModel(body.getCarModelId(), body.getPage(), body.getNoOfRows(), body.getLanguage());
    }

}
