/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import com.pltfhs.car.common.EObjectType;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.Comments;
import com.pltfhs.car.entity.EntityLikes;
import com.pltfhs.car.entity.EntityRate;
import com.pltfhs.car.entity.ObjectViewings;
import com.pltfhs.car.entity.Users;
import com.pltfhs.car.repository.CommentsRepository;
import com.pltfhs.car.repository.EntityLikesRepository;
import com.pltfhs.car.repository.EntityRateRepository;
import com.pltfhs.car.repository.EntityTypeRepository;
import com.pltfhs.car.repository.ObjectViewingRepository;
import com.pltfhs.car.repository.UsersRepository;
import com.pltfhs.car.view.Item;
import com.pltfhs.car.view.SingleKeys;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Client 1
 */
@Service
public class EntityInteractionService {

    @Autowired
    private EntityTypeRepository entityTypeRepository;
    @Autowired
    private EntityRateRepository entityRateRepository;
    @Autowired
    private EntityLikesRepository entityLikesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private ObjectViewingRepository objectViewingRepository;
    @Autowired
    private AfterInteractObject aio;

    public GeneralResponse<SingleKeys> like_disLikeEntity(String email, long entityId, EObjectType entityType, byte likeStatus) {
        //like status id  0=remove like/dislike,  1=like, 2=dislike
        Users userDB = usersRepository.findByEmail(email);
        EntityLikes userLikedThisObject = entityLikesRepository.findByEntityIdAndUserId(entityId, userDB.getUserId());
        if (userLikedThisObject != null) {
            if (likeStatus == 1 || likeStatus == 2) {
                //already exsit just do same  like or dislike status
                userLikedThisObject.setLikeStatusId(likeStatus);
                entityLikesRepository.save(userLikedThisObject);
            } else {
                //remove like/dislike
                entityLikesRepository.delete(userLikedThisObject);
            }
        } else {
            //add liked/dislike
            Users usesr = usersRepository.findByEmail(email);
            userLikedThisObject = new EntityLikes(new Date(), usesr.getUserId(), entityId);
            userLikedThisObject.setLikeStatusId(likeStatus);
            userLikedThisObject.setEntityType(entityTypeRepository.findOne(entityType.getObjectTypeId()));
            entityLikesRepository.saveAndFlush(userLikedThisObject);
        }
        SingleKeys result = new SingleKeys(userLikedThisObject,
                countByLikeStatus(entityId, entityType.getObjectTypeId(), (byte) 1), countByLikeStatus(entityId, entityType.getObjectTypeId(), (byte) 2)
        );
        GeneralResponse response = new GeneralResponse(StatusCode.OK, result);

        aio.updateObjectAfterLikeInteraction(entityType, entityId, result.getTotalLikes(), result.getTotalDislikes());

        return response;
    }

    private long countByLikeStatus(long entityId, int entityType, byte likeStatus) {
        return entityLikesRepository.countByEntityIdAndEntityType_TypeIdAndLikeStatusId(entityId, entityType, likeStatus);
    }

    public GeneralResponse rateEntity(String userEmail, long entityId, EObjectType entityType, int ratingValue) {
        Users userDB = usersRepository.findByEmail(userEmail);
        EntityRate entityRateByUser = entityRateRepository.findByEntityIdAndEntityType_TypeIdAndUserId(BigDecimal.valueOf(entityId), entityType.getObjectTypeId(), BigInteger.valueOf(userDB.getUserId()));
        if (entityRateByUser == null) {
            entityRateByUser = new EntityRate();
            entityRateByUser.setEntityId(BigDecimal.valueOf(entityId));
            entityRateByUser.setEntityType(entityTypeRepository.findOne(entityType.getObjectTypeId()));
            entityRateByUser.setUserId(BigInteger.valueOf(userDB.getUserId()));
            entityRateByUser.setRateDate(new Date());
            entityRateByUser.setRateNumber(ratingValue);
        } else {
            entityRateByUser.setRateNumber(ratingValue);
        }
        entityRateRepository.save(entityRateByUser);
        SingleKeys keys = new SingleKeys();
        keys.setAverageRate(getAverageRating(entityId, entityType.getObjectTypeId()));
        keys.setTotalRate(getTotalRating(entityId, entityType.getObjectTypeId()));

        GeneralResponse response = new GeneralResponse(StatusCode.OK, keys);

        aio.updateObjectAfterRatingInteraction(entityType, entityId, keys.getAverageRate(), keys.getTotalRate());

        return response;
    }

    private long getTotalRating(long entityId, int entityType) {
        return entityRateRepository.countByEntityIdAndEntityType_TypeId(BigDecimal.valueOf(entityId), entityType);
    }

    private Double getAverageRating(long entityId, int entityType) {
        return entityRateRepository.getAverageRatingByObjectId(BigDecimal.valueOf(entityId), entityType);
    }

    public GeneralResponse<Item> addCommentEntity(String userEmail, long entityId, EObjectType entityType, String comment) {
        GeneralResponse<Item> response = null;
        Users userDB = usersRepository.findByEmail(userEmail);
        if (userDB != null) {
            Comments comments = new Comments(null, userDB.getUserId(), entityId, comment, new Date());
            comments.setEntityType(entityTypeRepository.findOne(entityType.getObjectTypeId()));
            Comments saveedComment = commentsRepository.save(comments);
            //item has id of inserted comment and value of it
            Item keys = new Item(saveedComment);
            response = new GeneralResponse(StatusCode.OK, keys);

            aio.updateObjectAfterReviewInteraction(entityType, entityId);

        } else {
            response = new GeneralResponse(StatusCode.USER_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse<Item> editCommentEntity(String userEmail, String comment, long id) {
        GeneralResponse<Item> response = null;
        Users userDB = usersRepository.findByEmail(userEmail);
        if (userDB != null) {
            Comments commentDB = commentsRepository.findByUserIdAndCommentId(userDB.getUserId(), id);
            if (commentDB != null) {
                commentDB.setCommentText(comment);
                Item keys = new Item(commentDB);
                response = new GeneralResponse(StatusCode.OK, keys);
                commentsRepository.saveAndFlush(commentDB);
            } else {
                response = new GeneralResponse(StatusCode.COMMENT_NOT_FOUND);
            }
        } else {
            response = new GeneralResponse(StatusCode.USER_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse<Item> deleteCommentEntity(String userEmail, long id) {
        GeneralResponse<Item> response = null;
        Users userDB = usersRepository.findByEmail(userEmail);
        if (userDB != null) {
            Comments commentDB = commentsRepository.findByUserIdAndCommentId(userDB.getUserId(), id);
            if (commentDB != null) {
                commentsRepository.delete(commentDB);
                response = new GeneralResponse(StatusCode.OK);
            } else {
                response = new GeneralResponse(StatusCode.COMMENT_NOT_FOUND);
            }
        } else {
            response = new GeneralResponse(StatusCode.USER_NOT_FOUND);
        }
        return response;
    }

    public ObjectViewings addObjectView(Long objectId, EObjectType objectTypeId, String emailAddress) {
        Long userId = usersRepository.findByEmail(emailAddress).getUserId();
        List<ObjectViewings> objectViewingsList = objectViewingRepository.findByObjectIdAndObjectTypeIdAndActingUserId(objectId.toString(), Short.valueOf(objectTypeId.getObjectTypeId() + ""), userId);
        if (objectViewingsList.isEmpty()) {
            ObjectViewings objectViewings = new ObjectViewings(objectId.toString(), new Date(), objectTypeId.getObjectTypeId(), userId);

            aio.updateObjectAfterViewInteraction(objectTypeId, objectId, 1 + getTotalNumberOfViewing(objectId.toString(), objectTypeId.getObjectTypeId()));

            return objectViewingRepository.save(objectViewings);
        } else {
            Calendar viewingCalendar = Calendar.getInstance();
            viewingCalendar.setTime(objectViewingsList.get(objectViewingsList.size() - 1).getViewingDateTime());
            viewingCalendar.add(Calendar.DATE, 1);

            if (new Date().after(viewingCalendar.getTime())) {
                ObjectViewings objectViewings = new ObjectViewings(objectId.toString(), new Timestamp(new Date().getTime()), objectTypeId.getObjectTypeId(), userId);
                aio.updateObjectAfterViewInteraction(objectTypeId, 1 + objectId, getTotalNumberOfViewing(objectId.toString(), objectTypeId.getObjectTypeId()));
                return objectViewingRepository.save(objectViewings);
            } else {
                return objectViewingsList.get(objectViewingsList.size() - 1);
            }
        }

    }

    public int getTotalNumberOfViewing(String objectId, Integer objectTypeId) {
        return objectViewingRepository.countByObjectIdAndObjectTypeId(objectId, objectTypeId.shortValue());
    }
}
