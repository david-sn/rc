package com.pltfhs.car.repository;

import com.pltfhs.car.entity.ObjectViewings;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectViewingRepository extends JpaRepository<ObjectViewings, Long> {

    public List<ObjectViewings> findByObjectIdAndObjectTypeIdAndActingUserId(String objectId, Short objectType, Long userId);

    public int countByObjectIdAndObjectTypeId(String objectId, Short objectType);

}
