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
@Table(name = "test_dirve_request")
@NamedQueries({
    @NamedQuery(name = "TestDirveRequest.findAll", query = "SELECT t FROM TestDirveRequest t")})
public class TestDirveRequest implements Serializable {

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Size(max = 255)
    @Column(name = "note")
    private String note;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "test_drive_id")
    private Long testDriveId;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "test_drive_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date testDriveDate;
    @Column(name = "is_readed")
    private boolean isReaded;

    public TestDirveRequest() {
    }

    public TestDirveRequest(Long testDriveId) {
        this.testDriveId = testDriveId;
    }

    public TestDirveRequest(String note, String clientEmail, String clientName, String clientPhone, Date testDriveDate) {
        this.createDate = new Date();
        this.note = note;
        this.clientEmail = clientEmail;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.testDriveDate = new Date();
        this.isReaded = false;
    }

    public boolean isIsReaded() {
        return isReaded;
    }

    public void setIsReaded(boolean isReaded) {
        this.isReaded = isReaded;
    }

    public Long getTestDriveId() {
        return testDriveId;
    }

    public void setTestDriveId(Long testDriveId) {
        this.testDriveId = testDriveId;
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

    public Date getTestDriveDate() {
        return testDriveDate;
    }

    public void setTestDriveDate(Date testDriveDate) {
        this.testDriveDate = testDriveDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testDriveId != null ? testDriveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestDirveRequest)) {
            return false;
        }
        TestDirveRequest other = (TestDirveRequest) object;
        if ((this.testDriveId == null && other.testDriveId != null) || (this.testDriveId != null && !this.testDriveId.equals(other.testDriveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.TestDirveRequest[ testDriveId=" + testDriveId + " ]";
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
