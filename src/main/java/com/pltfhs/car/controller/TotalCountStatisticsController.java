/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.service.TotalCountStatisticsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
public class TotalCountStatisticsController {

    @Autowired
    private TotalCountStatisticsService totalCountStatisticsService;

    @RequestMapping(method = RequestMethod.GET, value = "/totalCount")
    public Map<String, Long> getAllCount() {
        return totalCountStatisticsService.getAllCount();
    }
}
