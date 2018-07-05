/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.view;

import com.pltfhs.car.entity.LookupServiceType;

/**
 *
 * @author Client 1
 */
public class LookupServiceTypeView {

    private Integer serviceId;
    private String serviceTypeName;
    private Long serviceMinStartTime;
    private Long serviceMaxStartTime;
    private String serviceDuration;
    private Boolean isAvailiable;
    private Integer maxRequestsPerDay;

    public LookupServiceTypeView(LookupServiceType serviceType, byte language) {
        this.serviceId = serviceType.getServiceId();
        if (language == 2) {
            this.serviceTypeName = serviceType.getServiceTypeNameAr();
        } else {
            this.serviceTypeName = serviceType.getServiceTypeNameEn();
        }
        this.isAvailiable = serviceType.getIsAvailiable();
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public Long getServiceMinStartTime() {
        return serviceMinStartTime;
    }

    public Long getServiceMaxStartTime() {
        return serviceMaxStartTime;
    }

    public String getServiceDuration() {
        return serviceDuration;
    }

    public Boolean getIsAvailiable() {
        return isAvailiable;
    }

    public Integer getMaxRequestsPerDay() {
        return maxRequestsPerDay;
    }

}
