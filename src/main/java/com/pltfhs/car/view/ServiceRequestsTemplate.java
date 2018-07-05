/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.view;

import com.pltfhs.car.entity.LookupServiceType;
import com.pltfhs.car.entity.ServiceRequests;
import com.pltfhs.car.entity.Users;

/**
 *
 * @author Client 1
 */
public class ServiceRequestsTemplate {

    private String kilometers;
    private Integer requestId;
    private Long creationDate;
    private String code;
    private NOwner owner;
    private Item serviceId;
    private UserCarView userCar;

    public ServiceRequestsTemplate(ServiceRequests serviceRequests, UserCarView userCar, Users users, LookupServiceType serviceType, byte language) {
        this.kilometers = serviceRequests.getKilometers();
        this.requestId = serviceRequests.getRequestId();
        this.creationDate = serviceRequests.getCreationDate().getTime();
        this.code = serviceRequests.getCode();
        this.owner = new NOwner(users);
        this.serviceId = new Item(serviceType, language);
        this.userCar = userCar;
    }

    public Item getServiceId() {
        return serviceId;
    }

    public void setServiceId(Item serviceId) {
        this.serviceId = serviceId;
    }

    public UserCarView getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarView userCar) {
        this.userCar = userCar;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public NOwner getOwner() {
        return owner;
    }

    public void setOwner(NOwner owner) {
        this.owner = owner;
    }

}
