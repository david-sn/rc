/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.view;

import com.pltfhs.car.entity.CarDetails;
import java.util.List;

/**
 *
 * @author Client 1
 */
public class CarDetailTemplate {

    private Long carId;
    private String carNameAr;
    private String carNameEn;
    private Integer carRate;
    private String curbWeight;
    private String engineCapacity;
    private String fuelTankCapacity;
    private String grossVehicleWeight;
    private String groundClearance;
    private String height;
    private String length;
    private String mainPic;
    private String maxPower;
    private String maxTorque;
    private int totalNumOfComments;
    private int totalNumOfDislikes;
    private int totalNumOfLikes;
    private int totalNumOfRatedUsers;
    private double averageRating;
    private String transmission;
    private String trunkCapacity;
    private String width;
    private String carPrice;

    private String internalImage1;
    private String internalImage2;
    private String internalImage3;
    private String externalImage1;
    private String externalImage2;
    private String externalImage3;
    private Item carModel;

    private boolean hasLiked;
    private boolean hasDisliked;

    private List<ObjectReviewJsonEntity> comments;

    public CarDetailTemplate(CarDetails carDetails, Long requestUserId, Byte language) {
        this.carId = carDetails.getCarId();
        this.carNameAr = carDetails.getCarNameAr();
        this.carNameEn = carDetails.getCarNameEn();
        this.averageRating = carDetails.getAverageRating();

        short likeStatus = ObjectReviewJsonEntity.getLikeStatus(carId, requestUserId);
        this.hasLiked = likeStatus == 1;
        this.hasDisliked = likeStatus == 2;
        if (requestUserId == null) {
            this.comments = ObjectReviewJsonEntity.getObjectComments(carId.intValue(), 1, language);
        } else {

        }

        this.carPrice = carDetails.getPrice();
        if (language == 1) {
//            this.carNameAr = carDetails.getCarNameAr();
        } else {
//            this.carNameEn = carDetails.getCarNameEn();
        }
        this.carRate = carDetails.getCarRate();
        this.curbWeight = carDetails.getCurbWeight();
        this.engineCapacity = carDetails.getEngineCapacity();
        this.fuelTankCapacity = carDetails.getFuelTankCapacity();
        this.grossVehicleWeight = carDetails.getGrossVehicleWeight();
        this.groundClearance = carDetails.getGroundClearance();
        this.height = carDetails.getHeight();
        this.length = carDetails.getLength();
        this.mainPic = carDetails.getMainPic();
        this.maxPower = carDetails.getMaxPower();
        this.maxTorque = carDetails.getMaxTorque();
        this.totalNumOfComments = carDetails.getTotalNumOfComments();
        this.totalNumOfDislikes = carDetails.getTotalNumOfDislikes();
        this.totalNumOfLikes = carDetails.getTotalNumOfLikes();
        this.totalNumOfRatedUsers = carDetails.getTotalNumOfRatedUsers();
        this.transmission = carDetails.getTransmission();
        this.trunkCapacity = carDetails.getTrunkCapacity();
        this.width = carDetails.getWidth();
        this.internalImage1 = carDetails.getInternalImage1();
        this.internalImage2 = carDetails.getInternalImage2();
        this.internalImage3 = carDetails.getInternalImage3();

        this.externalImage1 = carDetails.getExternalImage1();
        this.externalImage2 = carDetails.getExternalImage2();
        this.externalImage3 = carDetails.getExternalImage3();

        this.carModel = new Item(carDetails.getCarModel(), language);
    }

    public boolean isHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

    public boolean isHasDisliked() {
        return hasDisliked;
    }

    public void setHasDisliked(boolean hasDisliked) {
        this.hasDisliked = hasDisliked;
    }

    public List<ObjectReviewJsonEntity> getComments() {
        return comments;
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

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
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

    public void setComments(List<ObjectReviewJsonEntity> comments) {
        this.comments = comments;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
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

    public Integer getCarRate() {
        return carRate;
    }

    public void setCarRate(Integer carRate) {
        this.carRate = carRate;
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

    public Item getCarModel() {
        return carModel;
    }

    public void setCarModel(Item carModel) {
        this.carModel = carModel;
    }

}
