/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.repository;

import com.pltfhs.car.entity.LookupServiceType;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface LookupServiceTypeRepository extends JpaRepository<LookupServiceType, Integer> {

    List<LookupServiceType> findByIsDeletedIsFalse();

    List<LookupServiceType> findByIsDeletedIsFalse(Pageable pageable);

    List<LookupServiceType> findByIsDeletedIsFalseAndIsAvailiableIsTrue();

    Page<LookupServiceType> findByIsDeletedIsFalseAndIsAvailiableIsTrue(Pageable pageable);

    long countByIsDeletedIsFalse();
}
