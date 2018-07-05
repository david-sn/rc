/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "car_media")
@NamedQueries({
    @NamedQuery(name = "CarMedia.findAll", query = "SELECT c FROM CarMedia c")})
public class CarMedia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "media_id")
    private Long mediaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "media_url")
    private String mediaUrl;
    @JoinColumn(name = "car", referencedColumnName = "car_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private CarDetails car;

    public CarMedia() {
    }

    public CarMedia(Long mediaId) {
        this.mediaId = mediaId;
    }

    public CarMedia(Long mediaId, Date createdDate, String mediaUrl) {
        this.mediaId = mediaId;
        this.createdDate = createdDate;
        this.mediaUrl = mediaUrl;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public CarDetails getCar() {
        return car;
    }

    public void setCar(CarDetails car) {
        this.car = car;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mediaId != null ? mediaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarMedia)) {
            return false;
        }
        CarMedia other = (CarMedia) object;
        if ((this.mediaId == null && other.mediaId != null) || (this.mediaId != null && !this.mediaId.equals(other.mediaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.CarMedia[ mediaId=" + mediaId + " ]";
    }
    
}
