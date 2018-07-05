/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import com.pltfhs.car.common.EObjectType;
import com.pltfhs.car.entity.CarDetails;
import com.pltfhs.car.entity.Comments;
import com.pltfhs.car.entity.Faqs;
import com.pltfhs.car.entity.NewsPostBlog;
import com.pltfhs.car.repository.CarDetailsRepository;
import com.pltfhs.car.repository.CommentsRepository;
import com.pltfhs.car.repository.FaqsRepository;
import com.pltfhs.car.repository.NewsPosBlogtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 *
 * @author Client 1
 */
@EnableAsync
@Async
@Component
public class AfterInteractObject {

    @Autowired
    private CarDetailsRepository carDetailsRepository;
    @Autowired
    private FaqsRepository faqsRepository;
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private NewsPosBlogtRepository newsPosBlogtRepository;

    public void updateObjectAfterLikeInteraction(EObjectType objectTypes, long objectId, Long noOflike, Long noOfDislike) {
        switch (objectTypes.toString()) {
            case "CAR": {
                CarDetails carDB = carDetailsRepository.findOne(objectId);
                carDB.setTotalNumOfLikes(noOflike.intValue());
                carDB.setTotalNumOfDislikes(noOfDislike.intValue());
                carDetailsRepository.saveAndFlush(carDB);
            }
            break;

            case "FAQ": {
                Faqs faqDB = faqsRepository.findOne(objectId);
                faqDB.setTotalNumOfLikes(noOflike.intValue());
                faqDB.setTotalNumOfDislikes(noOfDislike.intValue());
                faqsRepository.saveAndFlush(faqDB);
            }
            break;
            case "COMMENT": {
                Comments commentDB = commentsRepository.findOne(objectId);
                commentDB.setNoOfLikes(noOflike);
                commentDB.setNoOfDislikes(noOfDislike);
                commentsRepository.saveAndFlush(commentDB);
            }
            break;
            case "POST": {
                NewsPostBlog postDB = newsPosBlogtRepository.findOne(objectId);
                postDB.setTotalNumOfLikes(noOflike.intValue());
                postDB.setTotalNumOfDislikes(noOfDislike.intValue());
                newsPosBlogtRepository.saveAndFlush(postDB);
            }
            break;
            case "BLOG": {
                NewsPostBlog blogDB = newsPosBlogtRepository.findOne(objectId);
                blogDB.setTotalNumOfLikes(noOflike.intValue());
                blogDB.setTotalNumOfDislikes(noOfDislike.intValue());
                newsPosBlogtRepository.saveAndFlush(blogDB);
            }
            break;
            case "NEWS": {
                NewsPostBlog newsDB = newsPosBlogtRepository.findOne(objectId);
                newsDB.setTotalNumOfLikes(noOflike.intValue());
                newsDB.setTotalNumOfDislikes(noOfDislike.intValue());
                newsPosBlogtRepository.saveAndFlush(newsDB);
            }
            break;
            default:
                throw new AssertionError();
        }
    }

    public void updateObjectAfterReviewInteraction(EObjectType objectTypes, long objectId) {
        switch (objectTypes.toString()) {
            case "CAR": {
                CarDetails carDB = carDetailsRepository.findOne(objectId);
                carDB.setTotalNumOfComments(1 + carDB.getTotalNumOfComments());
                carDetailsRepository.saveAndFlush(carDB);
            }
            break;
            case "FAQ": {
                Faqs faqDB = faqsRepository.findOne(objectId);
                faqDB.setTotalNumOfComments(1 + faqDB.getTotalNumOfComments());
            }
            break;
            case "COMMENT": {

            }
            break;
            case "POST": {
                NewsPostBlog postDB = newsPosBlogtRepository.findOne(objectId);
                postDB.setTotalNumOfComments(1 + postDB.getTotalNumOfComments());
                newsPosBlogtRepository.saveAndFlush(postDB);
            }
            break;
            case "BLOG": {
                NewsPostBlog blogDB = newsPosBlogtRepository.findOne(objectId);
                blogDB.setTotalNumOfComments(1 + blogDB.getTotalNumOfComments());
                newsPosBlogtRepository.saveAndFlush(blogDB);
            }
            break;
            case "NEWS": {
                NewsPostBlog newsDB = newsPosBlogtRepository.findOne(objectId);
                newsDB.setTotalNumOfComments(1 + newsDB.getTotalNumOfComments());
                newsPosBlogtRepository.saveAndFlush(newsDB);
            }
            break;
            default:
                throw new AssertionError();
        }
    }

    public void updateObjectAfterViewInteraction(EObjectType objectTypes, long objectId, int totalViewCount) {
        switch (objectTypes.toString()) {
            case "CAR": {
                CarDetails carDB = carDetailsRepository.findOne(objectId);
                carDB.setTotalViewing(totalViewCount);
            }
            break;
            case "FAQ": {
                Faqs faqDB = faqsRepository.findOne(objectId);
                faqDB.setTotalViewing(totalViewCount);
                faqsRepository.saveAndFlush(faqDB);
            }
            break;
            case "COMMENT": {

            }
            break;
            case "POST": {
                NewsPostBlog postDB = newsPosBlogtRepository.findOne(objectId);
                postDB.setTotalViewing(totalViewCount);
                newsPosBlogtRepository.saveAndFlush(postDB);
            }
            break;
            case "BLOG": {
                NewsPostBlog blogDB = newsPosBlogtRepository.findOne(objectId);
                blogDB.setTotalViewing(totalViewCount);
                newsPosBlogtRepository.saveAndFlush(blogDB);
            }
            break;
            case "NEWS": {
                NewsPostBlog newsDB = newsPosBlogtRepository.findOne(objectId);
                newsDB.setTotalViewing(totalViewCount);
                newsPosBlogtRepository.saveAndFlush(newsDB);
            }
            break;
            default:
                throw new AssertionError();
        }
    }

    public void updateObjectAfterRatingInteraction(EObjectType objectTypes, long objectId, double averageRating, Long totalRating) {
        switch (objectTypes.toString()) {
            case "CAR": {
                CarDetails carDB = carDetailsRepository.findOne(objectId);
                carDB.setAverageRating(averageRating);
                carDB.setTotalNumOfRatedUsers(totalRating.intValue());
                carDetailsRepository.saveAndFlush(carDB);
            }
            break;
            case "FAQ": {
                Faqs faqDB = faqsRepository.findOne(objectId);
                faqDB.setAverageRating(averageRating);
                faqDB.setTotalNumOfRatedUsers(totalRating.intValue());
                faqsRepository.saveAndFlush(faqDB);
            }
            break;
            case "COMMENT": {

            }
            break;
            case "POST": {
                NewsPostBlog postDB = newsPosBlogtRepository.findOne(objectId);
                postDB.setAverageRating(averageRating);
                postDB.setTotalNumOfRatedUsers(totalRating.intValue());
                newsPosBlogtRepository.saveAndFlush(postDB);
            }
            break;
            case "BLOG": {
                NewsPostBlog blogDB = newsPosBlogtRepository.findOne(objectId);
                blogDB.setAverageRating(averageRating);
                blogDB.setTotalNumOfRatedUsers(totalRating.intValue());
                newsPosBlogtRepository.saveAndFlush(blogDB);
            }
            break;
            case "NEWS": {
                NewsPostBlog newsDB = newsPosBlogtRepository.findOne(objectId);
                newsDB.setAverageRating(averageRating);
                newsDB.setTotalNumOfRatedUsers(totalRating.intValue());
                newsPosBlogtRepository.saveAndFlush(newsDB);
            }
            break;
            default:
                throw new AssertionError();
        }
    }

}
