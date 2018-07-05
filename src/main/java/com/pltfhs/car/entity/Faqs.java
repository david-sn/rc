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
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "faqs")
@NamedQueries({
    @NamedQuery(name = "Faqs.findAll", query = "SELECT f FROM Faqs f")})
public class Faqs implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_num_of_comments")
    private int totalNumOfComments;
    @Column(name = "total_num_of_dislikes")
    private int totalNumOfDislikes;
    @Column(name = "total_num_of_likes")
    private int totalNumOfLikes;
    @Column(name = "total_num_of_rated_users")
    private int totalNumOfRatedUsers;
    @Column(name = "average_rating")
    private double averageRating;
    @Column(name = "total_viewing")
    private int totalViewing;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "faq_id")
    private Long faqId;
    @Size(max = 255)
    @Column(name = "faq_title")
    private String faqTitle;
    @Size(max = 255)
    @Column(name = "faq_description")
    private String faqDescription;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "user_id")
    private BigInteger userId;
    @Size(max = 255)
    @Column(name = "answer1")
    private String answer1;
    @Size(max = 255)
    @Column(name = "answer2")
    private String answer2;
    @Size(max = 255)
    @Column(name = "answer3")
    private String answer3;
    @Size(max = 255)
    @Column(name = "answer4")
    private String answer4;
    @Size(max = 255)
    @Column(name = "answer5")
    private String answer5;

    public Faqs() {
        totalNumOfComments = 0;
        averageRating = 0.0;
        totalViewing = 0;
        totalNumOfDislikes = 0;
        totalNumOfRatedUsers = 0;
        totalNumOfLikes = 0;
    }

    public Integer getTotalViewing() {
        return totalViewing;
    }

    public void setTotalViewing(Integer totalViewing) {
        this.totalViewing = totalViewing;
    }

    public Faqs(Long faqId) {
        this.faqId = faqId;
    }

    public Long getFaqId() {
        return faqId;
    }

    public void setFaqId(Long faqId) {
        this.faqId = faqId;
    }

    public String getFaqTitle() {
        return faqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }

    public String getFaqDescription() {
        return faqDescription;
    }

    public void setFaqDescription(String faqDescription) {
        this.faqDescription = faqDescription;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getAnswer5() {
        return answer5;
    }

    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (faqId != null ? faqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faqs)) {
            return false;
        }
        Faqs other = (Faqs) object;
        if ((this.faqId == null && other.faqId != null) || (this.faqId != null && !this.faqId.equals(other.faqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.Fags[ faqId=" + faqId + " ]";
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

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

}
