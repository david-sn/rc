/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.repository;

import com.pltfhs.car.entity.InsurancePercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface InsurancePercentageRepository extends JpaRepository<InsurancePercentage, Integer> {

    InsurancePercentage findByCarBrandId_BrandIdAndInsuranceId(Integer brandId, Integer insuranceId);
}
