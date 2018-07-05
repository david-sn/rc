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
import javax.persistence.Lob;
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
@Table(name = "comments")
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c")})
public class Comments implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "no_of_likes")
    private long noOfLikes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "no_of_dislikes")
    private long noOfDislikes;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comment_id")
    private Long commentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "comment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "comment_text")
    private String commentText;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entity_id")
    private long entityId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private long userId;
    @JsonIgnore
    @JoinColumn(name = "entity_type", referencedColumnName = "type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EntityType entityType;

    public Comments() {
        noOfDislikes = 0;
        noOfLikes = 0;
    }

    public Comments(Long commentId) {
        this.commentId = commentId;
    }

    public Comments(Long commentId, long userId, long entityId, String commentText, Date commentDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.entityId = entityId;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.Comments[ commentId=" + commentId + " ]";
    }

    public long getNoOfLikes() {
        return noOfLikes;
    }

    public void setNoOfLikes(long noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public long getNoOfDislikes() {
        return noOfDislikes;
    }

    public void setNoOfDislikes(long noOfDislikes) {
        this.noOfDislikes = noOfDislikes;
    }

}
