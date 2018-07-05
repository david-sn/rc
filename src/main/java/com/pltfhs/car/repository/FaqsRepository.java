/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.repository;

import com.pltfhs.car.entity.Faqs;
import java.math.BigInteger;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface FaqsRepository extends JpaRepository<Faqs, Long> {

    @Query("from Faqs f where "
            + "f.faqTitle=:faqTitle and "
            + "f.faqDescription=:faqDescription and "
            + "f.userId=:userId and "
            + "f.creationDate between :endDate and NOW()")
    Faqs getIsFAqDublicate(@Param("faqTitle") String faqTitle, @Param("faqDescription") String faqDescription, @Param("userId") BigInteger userId, @Param("endDate") Date endDate);
}
