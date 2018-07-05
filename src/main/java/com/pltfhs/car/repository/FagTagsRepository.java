/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.repository;

import com.pltfhs.car.entity.FaqTags;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface FagTagsRepository extends JpaRepository<FaqTags, Integer> {

    @Modifying
    void deleteByTagValue(String tagValue);

    public List<FaqTags> findByFaqId(BigInteger id);
}
