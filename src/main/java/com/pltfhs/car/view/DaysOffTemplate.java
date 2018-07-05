/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.view;

import com.pltfhs.car.entity.DaysOff;

/**
 *
 * @author Client 1
 */
public class DaysOffTemplate {

    private Integer dayId;
    private long dateOffFrom;
    private long dateOffTo;
    private Item serviceType;

    public DaysOffTemplate(DaysOff daysOff, byte language) {
        this.dayId = daysOff.getDayId();
        this.dateOffFrom = daysOff.getDateOffFrom().getTime();
        this.dateOffTo = daysOff.getDateOffTo().getTime();
        this.serviceType = new Item(daysOff.getServiceId(), language);
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public long getDateOffFrom() {
        return dateOffFrom;
    }

    public void setDateOffFrom(long dateOffFrom) {
        this.dateOffFrom = dateOffFrom;
    }

    public long getDateOffTo() {
        return dateOffTo;
    }

    public void setDateOffTo(long dateOffTo) {
        this.dateOffTo = dateOffTo;
    }

    public Item getServiceType() {
        return serviceType;
    }

    public void setServiceType(Item serviceType) {
        this.serviceType = serviceType;
    }
    

}
