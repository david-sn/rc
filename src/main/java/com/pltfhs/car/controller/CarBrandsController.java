package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.CarBrands;
import com.pltfhs.car.service.BrandService;
import com.pltfhs.car.view.ImagesPath;
import com.pltfhs.car.view.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
public class CarBrandsController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/addBrand")
    public GeneralResponse<Integer> addBrand(@RequestBody CarBrands carRequestBody) {
        return brandService.addNewCarBrand(carRequestBody);

    }

    @RequestMapping("/get-all-brands")
    public GeneralResponse<List<CarBrands>> getAllBrands(@RequestBody GeneralParameter body) {
        return brandService.findAllBrands(body.getTypeId(), body.getPage(), body.getNoOfRows());
    }
    
    @RequestMapping("/get-all-service-brands")
    public GeneralResponse<List<CarBrands>> getAllServiceBrands(@RequestBody GeneralParameter body) {
        return brandService.findAllBrands(body.getTypeId(), body.getPage(), body.getNoOfRows());
    }

    @RequestMapping("/delete-image-brand")
    public GeneralResponse<String> deleteImage(@RequestBody ImagesPath imagesPath) {
        return brandService.deleteImage(imagesPath);
    }

    @RequestMapping("/find-by-brand-name")
    public GeneralResponse<List<CarBrands>> findByBrandName(@RequestBody GeneralParameter body) {
        return brandService.findByBrandName(body.getTypeId(), body.getValue());
    }

    @RequestMapping("/find-by-brand-id")
    public GeneralResponse<CarBrands> findByBrandId(@RequestBody Item item) {
        return brandService.findByBrandId(item.getId().intValue());
    }

    @RequestMapping("/deleteBrand")
    public GeneralResponse deleteBrand(@RequestBody Item item) {
        return brandService.deleteBrand(item.getId().intValue());
    }

    @RequestMapping("/updateBrand")
    public GeneralResponse updateBrand(@RequestBody CarBrands carRequestBody) {
        return brandService.updateBrand(carRequestBody);
    }
}
