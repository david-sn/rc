/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "days_off")
@NamedQueries({
    @NamedQuery(name = "DaysOff.findAll", query = "SELECT d FROM DaysOff d")})
public class DaysOff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id")
    private Integer dayId;
    @Column(name = "date_off_from")
    @Temporal(TemporalType.DATE)
    private Date dateOffFrom;
    @Column(name = "date_off_to")
    @Temporal(TemporalType.DATE)
    private Date dateOffTo;
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LookupServiceType serviceId;

    public DaysOff() {
    }

    public DaysOff(Integer dayId) {
        this.dayId = dayId;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public Date getDateOffFrom() {
        return dateOffFrom;
    }

    public void setDateOffFrom(Date dateOffFrom) {
        this.dateOffFrom = dateOffFrom;
    }

    public Date getDateOffTo() {
        return dateOffTo;
    }

    public void setDateOffTo(Date dateOffTo) {
        this.dateOffTo = dateOffTo;
    }

    public LookupServiceType getServiceId() {
        return serviceId;
    }

    public void setServiceId(LookupServiceType serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dayId != null ? dayId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DaysOff)) {
            return false;
        }
        DaysOff other = (DaysOff) object;
        if ((this.dayId == null && other.dayId != null) || (this.dayId != null && !this.dayId.equals(other.dayId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.DaysOff[ dayId=" + dayId + " ]";
    }
    
}
