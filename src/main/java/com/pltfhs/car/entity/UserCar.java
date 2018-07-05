/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "user_car")
@NamedQueries({
    @NamedQuery(name = "UserCar.findAll", query = "SELECT u FROM UserCar u")})
public class UserCar implements Serializable {

    @Size(max = 255)
    @Column(name = "car_number")
    private String carNumber;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_car_id")
    private Integer userCarId;
    @Column(name = "car_brand_id")
    private Integer carBrandId;
    @Column(name = "car_model_id")
    private Integer carModelId;
    @Size(max = 4)
    @Column(name = "model_year")
    private String modelYear;
    @Size(max = 255)
    @Column(name = "chase_number")
    private String chaseNumber;
    @Size(max = 255)
    @Column(name = "name_in_license_number")
    private String nameInLicenseNumber;
    @Size(max = 255)
    @Column(name = "engine_capacity")
    private String engineCapacity;
    @Size(max = 255)
    @Column(name = "kilo_meter")
    private String kiloMeter;
    @Size(max = 255)
    @Column(name = "note")
    private String note;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public UserCar() {
    }

    public UserCar(Integer carBrandId, Integer carModelId, String modelYear, String chaseNumber, String nameInLicenseNumber, String engineCapacity, String kiloMeter, String note, Integer userId, String carNumber) {
        this.carBrandId = carBrandId;
        this.carModelId = carModelId;
        this.modelYear = modelYear;
        this.chaseNumber = chaseNumber;
        this.nameInLicenseNumber = nameInLicenseNumber;
        this.engineCapacity = engineCapacity;
        this.kiloMeter = kiloMeter;
        this.note = note;
        this.userId = userId;
        this.carNumber = carNumber;
    }

    public UserCar(Integer userCarId) {
        this.userCarId = userCarId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userCarId != null ? userCarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCar)) {
            return false;
        }
        UserCar other = (UserCar) object;
        if ((this.userCarId == null && other.userCarId != null) || (this.userCarId != null && !this.userCarId.equals(other.userCarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.UserCar[ userCarId=" + userCarId + " ]";
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

}
