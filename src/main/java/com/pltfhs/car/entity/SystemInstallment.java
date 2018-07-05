/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "system_installment")
@NamedQueries({
    @NamedQuery(name = "SystemInstallment.findAll", query = "SELECT s FROM SystemInstallment s")})
public class SystemInstallment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_installment_id")
    private Integer systemInstallmentId;
    @Size(max = 255)
    @Column(name = "installment_group_name_ar")
    private String installmentGroupNameAr;
    @Column(name = "is_default")
    private Boolean isDefault;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "installment_percentage")
    private Double installmentPercentage;
    @Column(name = "is_lock")
    private Boolean isLock;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "is_deleted")
    @JsonIgnore
    private Boolean isDeleted;
    @Size(max = 255)
    @Column(name = "installment_group_name_en")
    private String installmentGroupNameEn;

    public SystemInstallment() {
    }

    public SystemInstallment(Integer systemInstallmentId) {
        this.systemInstallmentId = systemInstallmentId;
    }

    public Integer getSystemInstallmentId() {
        return systemInstallmentId;
    }

    public void setSystemInstallmentId(Integer systemInstallmentId) {
        this.systemInstallmentId = systemInstallmentId;
    }

    public String getInstallmentGroupNameAr() {
        return installmentGroupNameAr;
    }

    public void setInstallmentGroupNameAr(String installmentGroupNameAr) {
        this.installmentGroupNameAr = installmentGroupNameAr;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Double getInstallmentPercentage() {
        return installmentPercentage;
    }

    public void setInstallmentPercentage(Double installmentPercentage) {
        this.installmentPercentage = installmentPercentage;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getInstallmentGroupNameEn() {
        return installmentGroupNameEn;
    }

    public void setInstallmentGroupNameEn(String installmentGroupNameEn) {
        this.installmentGroupNameEn = installmentGroupNameEn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (systemInstallmentId != null ? systemInstallmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemInstallment)) {
            return false;
        }
        SystemInstallment other = (SystemInstallment) object;
        if ((this.systemInstallmentId == null && other.systemInstallmentId != null) || (this.systemInstallmentId != null && !this.systemInstallmentId.equals(other.systemInstallmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.SystemInstallment[ systemInstallmentId=" + systemInstallmentId + " ]";
    }

}
