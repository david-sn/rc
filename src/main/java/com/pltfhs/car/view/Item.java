/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pltfhs.car.entity.CarBrands;
import com.pltfhs.car.entity.CarImage;
import com.pltfhs.car.entity.CarModels;
import com.pltfhs.car.entity.Comments;
import com.pltfhs.car.entity.LookupServiceType;
import com.pltfhs.car.entity.SystemInstallment;

/**
 *
 * @author Client 1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    private Byte language;
    private Long id;
    private String value;

    public Item() {
    }

    public Item(Comments comments) {
        this.id = comments.getCommentId();
        this.value = comments.getCommentText();
    }

    public Item(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Item(CarImage ci) {
        this.id = ci.getImageId().longValue();
        this.value = ci.getImagePath();

    }

    public Item(CarModels carModel, byte language) {
        this.id = carModel.getModelId();
        if (language == 1) {
            this.value = carModel.getModelNameAr();
        } else {
            this.value = carModel.getModelNameEn();
        }
    }

    public Item(CarBrands carBrands, byte language) {
        this.id = carBrands.getBrandId().longValue();
        if (language == 1) {
            this.value = carBrands.getBrandNameAr();
        } else {
            this.value = carBrands.getBrandNameEn();
        }
    }

    public Item(LookupServiceType serviceId, byte language) {
        this.id = serviceId.getServiceId().longValue();
        if (language == 1) {
            this.value = serviceId.getServiceTypeNameAr();
        } else {
            this.value = serviceId.getServiceTypeNameEn();
        }
    }

    public Item(SystemInstallment systemInstallment, byte language) {
        this.id = systemInstallment.getSystemInstallmentId().longValue();
        if (language == 1) {
            this.value = systemInstallment.getInstallmentGroupNameAr();
        } else {
            this.value = systemInstallment.getInstallmentGroupNameEn();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Byte getLanguage() {
        return language;
    }

    public void setLanguage(Byte language) {
        this.language = language;
    }
}
