/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)

    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "facebook_token")
    private String facebookToken;
    @Column(name = "social_id")
    private String socialId;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 255)
    @Column(name = "gmail_token")
    private String gmailToken;
    @Column(name = "is_activated")
    private Short isActivated;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 255)
    @Column(name = "mobile_token")
    private String mobileToken;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "profile_pic")
    private String profilePic;
    @Basic(optional = false)
    @Column(name = "user_language")
    private String userLanguage;
    @Size(max = 255)
    @Column(name = "user_phone")
    private String userPhone;
    @Size(max = 255)
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "is_social")
    private Boolean isSocial;
    @Column(name = "user_type")
    public String userType;
    @JoinTable(name = "account_role", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<SystemRoles> systemRolesSet;

    public Users() {
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public Boolean isIsSocial() {
        return isSocial;
    }

    public void setIsSocial(Boolean isSocial) {
        this.isSocial = isSocial;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Users(String email) {
        this.email = email;
    }

    public Users(Long userId) {
        this.userId = userId;
    }

    public Users(Long userId, String firstName, String lastName, String email, String password, Date createdDate, String userLanguage, List<SystemRoles> systemRoles) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.userLanguage = userLanguage;
        this.systemRolesSet = systemRoles;
    }

    public String getUserType() {
        return userType;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGmailToken() {
        return gmailToken;
    }

    public void setGmailToken(String gmailToken) {
        this.gmailToken = gmailToken;
    }

    public Short getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Short isActivated) {
        this.isActivated = isActivated;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileToken() {
        return mobileToken;
    }

    public void setMobileToken(String mobileToken) {
        this.mobileToken = mobileToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<SystemRoles> getSystemRolesSet() {
        return systemRolesSet;
    }

    public void setSystemRolesSet(List<SystemRoles> systemRolesSet) {
        this.systemRolesSet = systemRolesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.Users[ userId=" + userId + " ]";
    }

}
