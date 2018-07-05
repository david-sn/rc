package com.pltfhs.car.repository;

import com.pltfhs.car.entity.CarModels;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelsRepository extends JpaRepository<CarModels, Long> {

    List<CarModels> findByIsDeletedIsFalseAndTypeId(Pageable pageable, Short typeId);

    CarModels findByModelIdAndIsDeletedIsFalse(Long modelId);

    List<CarModels> findByModelNameArContainingOrModelNameEnContainingAndIsDeletedIsFalseAndTypeId(String modelAr, String modelEn, Short typeId);

    List<CarModels> findBycarBrands_brandIdAndIsDeletedIsFalseAndTypeId(Integer brandId, Short typeId);

    long countByIsDeletedIsFalseAndTypeId(Short typeId);
}
