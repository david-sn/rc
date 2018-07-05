/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "entity_rate")
@NamedQueries({
    @NamedQuery(name = "EntityRate.findAll", query = "SELECT e FROM EntityRate e")})
public class EntityRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rate_id")
    private Long rateId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "entity_id")
    private BigDecimal entityId;
    @Column(name = "rate_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rateDate;
    @Column(name = "rate_number")
    private Integer rateNumber;
    @Column(name = "user_id")
    private BigInteger userId;
    @JoinColumn(name = "entity_type", referencedColumnName = "type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EntityType entityType;

    public EntityRate() {
    }

    public EntityRate(Long rateId) {
        this.rateId = rateId;
    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public BigDecimal getEntityId() {
        return entityId;
    }

    public void setEntityId(BigDecimal entityId) {
        this.entityId = entityId;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public Integer getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(Integer rateNumber) {
        this.rateNumber = rateNumber;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rateId != null ? rateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityRate)) {
            return false;
        }
        EntityRate other = (EntityRate) object;
        if ((this.rateId == null && other.rateId != null) || (this.rateId != null && !this.rateId.equals(other.rateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.EntityRate[ rateId=" + rateId + " ]";
    }
    
}
