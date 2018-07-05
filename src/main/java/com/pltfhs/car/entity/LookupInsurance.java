/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "lookup_insurance")
@NamedQueries({
    @NamedQuery(name = "LookupInsurance.findAll", query = "SELECT l FROM LookupInsurance l")})
public class LookupInsurance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "insurace_id")
    private Integer insuraceId;
    @Size(max = 255)
    @Column(name = "insurance_name")
    private String insuranceName;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public LookupInsurance() {
    }

    public LookupInsurance(Integer insuraceId) {
        this.insuraceId = insuraceId;
    }

    public Integer getInsuraceId() {
        return insuraceId;
    }

    public void setInsuraceId(Integer insuraceId) {
        this.insuraceId = insuraceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insuraceId != null ? insuraceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LookupInsurance)) {
            return false;
        }
        LookupInsurance other = (LookupInsurance) object;
        if ((this.insuraceId == null && other.insuraceId != null) || (this.insuraceId != null && !this.insuraceId.equals(other.insuraceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.LookupInsurance[ insuraceId=" + insuraceId + " ]";
    }
    
}
