package com.pltfhs.car.repository;

import com.pltfhs.car.entity.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityTypeRepository extends JpaRepository<EntityType, Integer> {

}
