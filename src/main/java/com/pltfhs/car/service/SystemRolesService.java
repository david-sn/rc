package com.pltfhs.car.service;

import com.pltfhs.car.repository.SystemRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemRolesService {

    @Autowired
    private SystemRolesRepository systemRolesRepository;
}
