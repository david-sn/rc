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
@Table(name = "complaint_form")
@NamedQueries({
    @NamedQuery(name = "ComplaintForm.findAll", query = "SELECT c FROM ComplaintForm c")})
public class ComplaintForm implements Serializable {

    @Size(max = 255)
    @Column(name = "respond_complain")
    private String respondComplain;
    @Column(name = "respond_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date respondDate;

    @Column(name = "user_id")
    private Integer userId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "complain_id")
    private Long complainId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "client_email")
    private String clientEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "client_name")
    private String clientName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "client_phone")
    private String clientPhone;
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "group_id")
    private Integer groupId;

    public ComplaintForm() {
    }

    public ComplaintForm(Long complainId) {
        this.complainId = complainId;
    }

    public ComplaintForm(Long complainId, String clientEmail, String clientName, String clientPhone) {
        this.complainId = complainId;
        this.clientEmail = clientEmail;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
    }

    public Long getComplainId() {
        return complainId;
    }

    public void setComplainId(Long complainId) {
        this.complainId = complainId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (complainId != null ? complainId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComplaintForm)) {
            return false;
        }
        ComplaintForm other = (ComplaintForm) object;
        if ((this.complainId == null && other.complainId != null) || (this.complainId != null && !this.complainId.equals(other.complainId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.ComplaintForm[ complainId=" + complainId + " ]";
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRespondComplain() {
        return respondComplain;
    }

    public void setRespondComplain(String respondComplain) {
        this.respondComplain = respondComplain;
    }

    public Date getRespondDate() {
        return respondDate;
    }

    public void setRespondDate(Date respondDate) {
        this.respondDate = respondDate;
    }
    
}
