/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "car_brands")
@NamedQueries({
    @NamedQuery(name = "CarBrands.findAll", query = "SELECT c FROM CarBrands c")})
public class CarBrands implements Serializable {

    @Column(name = "type_id")
    private Short typeId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "brand_id")
    private Integer brandId;
    @Lob
    @Column(name = "brand_image")
    private String brandImage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "brand_name_ar")
    private String brandNameAr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "brand_name_en")
    private String brandNameEn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carBrands", fetch = FetchType.LAZY)
    private Set<CarModels> carModelsSet;

    public CarBrands() {
    }

    public CarBrands(Integer brandId) {
        this.brandId = brandId;
    }

    public CarBrands(Integer brandId, String brandNameAr, String brandNameEn, Date createdDate) {
        this.brandId = brandId;
        this.brandNameAr = brandNameAr;
        this.brandNameEn = brandNameEn;
        this.createdDate = createdDate;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }

    public String getBrandNameAr() {
        return brandNameAr;
    }

    public void setBrandNameAr(String brandNameAr) {
        this.brandNameAr = brandNameAr;
    }

    public String getBrandNameEn() {
        return brandNameEn;
    }

    public void setBrandNameEn(String brandNameEn) {
        this.brandNameEn = brandNameEn;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<CarModels> getCarModelsSet() {
        return carModelsSet;
    }

    public void setCarModelsSet(Set<CarModels> carModelsSet) {
        this.carModelsSet = carModelsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brandId != null ? brandId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarBrands)) {
            return false;
        }
        CarBrands other = (CarBrands) object;
        if ((this.brandId == null && other.brandId != null) || (this.brandId != null && !this.brandId.equals(other.brandId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.CarBrands[ brandId=" + brandId + " ]";
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Short getTypeId() {
        return typeId;
    }

    public void setTypeId(Short typeId) {
        this.typeId = typeId;
    }

}
