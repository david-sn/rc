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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "insurance_percentage")
@NamedQueries({
    @NamedQuery(name = "InsurancePercentage.findAll", query = "SELECT i FROM InsurancePercentage i")})
public class InsurancePercentage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dbid")
    private Integer dbid;
    @Column(name = "insurance_id")
    private Integer insuranceId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "insurace_value")
    private Double insuraceValue;
    @Column(name = "is_locked")
    private Boolean isLocked;
    @JoinColumn(name = "car_brand_id", referencedColumnName = "brand_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CarBrands carBrandId;

    public InsurancePercentage() {
    }

    public InsurancePercentage(Integer dbid) {
        this.dbid = dbid;
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Double getInsuraceValue() {
        return insuraceValue;
    }

    public void setInsuraceValue(Double insuraceValue) {
        this.insuraceValue = insuraceValue;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public CarBrands getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(CarBrands carBrandId) {
        this.carBrandId = carBrandId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dbid != null ? dbid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsurancePercentage)) {
            return false;
        }
        InsurancePercentage other = (InsurancePercentage) object;
        if ((this.dbid == null && other.dbid != null) || (this.dbid != null && !this.dbid.equals(other.dbid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.InsurancePercentage[ dbid=" + dbid + " ]";
    }
    
}
