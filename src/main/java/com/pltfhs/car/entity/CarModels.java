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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "car_models")
@NamedQueries({
    @NamedQuery(name = "CarModels.findAll", query = "SELECT c FROM CarModels c")})
public class CarModels implements Serializable {

    @Column(name = "type_id")
    private Short typeId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "model_id")
    private Long modelId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdDate;
    @Basic(optional = false)
    @Lob
    @Column(name = "model_img")
    private String modelImg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "model_name_ar")
    private String modelNameAr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "model_name_en")
    private String modelNameEn;
    @JoinColumn(name = "car_brands", referencedColumnName = "brand_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private CarBrands carBrands;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carModel", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CarDetails> carDetailsSet;

    public CarModels() {
    }

    public CarModels(Long modelId) {
        this.modelId = modelId;
    }

    public CarModels(Long modelId, Date createdDate, String modelImg, String modelNameAr, String modelNameEn) {
        this.modelId = modelId;
        this.createdDate = createdDate;
        this.modelImg = modelImg;
        this.modelNameAr = modelNameAr;
        this.modelNameEn = modelNameEn;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModelImg() {
        return modelImg;
    }

    public void setModelImg(String modelImg) {
        this.modelImg = modelImg;
    }

    public String getModelNameAr() {
        return modelNameAr;
    }

    public void setModelNameAr(String modelNameAr) {
        this.modelNameAr = modelNameAr;
    }

    public String getModelNameEn() {
        return modelNameEn;
    }

    public void setModelNameEn(String modelNameEn) {
        this.modelNameEn = modelNameEn;
    }

    public CarBrands getCarBrands() {
        return carBrands;
    }

    public void setCarBrands(CarBrands carBrands) {
        this.carBrands = carBrands;
    }

    public Set<CarDetails> getCarDetailsSet() {
        return carDetailsSet;
    }

    public void setCarDetailsSet(Set<CarDetails> carDetailsSet) {
        this.carDetailsSet = carDetailsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelId != null ? modelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarModels)) {
            return false;
        }
        CarModels other = (CarModels) object;
        if ((this.modelId == null && other.modelId != null) || (this.modelId != null && !this.modelId.equals(other.modelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.CarModels[ modelId=" + modelId + " ]";
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
