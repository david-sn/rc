/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "lookup_service_type")
@NamedQueries({
    @NamedQuery(name = "LookupServiceType.findAll", query = "SELECT l FROM LookupServiceType l")})
public class LookupServiceType implements Serializable {

    @OneToMany(mappedBy = "serviceId", fetch = FetchType.LAZY)
    private Set<DaysOff> daysOffSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "service_id")
    private Integer serviceId;
    @Size(max = 255)
    @Column(name = "service_type_name_ar")
    private String serviceTypeNameAr;
    @Size(max = 255)
    @Column(name = "service_type_name_en")
    private String serviceTypeNameEn;
    @Column(name = "service_min_start_time")
    @Temporal(TemporalType.TIME)
    private Date serviceMinStartTime;
    @Column(name = "service_max_start_time")
    @Temporal(TemporalType.TIME)
    private Date serviceMaxStartTime;
    @Size(max = 255)
    @Column(name = "service_duration")
    private String serviceDuration;
    @Column(name = "is_availiable")
    private Boolean isAvailiable;
    @Column(name = "max_requests_per_day")
    private Integer maxRequestsPerDay;
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public LookupServiceType() {
    }

    public LookupServiceType(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceTypeNameAr() {
        return serviceTypeNameAr;
    }

    public void setServiceTypeNameAr(String serviceTypeNameAr) {
        this.serviceTypeNameAr = serviceTypeNameAr;
    }

    public String getServiceTypeNameEn() {
        return serviceTypeNameEn;
    }

    public void setServiceTypeNameEn(String serviceTypeNameEn) {
        this.serviceTypeNameEn = serviceTypeNameEn;
    }

    public Date getServiceMinStartTime() {
        return serviceMinStartTime;
    }

    public void setServiceMinStartTime(Date serviceMinStartTime) {
        this.serviceMinStartTime = serviceMinStartTime;
    }

    public Date getServiceMaxStartTime() {
        return serviceMaxStartTime;
    }

    public void setServiceMaxStartTime(Date serviceMaxStartTime) {
        this.serviceMaxStartTime = serviceMaxStartTime;
    }

    public String getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(String serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public Boolean getIsAvailiable() {
        return isAvailiable;
    }

    public void setIsAvailiable(Boolean isAvailiable) {
        this.isAvailiable = isAvailiable;
    }

    public Integer getMaxRequestsPerDay() {
        return maxRequestsPerDay;
    }

    public void setMaxRequestsPerDay(Integer maxRequestsPerDay) {
        this.maxRequestsPerDay = maxRequestsPerDay;
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
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LookupServiceType)) {
            return false;
        }
        LookupServiceType other = (LookupServiceType) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.LookupServiceType[ serviceId=" + serviceId + " ]";
    }

    public Set<DaysOff> getDaysOffSet() {
        return daysOffSet;
    }

    public void setDaysOffSet(Set<DaysOff> daysOffSet) {
        this.daysOffSet = daysOffSet;
    }

}
