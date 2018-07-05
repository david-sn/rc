/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.repository;

import com.pltfhs.car.entity.NewsPostBlog;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Client 1
 */
@Repository
public interface NewsPosBlogtRepository extends JpaRepository<NewsPostBlog, Long> {

    NewsPostBlog findByDbidAndIsDeletedIsFalse(Long dbid);

    List<NewsPostBlog> findByTypeIdAndIsDeletedIsFalse(short typeId, Pageable pageable);

    List<NewsPostBlog> findByTypeIdAndBlogIdAndIsDeletedIsFalse(short typeId, Integer blogId, Pageable pageable);

    NewsPostBlog findByDbidAndTypeIdAndIsDeletedIsFalse(Long dbid, short typeId);

    long countByIsDeletedIsFalseAndTypeId(short typeId);
}
