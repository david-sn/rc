/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "car_details")
@NamedQueries({
    @NamedQuery(name = "CarDetails.findAll", query = "SELECT c FROM CarDetails c")})
public class CarDetails implements Serializable {

    @Size(max = 255)
    @Column(name = "car_catalog")
    private String carCatalog;
    @Size(max = 255)
    @Column(name = "internal_image1")
    private String internalImage1;
    @Size(max = 255)
    @Column(name = "internal_image2")
    private String internalImage2;
    @Size(max = 255)
    @Column(name = "internal_image3")
    private String internalImage3;
    @Size(max = 255)
    @Column(name = "external_image1")
    private String externalImage1;
    @Size(max = 255)
    @Column(name = "external_image2")
    private String externalImage2;
    @Size(max = 255)
    @Column(name = "external_image3")
    private String externalImage3;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "carId", fetch = FetchType.LAZY)
    private Set<CarImage> carImageSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "car_id")
    private Long carId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "car_name_ar")
    private String carNameAr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "car_name_en")
    private String carNameEn;
    @Column(name = "car_rate")
    private Integer carRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdDate;
    @Size(max = 255)
    @Column(name = "curb_weight")
    private String curbWeight;
    @Size(max = 255)
    @Column(name = "engine_capacity")
    private String engineCapacity;
    @Size(max = 255)
    @Column(name = "fuel_tank_capacity")
    private String fuelTankCapacity;
    @Size(max = 255)
    @Column(name = "gross_vehicle_weight")
    private String grossVehicleWeight;
    @Size(max = 255)
    @Column(name = "ground_clearance")
    private String groundClearance;
    @Size(max = 255)
    @Column(name = "height")
    private String height;
    @Size(max = 255)
    @Column(name = "length")
    private String length;
    @Column(name = "main_pic")
    private String mainPic;
    @Size(max = 255)
    @Column(name = "max_power")
    private String maxPower;
    @Size(max = 255)
    @Column(name = "max_torque")
    private String maxTorque;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_num_of_comments")
    private int totalNumOfComments;
    @Column(name = "total_num_of_dislikes")
    private int totalNumOfDislikes;
    @Column(name = "total_num_of_likes")
    private int totalNumOfLikes;
    @Column(name = "total_num_of_rated_users")
    private int totalNumOfRatedUsers;
    @Column(name = "average_rating")
    private double averageRating;
    @Column(name = "total_viewing")
    private int totalViewing;
    @Size(max = 255)
    @Column(name = "transmission")
    private String transmission;
    @Size(max = 255)
    @Column(name = "trunk_capacity")
    private String trunkCapacity;
    @Size(max = 255)
    @Column(name = "width")
    private String width;
    @Size(max = 255)
    @Column(name = "price")
    private String price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car", fetch = FetchType.LAZY)
    private Set<CarMedia> carMediaSet;
    @JoinColumn(name = "car_model", referencedColumnName = "model_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private CarModels carModel;

    public CarDetails() {
        totalNumOfComments = 0;
        averageRating = 0.0;
        totalViewing = 0;
        totalNumOfDislikes = 0;
        totalNumOfRatedUsers = 0;
        totalNumOfLikes = 0;
    }

    public CarDetails(Long carId) {
        this();
        this.carId = carId;
    }

    public CarDetails(Long carId, String carNameAr, String carNameEn, Date createdDate) {
        this();
        this.carId = carId;
        this.carNameAr = carNameAr;
        this.carNameEn = carNameEn;
        this.createdDate = createdDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarNameAr() {
        return carNameAr;
    }

    public void setCarNameAr(String carNameAr) {
        this.carNameAr = carNameAr;
    }

    public String getCarNameEn() {
        return carNameEn;
    }

    public void setCarNameEn(String carNameEn) {
        this.carNameEn = carNameEn;
    }

    public String getCarCatalog() {
        return carCatalog;
    }

    public void setCarCatalog(String carCatalog) {
        this.carCatalog = carCatalog;
    }

    public Integer getCarRate() {
        return carRate;
    }

    public void setCarRate(Integer carRate) {
        this.carRate = carRate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCurbWeight() {
        return curbWeight;
    }

    public void setCurbWeight(String curbWeight) {
        this.curbWeight = curbWeight;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(String fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public String getGrossVehicleWeight() {
        return grossVehicleWeight;
    }

    public void setGrossVehicleWeight(String grossVehicleWeight) {
        this.grossVehicleWeight = grossVehicleWeight;
    }

    public String getGroundClearance() {
        return groundClearance;
    }

    public void setGroundClearance(String groundClearance) {
        this.groundClearance = groundClearance;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }

    public Integer getTotalViewing() {
        return totalViewing;
    }

    public void setTotalViewing(Integer totalViewing) {
        this.totalViewing = totalViewing;
    }

    public String getMaxTorque() {
        return maxTorque;
    }

    public void setMaxTorque(String maxTorque) {
        this.maxTorque = maxTorque;
    }

    public int getTotalNumOfComments() {
        return totalNumOfComments;
    }

    public void setTotalNumOfComments(int totalNumOfComments) {
        this.totalNumOfComments = totalNumOfComments;
    }

    public int getTotalNumOfDislikes() {
        return totalNumOfDislikes;
    }

    public void setTotalNumOfDislikes(int totalNumOfDislikes) {
        this.totalNumOfDislikes = totalNumOfDislikes;
    }

    public int getTotalNumOfLikes() {
        return totalNumOfLikes;
    }

    public void setTotalNumOfLikes(int totalNumOfLikes) {
        this.totalNumOfLikes = totalNumOfLikes;
    }

    public int getTotalNumOfRatedUsers() {
        return totalNumOfRatedUsers;
    }

    public void setTotalNumOfRatedUsers(int totalNumOfRatedUsers) {
        this.totalNumOfRatedUsers = totalNumOfRatedUsers;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getTrunkCapacity() {
        return trunkCapacity;
    }

    public void setTrunkCapacity(String trunkCapacity) {
        this.trunkCapacity = trunkCapacity;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public Set<CarMedia> getCarMediaSet() {
        return carMediaSet;
    }

    public void setCarMediaSet(Set<CarMedia> carMediaSet) {
        this.carMediaSet = carMediaSet;
    }

    public CarModels getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModels carModel) {
        this.carModel = carModel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarDetails)) {
            return false;
        }
        CarDetails other = (CarDetails) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.CarDetails[ carId=" + carId + " ]";
    }

    public Set<CarImage> getCarImageSet() {
        return carImageSet;
    }

    public void setCarImageSet(Set<CarImage> carImageSet) {
        this.carImageSet = carImageSet;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

  

    public String getInternalImage1() {
        return internalImage1;
    }

    public void setInternalImage1(String internalImage1) {
        this.internalImage1 = internalImage1;
    }

    public String getInternalImage2() {
        return internalImage2;
    }

    public void setInternalImage2(String internalImage2) {
        this.internalImage2 = internalImage2;
    }

    public String getInternalImage3() {
        return internalImage3;
    }

    public void setInternalImage3(String internalImage3) {
        this.internalImage3 = internalImage3;
    }

    public String getExternalImage1() {
        return externalImage1;
    }

    public void setExternalImage1(String externalImage1) {
        this.externalImage1 = externalImage1;
    }

    public String getExternalImage2() {
        return externalImage2;
    }

    public void setExternalImage2(String externalImage2) {
        this.externalImage2 = externalImage2;
    }

    public String getExternalImage3() {
        return externalImage3;
    }

    public void setExternalImage3(String externalImage3) {
        this.externalImage3 = externalImage3;
    }

}
