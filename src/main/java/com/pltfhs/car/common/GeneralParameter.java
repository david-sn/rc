/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.common;

import com.pltfhs.car.entity.CarDetails;
import com.pltfhs.car.entity.CarModels;
import com.pltfhs.car.entity.DaysOff;
import com.pltfhs.car.view.Item;

/**
 *
 * @author Client 1
 */
public class GeneralParameter extends Item {

    private Integer testDriveId;
    private Long testDriveDate;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private Long entityId;
    private Byte likeStatus;
    private Integer rateValue;
    private String tags[];
    private String title;
    private String description;
    private Integer faqId;
    private Integer page;
    private Integer noOfRows;
    private Integer blogId;
    private CarDetails carDetails;
    private CarModels carModel;
    private Long carModelId;
    private Long carBrandId;
    private String[] internalImage;
    private String[] externalImage;
    private String[] deletedImages;
    private Integer lookupServiceTypeId;
    private Long targerDate;
    private Boolean onlyAvailableServices;
    private Double downPaymentPercentage;
    private Double noOfYearsInstallment;
    private Double totalCarPrice;
    private Integer systemInstallmentGroup;
    private Double customIneresetPercentage;
    private DaysOff daysOff;
    private Integer serviceId;
    private String answer;
    private String deletedTags[];
    private String note;
    private String licenseNumber;
    private String modelYear;
    private String chaseNumber;
    private String kiloMeter;
    private String engineCapacity;
    private String carNumber;
    private int userCarId;
    private String nameInLicence;
    private String faqTitle;
    private String faqDescription;
    private EObjectType entityType;
    private String oldPassword;
    private String newPassword;
    private Short typeId;
    private String to;
    private String subject;
    private String msg;
    private String type;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Short getTypeId() {
        return typeId;
    }

    public void setTypeId(Short typeId) {
        this.typeId = typeId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public EObjectType getEntityType() {
        return entityType;
    }

    public void setEntityType(EObjectType entityType) {
        this.entityType = entityType;
    }

    public String getFaqTitle() {
        return faqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }

    public String getFaqDescription() {
        return faqDescription;
    }

    public void setFaqDescription(String faqDescription) {
        this.faqDescription = faqDescription;
    }

    public String getNameInLicence() {
        return nameInLicence;
    }

    public void setNameInLicence(String nameInLicence) {
        this.nameInLicence = nameInLicence;
    }

    public int getUserCarId() {
        return userCarId;
    }

    public void setUserCarId(int userCarId) {
        this.userCarId = userCarId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

    public String getKiloMeter() {
        return kiloMeter;
    }

    public void setKiloMeter(String kiloMeter) {
        this.kiloMeter = kiloMeter;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String[] getDeletedTags() {
        return deletedTags;
    }

    public void setDeletedTags(String[] deletedTags) {
        this.deletedTags = deletedTags;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public DaysOff getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(DaysOff daysOff) {
        this.daysOff = daysOff;
    }

    public Double getCustomIneresetPercentage() {
        return customIneresetPercentage;
    }

    public void setCustomIneresetPercentage(Double customIneresetPercentage) {
        this.customIneresetPercentage = customIneresetPercentage;
    }

    public Double getDownPaymentPercentage() {
        return downPaymentPercentage;
    }

    public void setDownPaymentPercentage(Double downPaymentPercentage) {
        this.downPaymentPercentage = downPaymentPercentage;
    }

    public Double getNoOfYearsInstallment() {
        return noOfYearsInstallment;
    }

    public void setNoOfYearsInstallment(Double noOfYearsInstallment) {
        this.noOfYearsInstallment = noOfYearsInstallment;
    }

    public Double getTotalCarPrice() {
        return totalCarPrice;
    }

    public void setTotalCarPrice(Double totalCarPrice) {
        this.totalCarPrice = totalCarPrice;
    }

    public Integer getSystemInstallmentGroup() {
        return systemInstallmentGroup;
    }

    public void setSystemInstallmentGroup(Integer systemInstallmentGroup) {
        this.systemInstallmentGroup = systemInstallmentGroup;
    }

    public Boolean getOnlyAvailableServices() {
        return onlyAvailableServices;
    }

    public void setOnlyAvailableServices(Boolean onlyAvailableServices) {
        this.onlyAvailableServices = onlyAvailableServices;
    }

    public String[] getDeletedImages() {
        return deletedImages;
    }

    public void setDeletedImages(String[] deletedImages) {
        this.deletedImages = deletedImages;
    }

    public Integer getLookupServiceTypeId() {
        return lookupServiceTypeId;
    }

    public void setLookupServiceTypeId(Integer lookupServiceTypeId) {
        this.lookupServiceTypeId = lookupServiceTypeId;
    }

    public Long getTargerDate() {
        return targerDate;
    }

    public void setTargerDate(Long targerDate) {
        this.targerDate = targerDate;
    }

    public String[] getInternalImage() {
        return internalImage;
    }

    public void setInternalImage(String[] internalImage) {
        this.internalImage = internalImage;
    }

    public String[] getExternalImage() {
        return externalImage;
    }

    public void setExternalImage(String[] externalImage) {
        this.externalImage = externalImage;
    }

    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }

    public CarModels getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModels carModel) {
        this.carModel = carModel;
    }

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(Integer noOfRows) {
        this.noOfRows = noOfRows;
    }

    public Integer getFaqId() {
        return faqId;
    }

    public void setFaqId(Integer faqId) {
        this.faqId = faqId;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRateValue() {
        return rateValue;
    }

    public void setRateValue(Integer rateValue) {
        this.rateValue = rateValue;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Byte getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Byte likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Integer getTestDriveId() {
        return testDriveId;
    }

    public void setTestDriveId(Integer testDriveId) {
        this.testDriveId = testDriveId;
    }

    public Long getTestDriveDate() {
        return testDriveDate;
    }

    public void setTestDriveDate(Long testDriveDate) {
        this.testDriveDate = testDriveDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

}
