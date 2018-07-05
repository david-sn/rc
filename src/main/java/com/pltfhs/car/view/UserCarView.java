package com.pltfhs.car.view;

import com.pltfhs.car.entity.CarBrands;
import com.pltfhs.car.entity.CarModels;
import com.pltfhs.car.entity.UserCar;
import java.util.Date;

public class UserCarView {

    private String carNumber;
    private static final long serialVersionUID = 1L;
    private Integer userCarId;
    private Integer carBrandId;
    private Integer carModelId;
    private String modelYear;
    private String chaseNumber;
    private String nameInLicenseNumber;
    private String engineCapacity;
    private String kiloMeter;
    private String note;
    private String brandName;
    private String modelName;
    private Integer userId;
    private Date creationDate;
    private Boolean isDeleted;

    public UserCarView() {
    }

    public UserCarView(UserCar car, CarBrands brnd, CarModels mdl, Byte language) {
        super();
        this.carNumber = car.getCarNumber();
        this.userCarId = car.getUserCarId();
        this.carBrandId = car.getCarBrandId();
        this.carModelId = car.getCarModelId();
        this.chaseNumber = car.getChaseNumber();
        this.nameInLicenseNumber = car.getNameInLicenseNumber();
        this.kiloMeter = car.getKiloMeter();
        this.note = car.getNote();
        if (language == 1) {
            this.brandName = brnd.getBrandNameEn();
            this.modelName = mdl.getModelNameEn();
        } else {
            this.brandName = brnd.getBrandNameAr();
            this.modelName = mdl.getModelNameAr();
        }
        this.userId = car.getUserId();
        this.creationDate = car.getCreationDate();
        this.isDeleted = car.getIsDeleted();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getUserCarId() {
        return userCarId;
    }

    public void setUserCarId(Integer userCarId) {
        this.userCarId = userCarId;
    }

    public Integer getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Integer carBrandId) {
        this.carBrandId = carBrandId;
    }

    public Integer getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Integer carModelId) {
        this.carModelId = carModelId;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getChaseNumber() {
        return chaseNumber;
    }

    public void setChaseNumber(String chaseNumber) {
        this.chaseNumber = chaseNumber;
    }

    public String getNameInLicenseNumber() {
        return nameInLicenseNumber;
    }

    public void setNameInLicenseNumber(String nameInLicenseNumber) {
        this.nameInLicenseNumber = nameInLicenseNumber;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getKiloMeter() {
        return kiloMeter;
    }

    public void setKiloMeter(String kiloMeter) {
        this.kiloMeter = kiloMeter;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
