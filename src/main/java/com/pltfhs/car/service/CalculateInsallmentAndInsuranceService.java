/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import com.pltfhs.car.entity.InsurancePercentage;
import com.pltfhs.car.entity.SystemInstallment;
import com.pltfhs.car.repository.InsurancePercentageRepository;
import com.pltfhs.car.repository.SystemInstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Client 1
 */
@Service
public class CalculateInsallmentAndInsuranceService {

    @Autowired
    private SystemInstallmentRepository installmentRepository;
    @Autowired
    private InsurancePercentageRepository insurancePercentageRepository;

    public double calculateInstallmentPerMonth(double downPaymentPercentage, double noOfYearsInsatallment, double totalCarPrice, int sytemInsallmentGroupId) {

        SystemInstallment systemInstallmentDB = installmentRepository.findOne(sytemInsallmentGroupId);

        double totalPayDownPrice = (totalCarPrice * downPaymentPercentage) / 100;
        //calcualte the rest price after pay down paid
        double theRestOfCarPrice = totalCarPrice - totalPayDownPrice;
        //calculate profit per a year
        double profitValuePerOneYear = (theRestOfCarPrice * systemInstallmentDB.getInstallmentPercentage()) / 100;
        //calculate profit for all years
        double profitForAllYears = profitValuePerOneYear * noOfYearsInsatallment;
        //calculate price of car with profit price
        double totalPriceWithProfit = theRestOfCarPrice + profitForAllYears;
        //calcualte installement per month
        double installmentPerMonth = totalPriceWithProfit / (12 * noOfYearsInsatallment);

        return installmentPerMonth;
    }

    public double customCalculateInstallmentPerMonth(double downPaymentPercentage, double noOfYearsInsatallment, double totalCarPrice, double customIneresetPercentage) {

        double totalPayDownPrice = (totalCarPrice * downPaymentPercentage) / 100;
        //calcualte the rest price after pay down paid
        double theRestOfCarPrice = totalCarPrice - totalPayDownPrice;
        //calculate profit per a year
        double profitValuePerOneYear = (theRestOfCarPrice * customIneresetPercentage) / 100;
        //calculate profit for all years
        double profitForAllYears = profitValuePerOneYear * noOfYearsInsatallment;
        //calculate price of car with profit price
        double totalPriceWithProfit = theRestOfCarPrice + profitForAllYears;
        //calcualte installement per month
        double installmentPerMonth = totalPriceWithProfit / (12 * noOfYearsInsatallment);

        return installmentPerMonth;
    }

    public double calculateInsurance(double carPrice, int insuraceId, int carBrandId, double anotherFees) {
        InsurancePercentage insurancePercentageDB = insurancePercentageRepository.findByCarBrandId_BrandIdAndInsuranceId(carBrandId, insuraceId);
        double insuranceAmount = (carPrice * anotherFees * insurancePercentageDB.getInsuraceValue()) / 100;
        return insuranceAmount;
    }

}
