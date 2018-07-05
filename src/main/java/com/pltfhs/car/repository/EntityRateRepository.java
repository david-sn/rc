package com.pltfhs.car.repository;

import com.pltfhs.car.entity.EntityRate;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRateRepository extends JpaRepository<EntityRate, Long> {

    EntityRate findByEntityIdAndEntityType_TypeIdAndUserId(BigDecimal entityId, Integer typeId, BigInteger userId);

    long countByEntityIdAndEntityType_TypeId(BigDecimal entityId, Integer typeId);

    @Query("SELECT avg (rating.rateNumber) FROM EntityRate rating WHERE  rating.entityId = :entityId AND rating.entityType.typeId = :entityType ")
    public Double getAverageRatingByObjectId(@Param(value = "entityId") BigDecimal entityId, @Param(value = "entityType") Integer entityType);

}
