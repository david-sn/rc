package com.pltfhs.car.repository;

import com.pltfhs.car.entity.CarBrands;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandsRepository extends JpaRepository<CarBrands, Integer> {

    List<CarBrands> findByBrandNameArOrBrandNameEnAndIsDeletedIsFalseAndTypeId(String brandNameAr, String brandNameEn, Short typeId);

    long countByBrandNameArOrBrandNameEnAndIsDeletedIsFalseAndTypeId(String brandNameAr, String brandNameEn, Short typeId);

    CarBrands findByBrandIdAndIsDeletedIsFalse(Integer brandId);

    List<CarBrands> findByIsDeletedIsFalseAndTypeId(Short typeId, Pageable pageable);

    long countByIsDeletedIsFalseAndTypeId(Short typeId);

}
