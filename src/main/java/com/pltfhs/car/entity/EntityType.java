/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "entity_type")
@NamedQueries({
    @NamedQuery(name = "EntityType.findAll", query = "SELECT e FROM EntityType e")})
public class EntityType implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityType", fetch = FetchType.LAZY)
    private Set<Comments> commentsSet;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "type_id")
    private Integer typeId;
    @Size(max = 45)
    @Column(name = "type_name")
    private String typeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityType", fetch = FetchType.LAZY)
    private Set<EntityRate> entityRateSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entityType", fetch = FetchType.LAZY)
    private Set<EntityLikes> entityLikesSet;

    public EntityType() {
    }

    public EntityType(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Set<EntityRate> getEntityRateSet() {
        return entityRateSet;
    }

    public void setEntityRateSet(Set<EntityRate> entityRateSet) {
        this.entityRateSet = entityRateSet;
    }

    public Set<EntityLikes> getEntityLikesSet() {
        return entityLikesSet;
    }

    public void setEntityLikesSet(Set<EntityLikes> entityLikesSet) {
        this.entityLikesSet = entityLikesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityType)) {
            return false;
        }
        EntityType other = (EntityType) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.EntityType[ typeId=" + typeId + " ]";
    }

    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }
    
}
