package com.pltfhs.car.view;

import com.pltfhs.car.entity.Comments;
import com.pltfhs.car.entity.EntityLikes;
import com.pltfhs.car.repository.CommentsRepository;
import com.pltfhs.car.repository.EntityLikesRepository;
import com.pltfhs.car.repository.UsersRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectReviewJsonEntity {

    private static EntityLikesRepository likeRepository;
    private static UsersRepository usersRepository;
    private static CommentsRepository commentsRepository;

    @Autowired
    public void setCommentsRepository(CommentsRepository commentsRepository) {
        ObjectReviewJsonEntity.commentsRepository = commentsRepository;
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        ObjectReviewJsonEntity.usersRepository = usersRepository;
    }

    @Autowired
    public void setLikeRepository(EntityLikesRepository likeRepository) {
        ObjectReviewJsonEntity.likeRepository = likeRepository;
    }

    private Long reviewId;
    private String reviewText;
    private long lastModifiedDate;
    private String lastModifiedDateAgo;
    private NOwner owner;

    public ObjectReviewJsonEntity() {
    }

    private ObjectReviewJsonEntity(Comments comments, byte language) {
        this.reviewId = comments.getCommentId();
        this.reviewText = comments.getCommentText();

        this.owner = new NOwner(usersRepository.findOne(comments.getUserId()));
        lastModifiedDate = comments.getCommentDate().getTime();

        if (language == 1) {
            lastModifiedDateAgo = DateUtilities.getArTimeAgo(new Date(lastModifiedDate));
        } else {
            lastModifiedDateAgo = DateUtilities.getTimeAgo(new Date(lastModifiedDate));
        }

    }

    public static List<ObjectReviewJsonEntity> getObjectComments(int entityId, int entityTypeId, byte language) {
        List<ObjectReviewJsonEntity> result = new ArrayList<>();
        commentsRepository.findByEntityIdAndEntityType_typeId(entityId, entityTypeId).forEach(c -> {
            result.add(new ObjectReviewJsonEntity(c, language));
        });
        return result;
    }

    public static short getLikeStatus(Long objectId, Long userId) {
        if (userId==null) {
            return 0;
        }
        EntityLikes isUserLikedThisReview = likeRepository.findByEntityIdAndUserId(objectId, userId);
        if (isUserLikedThisReview != null) {
            Short myLikeStatus = isUserLikedThisReview.getLikeStatusId();
            return myLikeStatus;
        }
        return 0;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedDateAgo() {
        return lastModifiedDateAgo;
    }

    public void setLastModifiedDateAgo(String lastModifiedDateAgo) {
        this.lastModifiedDateAgo = lastModifiedDateAgo;
    }

    public NOwner getOwner() {
        return owner;
    }

    public void setOwner(NOwner owner) {
        this.owner = owner;
    }

    //    public Double getTotalRating() {
    //        return totalRating;
    //    }
    //
    //    public void setTotalRating(Double totalRating) {
    //        this.totalRating = totalRating;
    //    }
}
