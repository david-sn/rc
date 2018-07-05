package com.pltfhs.car.service;

import com.pltfhs.car.repository.CarMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarMediaService {

    @Autowired
    private CarMediaRepository carMediaRepository;
}
