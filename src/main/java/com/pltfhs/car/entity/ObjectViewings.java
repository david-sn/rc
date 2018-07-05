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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "object_viewings")
@NamedQueries({
    @NamedQuery(name = "ObjectViewings.findAll", query = "SELECT o FROM ObjectViewings o")})
public class ObjectViewings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viewing_id")
    private Long viewingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "object_type_id")
    private short objectTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "object_id")
    private String objectId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acting_user_id")
    private long actingUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "viewing_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date viewingDateTime;

    public ObjectViewings() {
    }

    public ObjectViewings(Long viewingId) {
        this.viewingId = viewingId;
    }

    public ObjectViewings(Long viewingId, short objectTypeId, String objectId, long actingUserId, Date viewingDateTime) {
        this.viewingId = viewingId;
        this.objectTypeId = objectTypeId;
        this.objectId = objectId;
        this.actingUserId = actingUserId;
        this.viewingDateTime = viewingDateTime;
    }

    public ObjectViewings(String objectId, Date date, Integer objectTypeId, Long userId) {
        this.objectTypeId = objectTypeId.shortValue();
        this.objectId = objectId;
        this.actingUserId = userId;
        this.viewingDateTime = date;
    }

    public Long getViewingId() {
        return viewingId;
    }

    public void setViewingId(Long viewingId) {
        this.viewingId = viewingId;
    }

    public short getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(short objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public long getActingUserId() {
        return actingUserId;
    }

    public void setActingUserId(long actingUserId) {
        this.actingUserId = actingUserId;
    }

    public Date getViewingDateTime() {
        return viewingDateTime;
    }

    public void setViewingDateTime(Date viewingDateTime) {
        this.viewingDateTime = viewingDateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (viewingId != null ? viewingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjectViewings)) {
            return false;
        }
        ObjectViewings other = (ObjectViewings) object;
        if ((this.viewingId == null && other.viewingId != null) || (this.viewingId != null && !this.viewingId.equals(other.viewingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.ObjectViewings[ viewingId=" + viewingId + " ]";
    }

}
