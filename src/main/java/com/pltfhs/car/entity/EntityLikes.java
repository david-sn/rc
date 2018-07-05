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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "entity_likes")
@NamedQueries({
    @NamedQuery(name = "EntityLikes.findAll", query = "SELECT e FROM EntityLikes e")})
public class EntityLikes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "like_id")
    private Long likeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entity_id")
    private long entityId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "like_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date likeDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "like_status_id")
    private short likeStatusId;
    @JoinColumn(name = "entity_type", referencedColumnName = "type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EntityType entityType;

    public EntityLikes() {
    }

    public EntityLikes(Long likeId) {
        this.likeId = likeId;
    }

    public EntityLikes(Long likeId, long entityId, Date likeDate, long userId, short likeStatusId) {
        this.likeId = likeId;
        this.entityId = entityId;
        this.likeDate = likeDate;
        this.userId = userId;
        this.likeStatusId = likeStatusId;
    }

    public EntityLikes(Date date, Long userId, long entityId) {
        this.likeDate = date;
        this.userId = userId;
        this.entityId = entityId;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public short getLikeStatusId() {
        return likeStatusId;
    }

    public void setLikeStatusId(short likeStatusId) {
        this.likeStatusId = likeStatusId;
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
        hash += (likeId != null ? likeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityLikes)) {
            return false;
        }
        EntityLikes other = (EntityLikes) object;
        if ((this.likeId == null && other.likeId != null) || (this.likeId != null && !this.likeId.equals(other.likeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.EntityLikes[ likeId=" + likeId + " ]";
    }

}
