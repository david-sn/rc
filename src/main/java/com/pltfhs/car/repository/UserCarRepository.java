/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.repository;

import com.pltfhs.car.entity.UserCar;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface UserCarRepository extends JpaRepository<UserCar, Integer> {

    UserCar findByUserCarIdAndUserIdAndIsDeletedIsFalse(int userCarId, int userId);

    List<UserCar> findByUserIdAndIsDeletedIsFalse(int userId, Pageable pageable);

    long countByUserIdAndIsDeletedIsFalse(int userId);
}
