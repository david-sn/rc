/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "faq_tags")
@NamedQueries({
    @NamedQuery(name = "FaqTags.findAll", query = "SELECT f FROM FaqTags f")})
public class FaqTags implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tag_id")
    private Long tagId;
    @Size(max = 255)
    @Column(name = "tag_value")
    private String tagValue;
    @Column(name = "faq_id")
    private BigInteger faqId;

    public FaqTags() {
    }

    public FaqTags(Long tagId) {
        this.tagId = tagId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public BigInteger getFaqId() {
        return faqId;
    }

    public void setFaqId(BigInteger faqId) {
        this.faqId = faqId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagId != null ? tagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaqTags)) {
            return false;
        }
        FaqTags other = (FaqTags) object;
        if ((this.tagId == null && other.tagId != null) || (this.tagId != null && !this.tagId.equals(other.tagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.FaqTags[ tagId=" + tagId + " ]";
    }
    
}
