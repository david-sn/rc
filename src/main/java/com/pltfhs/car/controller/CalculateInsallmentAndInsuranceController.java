/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.service.CalculateInsallmentAndInsuranceService;
import com.pltfhs.car.view.SingleKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
public class CalculateInsallmentAndInsuranceController {

    @Autowired
    private CalculateInsallmentAndInsuranceService insallmentAndInsuranceService;

    @RequestMapping("/calculateInstallmentPerMonth")
    public GeneralResponse calculateInstallmentPerMonth(@RequestBody GeneralParameter body) {

        double calculateInstallmentPerMonth = insallmentAndInsuranceService.calculateInstallmentPerMonth(
                body.getDownPaymentPercentage(),
                body.getNoOfYearsInstallment(),
                body.getTotalCarPrice(),
                body.getSystemInstallmentGroup()
        );

        SingleKeys keys = new SingleKeys();
        keys.setInstallmentPerMonth(calculateInstallmentPerMonth);
        return new GeneralResponse(StatusCode.OK, keys);
    }

    @RequestMapping("/customCalculateInstallmentPerMonth")
    public GeneralResponse customCalculateInstallmentPerMonth(@RequestBody GeneralParameter body) {

        double calculateInstallmentPerMonth = insallmentAndInsuranceService.customCalculateInstallmentPerMonth(
                body.getDownPaymentPercentage(),
                body.getNoOfYearsInstallment(),
                body.getTotalCarPrice(),
                body.getCustomIneresetPercentage()
        );

        SingleKeys keys = new SingleKeys();
        keys.setInstallmentPerMonth(calculateInstallmentPerMonth);
        return new GeneralResponse(StatusCode.OK, keys);
    }

    @RequestMapping("/calculateInsurance")
    public double calculateInsurance(@RequestBody GeneralParameter body) {
        return 0.0;
    }

}
