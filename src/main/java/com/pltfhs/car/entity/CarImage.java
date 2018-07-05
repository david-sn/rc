/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "car_image")
@NamedQueries({
    @NamedQuery(name = "CarImage.findAll", query = "SELECT c FROM CarImage c")})
public class CarImage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "image_Id")
    private Integer imageId;
    @Size(max = 255)
    @Column(name = "image_path")
    private String imagePath;
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CarDetails carId;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "image_data")
    private String imageData;
    @Column(name = "image_type")
    private Short imageType;

    public CarImage() {
    }

    public CarImage(String imagePath, CarDetails carId, Short imageType) {
        this.imagePath = imagePath;
        this.carId = carId;
        this.imageType = imageType;
    }

    public CarImage(String imagePath, CarDetails carId, String imageData, Short imageType) {
        this.imagePath = imagePath;
        this.carId = carId;
        this.imageData = imageData;
        this.imageType = imageType;
    }

    public CarImage(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public CarDetails getCarId() {
        return carId;
    }

    public void setCarId(CarDetails carId) {
        this.carId = carId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarImage)) {
            return false;
        }
        CarImage other = (CarImage) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.CarImage[ imageId=" + imageId + " ]";
    }

    public Short getImageType() {
        return imageType;
    }

    public void setImageType(Short imageType) {
        this.imageType = imageType;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

}
