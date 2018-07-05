package com.pltfhs.car.repository;

import com.pltfhs.car.entity.EntityLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityLikesRepository extends JpaRepository<EntityLikes, Long> {

    public EntityLikes findByEntityIdAndUserId(long entityId, long userId);

    public long countByEntityIdAndEntityType_TypeIdAndLikeStatusId(long entityId, Integer entityType, short likeStatus);

}
