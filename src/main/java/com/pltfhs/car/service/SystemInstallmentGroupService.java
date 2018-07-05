/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.SystemInstallment;
import com.pltfhs.car.repository.SystemInstallmentRepository;
import com.pltfhs.car.view.Item;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Client 1
 */
@Service
public class SystemInstallmentGroupService {

    @Autowired
    private SystemInstallmentRepository installmentRepository;

    public GeneralResponse addSytemInsallmentGroup(SystemInstallment installment) {
        SystemInstallment isDublicate = installmentRepository.findOne(Example.of(installment));
        if (isDublicate != null) {
            return new GeneralResponse(StatusCode.DATA_DUBLICATE);
        }
        if (installment.getIsDefault()) {
            installmentRepository.updateRecordIsDefault();
        }
        installment.setCreationDate(new Date());
        installment.setIsDeleted(Boolean.FALSE);
        SystemInstallment saved = installmentRepository.save(installment);
        return new GeneralResponse(StatusCode.OK, saved);
    }

    public GeneralResponse deleteSytemInsallmentGroup(int id) {
        SystemInstallment systemInstallmentDB = installmentRepository.findOne(id);
        if (systemInstallmentDB == null) {
            return new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        } else {
            systemInstallmentDB.setIsDeleted(Boolean.TRUE);
            installmentRepository.saveAndFlush(systemInstallmentDB);
            return new GeneralResponse(StatusCode.OK);
        }
    }

    public GeneralResponse updateSytemInsallmentGroup(SystemInstallment installment) {
        SystemInstallment systemInstallmentDB = installmentRepository.findOne(installment.getSystemInstallmentId());
        if (systemInstallmentDB == null) {
            return new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        }
        if (installment.getIsDefault()) {
            //if update record with default true change other to false and set new update only true
            installmentRepository.updateRecordIsDefault();
        } else {
            if (systemInstallmentDB.getIsDefault()) {
                //the records can't be without defalut value
                return new GeneralResponse(StatusCode.DEFAULT_VALUE_ERROR);
            }
        }
        SystemInstallment saved = installmentRepository.saveAndFlush(installment);
        return new GeneralResponse(StatusCode.OK, saved);
    }

    public GeneralResponse findAllSytemInsallmentGroup(int page, int noOfRows, Byte language) {
        List<SystemInstallment> findAll = installmentRepository.findByIsDeletedIsFalse(new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "creationDate"));
        GeneralResponse response;
        if (language == null) {
            response = new GeneralResponse(StatusCode.OK, findAll);
        } else {
            List<Item> result = new ArrayList(findAll.size());
            findAll.forEach(sig -> {
                result.add(new Item(sig, language));
            });
            response = new GeneralResponse(StatusCode.OK, result, installmentRepository.countByIsDeletedIsFalse());
        }
        return response;
    }
}
