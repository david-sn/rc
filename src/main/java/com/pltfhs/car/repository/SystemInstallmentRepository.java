/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.repository;

import com.pltfhs.car.entity.SystemInstallment;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface SystemInstallmentRepository extends JpaRepository<SystemInstallment, Integer> {

    @Modifying//suport DML
    @Transactional
    @Query("update SystemInstallment s set s.isDefault=false")
    void updateRecordIsDefault();

    List<SystemInstallment> findByIsDeletedIsFalse(Pageable pageable);
    
    long countByIsDeletedIsFalse();
}
