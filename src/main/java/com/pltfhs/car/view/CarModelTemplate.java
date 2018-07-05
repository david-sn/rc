/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.view;

import com.pltfhs.car.entity.CarModels;

/**
 *
 * @author Client 1
 */
public class CarModelTemplate {

    private Long modelId;
    private String modelImg;
    private String modelNameAr;
    private String modelNameEn;
    private Item carBrands;

    public CarModelTemplate(CarModels carModels, byte language) {
        this.modelId = carModels.getModelId();
        this.modelImg = carModels.getModelImg();
        this.modelNameAr = carModels.getModelNameAr();
        this.modelNameEn = carModels.getModelNameEn();
        this.carBrands = new Item(carModels.getCarBrands(), language);
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    } 

    public String getModelImg() {
        return modelImg;
    }

    public void setModelImg(String modelImg) {
        this.modelImg = modelImg;
    }

    public String getModelNameAr() {
        return modelNameAr;
    }

    public void setModelNameAr(String modelNameAr) {
        this.modelNameAr = modelNameAr;
    }

    public String getModelNameEn() {
        return modelNameEn;
    }

    public void setModelNameEn(String modelNameEn) {
        this.modelNameEn = modelNameEn;
    }

    public Item getCarBrands() {
        return carBrands;
    }

    public void setCarBrands(Item carBrands) {
        this.carBrands = carBrands;
    }

}
