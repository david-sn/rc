package com.pltfhs.car.repository;

import com.pltfhs.car.entity.CarMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMediaRepository extends JpaRepository<CarMedia, Long> {

}
