/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pltfhs.car.entity.EntityLikes;

/**
 *
 * @author Client 1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleKeys {

    private Long totalLikes;
    private Long totalDislikes;
    private Long entityId;
    private Short likeStatusId;
    private Integer entityType;
    private Double averageRate;
    private Long totalRate;
    private Long brandId;
    private Double installmentPerMonth;
    private Integer totalViewing;

    public SingleKeys(EntityLikes enttyLikes, long totalLikes, long totalDislikes) {

        this.totalLikes = totalLikes;
        this.totalDislikes = totalDislikes;
        if (enttyLikes != null) {
            this.entityId = enttyLikes.getEntityId();
            this.likeStatusId = enttyLikes.getLikeStatusId();
            this.entityType = enttyLikes.getEntityType().getTypeId();
        }
    }

    public Integer getTotalViewing() {
        return totalViewing;
    }

    public void setTotalViewing(Integer totalViewing) {
        this.totalViewing = totalViewing;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Double getInstallmentPerMonth() {
        return installmentPerMonth;
    }

    public void setInstallmentPerMonth(Double installmentPerMonth) {
        this.installmentPerMonth = installmentPerMonth;
    }

    public Long getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Long totalRate) {
        this.totalRate = totalRate;
    }

    public SingleKeys() {
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public Long getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Long totalLikes) {
        this.totalLikes = totalLikes;
    }

    public Long getTotalDislikes() {
        return totalDislikes;
    }

    public void setTotalDislikes(Long totalDislikes) {
        this.totalDislikes = totalDislikes;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Short getLikeStatusId() {
        return likeStatusId;
    }

    public void setLikeStatusId(Short likeStatusId) {
        this.likeStatusId = likeStatusId;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public void setEntityType(Integer entityType) {
        this.entityType = entityType;
    }

}
