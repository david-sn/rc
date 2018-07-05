/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pltfhs.car.entity.NewsPostBlog;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Client 1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsBlogPostTemplate {

    private Long dbid;
    private String title;
    private String description;
    private Date creationDate;
    private String coverImg;
    private String attache1;
    private String attache2;
    private String attache3;
    private Boolean isPinned;
    private String code;
    private String subDescription;
    private int totalNumOfComments;
    private int totalNumOfDislikes;
    private int totalNumOfLikes;
    private int totalNumOfRatedUsers;
    private double averageRating;
    private int totalViewing;
    private List<ObjectReviewJsonEntity> comments;

    public NewsBlogPostTemplate(NewsPostBlog newsPostBlog, byte language, short objectTypeId) {
        this.dbid = newsPostBlog.getDbid();
        this.title = newsPostBlog.getTitle();
        this.description = newsPostBlog.getDescription();
        this.creationDate = newsPostBlog.getCreationDate();
        this.coverImg = newsPostBlog.getCoverImg();
        this.attache1 = newsPostBlog.getAttache1();
        this.attache2 = newsPostBlog.getAttache2();
        this.attache3 = newsPostBlog.getAttache3();
        this.isPinned = newsPostBlog.getIsPinned();
        this.code = newsPostBlog.getCode();
        this.subDescription = newsPostBlog.getSubDescription();
        this.totalNumOfComments = newsPostBlog.getTotalNumOfComments();
        this.totalNumOfDislikes = newsPostBlog.getTotalNumOfDislikes();
        this.totalNumOfLikes = newsPostBlog.getTotalNumOfLikes();
        this.totalNumOfRatedUsers = newsPostBlog.getTotalNumOfRatedUsers();
        this.totalViewing = newsPostBlog.getTotalViewing();
        this.averageRating = newsPostBlog.getAverageRating();
        this.comments = ObjectReviewJsonEntity.getObjectComments(dbid.intValue(), objectTypeId, language);

    }

    public int getTotalNumOfComments() {
        return totalNumOfComments;
    }

    public int getTotalNumOfDislikes() {
        return totalNumOfDislikes;
    }

    public int getTotalNumOfLikes() {
        return totalNumOfLikes;
    }

    public int getTotalNumOfRatedUsers() {
        return totalNumOfRatedUsers;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public double getTotalViewing() {
        return totalViewing;
    }

    public List<ObjectReviewJsonEntity> getComments() {
        return comments;
    }

    public Long getDbid() {
        return dbid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public String getAttache1() {
        return attache1;
    }

    public String getAttache2() {
        return attache2;
    }

    public String getAttache3() {
        return attache3;
    }

    public Boolean getIsPinned() {
        return isPinned;
    }

    public String getCode() {
        return code;
    }

    public String getSubDescription() {
        return subDescription;
    }

}
