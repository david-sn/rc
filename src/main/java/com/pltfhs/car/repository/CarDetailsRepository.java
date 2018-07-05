package com.pltfhs.car.repository;

import com.pltfhs.car.entity.CarDetails;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailsRepository extends JpaRepository<CarDetails, Long> {

    List<CarDetails> findByIsDeletedIsFalseAndCarModel_ModelId(Long modelId, Pageable pageable);

    long countByIsDeletedIsFalseAndCarModel_ModelId(Long modelId);

    List<CarDetails> findByIsDeletedIsFalse(Pageable pageable);

    long countByIsDeletedIsFalse();

    CarDetails findByIsDeletedIsFalseAndCarId(Long carId);

}
