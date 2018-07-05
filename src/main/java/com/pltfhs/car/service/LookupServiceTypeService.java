/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.LookupServiceType;
import com.pltfhs.car.repository.LookupServiceTypeRepository;
import com.pltfhs.car.view.Item;
import com.pltfhs.car.view.LookupServiceTypeView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author Client 1
 */
@Service
public class LookupServiceTypeService {

    @Autowired
    private LookupServiceTypeRepository lookupServiceTypeRepository;

    public GeneralResponse<Item> addServiceType(LookupServiceType lookupServiceType) {
        GeneralResponse<Item> response;
        LookupServiceType isDublicated = lookupServiceTypeRepository.findOne(Example.of(lookupServiceType));
        if (isDublicated == null) {
            lookupServiceType.setIsDeleted(false);
            lookupServiceType.setIsAvailiable(true);
            LookupServiceType serviceTypeDB = lookupServiceTypeRepository.save(lookupServiceType);
            response = new GeneralResponse<>(StatusCode.OK, new Item(serviceTypeDB.getServiceId().longValue(), null));
        } else {
            response = new GeneralResponse<>(StatusCode.DATA_DUBLICATE);
        }
        return response;
    }

    public GeneralResponse updateServiceType(LookupServiceType lookupServiceType) {
        GeneralResponse response;
        LookupServiceType serviceTypeDB = lookupServiceTypeRepository.findOne(lookupServiceType.getServiceId());
        if (serviceTypeDB != null) {
            serviceTypeDB.setIsAvailiable(Boolean.TRUE);
            serviceTypeDB.setMaxRequestsPerDay(Integer.MAX_VALUE);
            serviceTypeDB.setServiceDuration(lookupServiceType.getServiceDuration());
            serviceTypeDB.setServiceMaxStartTime(lookupServiceType.getServiceMaxStartTime());
            serviceTypeDB.setServiceMinStartTime(lookupServiceType.getServiceMinStartTime());
            serviceTypeDB.setServiceTypeNameAr(lookupServiceType.getServiceTypeNameAr());
            serviceTypeDB.setServiceTypeNameEn(lookupServiceType.getServiceTypeNameEn());
            lookupServiceTypeRepository.saveAndFlush(serviceTypeDB);
            response = new GeneralResponse(StatusCode.OK);
        } else {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse deleteServiceType(int lookupServiceTypeId) {
        GeneralResponse response;
        LookupServiceType serviceTypeDB = lookupServiceTypeRepository.findOne(lookupServiceTypeId);
        if (serviceTypeDB != null) {
            serviceTypeDB.setIsDeleted(true);
            lookupServiceTypeRepository.saveAndFlush(serviceTypeDB);
            response = new GeneralResponse(StatusCode.OK);
        } else {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse findAllService(byte language, Integer page, Integer noOfRows) {
        GeneralResponse response;
        Page<LookupServiceType> result = null;
        if (page != null && noOfRows != null) {
            result = lookupServiceTypeRepository.findByIsDeletedIsFalseAndIsAvailiableIsTrue(new PageRequest(page - 1, noOfRows));
        }
        if (result == null || !result.hasContent()) {
            response = new GeneralResponse(StatusCode.ZERO_RESULTS);
        } else {
            List<LookupServiceTypeView> data = new ArrayList();
            result.forEach((lookupServiceType) -> {
                data.add(new LookupServiceTypeView(lookupServiceType, language));
            });
            response = new GeneralResponse(StatusCode.OK, data, result.getTotalElements());
        }
        return response;
    }

    public GeneralResponse findAllServiceAdmin(byte language, Integer page, Integer noOfRows) {
        GeneralResponse response;
        List<LookupServiceType> result = null;
        if (page != null && noOfRows != null) {
            result = lookupServiceTypeRepository.findByIsDeletedIsFalse(new PageRequest(page - 1, noOfRows));
        }
        if (result == null || result.isEmpty()) {
            response = new GeneralResponse(StatusCode.ZERO_RESULTS);
        } else {
            response = new GeneralResponse(StatusCode.OK, result, lookupServiceTypeRepository.countByIsDeletedIsFalse());
        }
        return response;
    }
}
