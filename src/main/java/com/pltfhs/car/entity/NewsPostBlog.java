/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "news_post_blog")
@NamedQueries({
    @NamedQuery(name = "NewsPostBlog.findAll", query = "SELECT n FROM NewsPostBlog n")})
public class NewsPostBlog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dbid")
    private Long dbid;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "expire_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;
    @Column(name = "type_id")
    private Short typeId;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "added_by_user_id")
    private BigInteger addedByUserId;
    @Size(max = 255)
    @Column(name = "label")
    private String label;
    @Size(max = 255)
    @Column(name = "cover_img")
    private String coverImg;
    @Size(max = 255)
    @Column(name = "attache1")
    private String attache1;
    @Size(max = 255)
    @Column(name = "attache2")
    private String attache2;
    @Size(max = 255)
    @Column(name = "attache3")
    private String attache3;
    @Column(name = "is_pinned")
    private Boolean isPinned;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "sub_description")
    private String subDescription;
    @Column(name = "blog_id")
    private Integer blogId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_num_of_comments")
    private int totalNumOfComments;
    @Column(name = "total_num_of_dislikes")
    private int totalNumOfDislikes;
    @Column(name = "total_num_of_likes")
    private int totalNumOfLikes;
    @Column(name = "total_num_of_rated_users")
    private int totalNumOfRatedUsers;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "average_rating")
    private Double averageRating;
    @Column(name = "total_viewing")
    private Integer totalViewing;

    public NewsPostBlog() {
        totalNumOfComments = 0;
        averageRating = 0.0;
        totalViewing = 0;
        totalNumOfDislikes = 0;
        totalNumOfRatedUsers = 0;
        totalNumOfLikes = 0;
    }

    public NewsPostBlog(Long dbid) {
        this.dbid = dbid;
    }

    public NewsPostBlog(Long dbid, int totalNumOfComments) {
        this.dbid = dbid;
        this.totalNumOfComments = totalNumOfComments;
    }

    public Long getDbid() {
        return dbid;
    }

    public void setDbid(Long dbid) {
        this.dbid = dbid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Short getTypeId() {
        return typeId;
    }

    public void setTypeId(Short typeId) {
        this.typeId = typeId;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public BigInteger getAddedByUserId() {
        return addedByUserId;
    }

    public void setAddedByUserId(BigInteger addedByUserId) {
        this.addedByUserId = addedByUserId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getAttache1() {
        return attache1;
    }

    public void setAttache1(String attache1) {
        this.attache1 = attache1;
    }

    public String getAttache2() {
        return attache2;
    }

    public void setAttache2(String attache2) {
        this.attache2 = attache2;
    }

    public String getAttache3() {
        return attache3;
    }

    public void setAttache3(String attache3) {
        this.attache3 = attache3;
    }

    public Boolean getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(Boolean isPinned) {
        this.isPinned = isPinned;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public int getTotalNumOfComments() {
        return totalNumOfComments;
    }

    public void setTotalNumOfComments(int totalNumOfComments) {
        this.totalNumOfComments = totalNumOfComments;
    }

    public int getTotalNumOfDislikes() {
        return totalNumOfDislikes;
    }

    public void setTotalNumOfDislikes(int totalNumOfDislikes) {
        this.totalNumOfDislikes = totalNumOfDislikes;
    }

    public int getTotalNumOfLikes() {
        return totalNumOfLikes;
    }

    public void setTotalNumOfLikes(int totalNumOfLikes) {
        this.totalNumOfLikes = totalNumOfLikes;
    }

    public int getTotalNumOfRatedUsers() {
        return totalNumOfRatedUsers;
    }

    public void setTotalNumOfRatedUsers(int totalNumOfRatedUsers) {
        this.totalNumOfRatedUsers = totalNumOfRatedUsers;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalViewing() {
        return totalViewing;
    }

    public void setTotalViewing(int totalViewing) {
        this.totalViewing = totalViewing;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dbid != null ? dbid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NewsPostBlog)) {
            return false;
        }
        NewsPostBlog other = (NewsPostBlog) object;
        if ((this.dbid == null && other.dbid != null) || (this.dbid != null && !this.dbid.equals(other.dbid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.NewsPostBlog[ dbid=" + dbid + " ]";
    }

}
