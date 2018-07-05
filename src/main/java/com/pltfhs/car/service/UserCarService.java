/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.CarBrands;
import com.pltfhs.car.entity.CarModels;
import com.pltfhs.car.entity.UserCar;
import com.pltfhs.car.entity.Users;
import com.pltfhs.car.repository.CarBrandsRepository;
import com.pltfhs.car.repository.CarModelsRepository;
import com.pltfhs.car.repository.UserCarRepository;
import com.pltfhs.car.repository.UsersRepository;
import com.pltfhs.car.view.Item;
import com.pltfhs.car.view.UserCarView;

/**
 *
 * @author Client 1
 */
@Service
public class UserCarService {

    @Autowired
    private UserCarRepository userCarRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CarBrandsRepository carBrandRepo;
    @Autowired
    private CarModelsRepository carModelRepo;

    public GeneralResponse addUserCar(String emailAddress, Integer carBrandId, Integer carModelId, String modelYear, String chaseNumber, String nameInLicense, String engineCapacity, String kiloMeter, String carNumber, String note) {

        GeneralResponse response;
        Users userDB = usersRepository.findByEmail(emailAddress);
        UserCar userCar = new UserCar(
                carBrandId, carModelId, modelYear, chaseNumber, nameInLicense,
                engineCapacity, kiloMeter, note, userDB.getUserId().intValue(), carNumber);

        UserCar isDublicate = userCarRepository.findOne(Example.of(userCar));
        if (isDublicate == null) {
            userCar.setCreationDate(new Date());
            userCar.setIsDeleted(Boolean.FALSE);
            UserCar savedCar = userCarRepository.save(userCar);
            response = new GeneralResponse(StatusCode.OK, new Item(savedCar.getUserCarId().longValue(), null));
        } else {
            //error dublication
            response = new GeneralResponse(StatusCode.DATA_DUBLICATE);
        }
        return response;
    }

    public GeneralResponse deleteUserCar(String emailAddress, int userCarId) {
        GeneralResponse response;
        Users userDB = usersRepository.findByEmail(emailAddress);
        UserCar userCarFB = userCarRepository.findByUserCarIdAndUserIdAndIsDeletedIsFalse(userCarId, userDB.getUserId().intValue());
        if (userCarFB == null) {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        } else {
            userCarFB.setIsDeleted(Boolean.TRUE);
            userCarRepository.saveAndFlush(userCarFB);
            response = new GeneralResponse(StatusCode.OK);
        }
        return response;
    }

    public GeneralResponse updateUserCar(String emailAddress, UserCar userCar) {
        GeneralResponse response;
        Users userDB = usersRepository.findByEmail(emailAddress);
        UserCar userCarToUpdate = userCarRepository.findByUserCarIdAndUserIdAndIsDeletedIsFalse(userCar.getUserCarId(), userDB.getUserId().intValue());

        if (userCarToUpdate == null) {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        } else {
            userCarToUpdate.setCarBrandId(userCar.getCarBrandId());
            userCarToUpdate.setCarModelId(userCar.getCarModelId());
            userCarToUpdate.setCarNumber(userCar.getCarNumber());
            userCarToUpdate.setChaseNumber(userCar.getChaseNumber());
            userCarToUpdate.setEngineCapacity(userCar.getEngineCapacity());
            userCarToUpdate.setKiloMeter(userCar.getKiloMeter());
            userCarToUpdate.setNameInLicenseNumber(userCar.getNameInLicenseNumber());
            userCarToUpdate.setModelYear(userCar.getModelYear());
            userCarToUpdate.setNote(userCar.getNote());
            userCarRepository.saveAndFlush(userCarToUpdate);
            response = new GeneralResponse(StatusCode.OK);
        }
        return response;
    }

    public GeneralResponse getUserCarById(String emailAddress, int userCarId, Byte languag) {
        GeneralResponse response;
        Users userDB = usersRepository.findByEmail(emailAddress);
        UserCar userCarDB = userCarRepository.findByUserCarIdAndUserIdAndIsDeletedIsFalse(userCarId, userDB.getUserId().intValue());
        if (userCarDB == null) {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        } else {
        	CarBrands brand = carBrandRepo.findOne(userCarDB.getCarBrandId().intValue());
        	CarModels model = carModelRepo.findOne(userCarDB.getCarModelId().longValue());
        	UserCarView rslt = new UserCarView(userCarDB,brand,model,languag);
            response = new GeneralResponse(StatusCode.OK, rslt);
        }
        return response;
    }

    public GeneralResponse getAllUserCar(String emailAddress, int page, int noOfRows, Byte languag) {
        Users userDB = usersRepository.findByEmail(emailAddress);
        List<UserCar> result = userCarRepository.findByUserIdAndIsDeletedIsFalse(userDB.getUserId().intValue(), new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "creationDate"));
        List<UserCarView>  rslts = new ArrayList<>();
        for(UserCar car : result) {
        	CarBrands brand = carBrandRepo.findOne(car.getCarBrandId().intValue());
        	CarModels model = carModelRepo.findOne(car.getCarModelId().longValue());
        	rslts.add( new UserCarView(car,brand,model, languag));
        }
        
        return new GeneralResponse(StatusCode.OK, rslts, userCarRepository.countByUserIdAndIsDeletedIsFalse(userDB.getUserId().intValue()));
    }

}
