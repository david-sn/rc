package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.CarDetails;
import com.pltfhs.car.entity.CarModels;
import com.pltfhs.car.entity.Users;
import com.pltfhs.car.repository.CarDetailsRepository;
import com.pltfhs.car.repository.CarImageRepository;
import com.pltfhs.car.repository.UsersRepository;
import com.pltfhs.car.security.JWTFilter;
import com.pltfhs.car.util.MultimediaUtil;
import com.pltfhs.car.view.CarDetailTemplate;
import com.pltfhs.car.view.Item;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CarDetailsService {

    @Autowired
    private CarDetailsRepository carDetailsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CarImageRepository carImageRepository;
    @Autowired
    private MultimediaUtil multimediaUtil;

    public GeneralResponse<Item> addCar(String carNameAr, String carNameEn, String curbWeight, String fuelTankCapacity, String grossVehicleWeight,
            String groundClearance, String height, String width, MultipartFile mainPic, String maxPower, String maxTorque, String transmission, String trunkCapacity, Long carModelId,
            String price, MultipartFile internalImage1, MultipartFile internalImage2, MultipartFile internalImage3, MultipartFile externalImage1, MultipartFile externalImage2, MultipartFile externalImage3, String engineCapacity, String length, MultipartFile carCatalog) throws IOException {

        CarDetails newCarDetial = new CarDetails();
        newCarDetial.setCreatedDate(new Date());
        newCarDetial.setCarModel(new CarModels(carModelId));
        newCarDetial.setIsDeleted(false);
        newCarDetial.setCarNameAr(carNameAr);
        newCarDetial.setCarNameEn(carNameEn);
        newCarDetial.setCurbWeight(curbWeight);
        newCarDetial.setFuelTankCapacity(fuelTankCapacity);
        newCarDetial.setGrossVehicleWeight(grossVehicleWeight);
        newCarDetial.setGroundClearance(groundClearance);
        newCarDetial.setHeight(height);
        newCarDetial.setWidth(width);
        newCarDetial.setMaxPower(maxPower);
        newCarDetial.setMaxTorque(maxTorque);
        newCarDetial.setTransmission(transmission);
        newCarDetial.setTrunkCapacity(trunkCapacity);
        newCarDetial.setPrice(price);
        newCarDetial.setEngineCapacity(engineCapacity);
        newCarDetial.setLength(length);
        if (carCatalog != null) {
            newCarDetial.setCarCatalog(MultimediaUtil.uploadFile(carCatalog, "carCatalog"));
        }

        if (mainPic != null) {
            newCarDetial.setMainPic(MultimediaUtil.uploadPhoto(mainPic.getBytes(), "car"));
        }
        System.out.println(newCarDetial);

        if (internalImage1 != null) {
            newCarDetial.setInternalImage1(MultimediaUtil.uploadPhoto(internalImage1, "car"));
        }
        if (internalImage2 != null) {
            newCarDetial.setInternalImage2(MultimediaUtil.uploadPhoto(internalImage2, "car"));
        }
        if (internalImage3 != null) {
            newCarDetial.setInternalImage3(MultimediaUtil.uploadPhoto(internalImage3, "car"));
        }

        if (externalImage1 != null) {
            newCarDetial.setExternalImage1(MultimediaUtil.uploadPhoto(externalImage1, "car"));
        }
        if (externalImage2 != null) {
            newCarDetial.setExternalImage2(MultimediaUtil.uploadPhoto(externalImage2, "car"));
        }
        if (externalImage3 != null) {
            newCarDetial.setExternalImage3(MultimediaUtil.uploadPhoto(externalImage3, "car"));
        }
//        CarImage carImage1 = new CarImage(MultimediaUtil.uploadPhoto(internalImage1.getBytes(), "car"), newCarDetial, (short) 2);
//        carImageRepository.save(carImage1);
//        CarImage carImage2 = new CarImage(MultimediaUtil.uploadPhoto(internalImage2.getBytes(), "car"), newCarDetial, (short) 2);
//        carImageRepository.save(carImage2);
//        CarImage carImage3 = new CarImage(MultimediaUtil.uploadPhoto(internalImage3.getBytes(), "car"), newCarDetial, (short) 2);
//        carImageRepository.save(carImage3);
//        CarImage carImage4 = new CarImage(MultimediaUtil.uploadPhoto(externalImage1.getBytes(), "car"), newCarDetial, (short) 1);
//        carImageRepository.save(carImage4);
//        CarImage carImage5 = new CarImage(MultimediaUtil.uploadPhoto(externalImage2.getBytes(), "car"), newCarDetial, (short) 1);
//        carImageRepository.save(carImage5);
//        CarImage carImage6 = new CarImage(MultimediaUtil.uploadPhoto(externalImage3.getBytes(), "car"), newCarDetial, (short) 1);
//        carImageRepository.save(carImage6);
        CarDetails carDetailsDB = carDetailsRepository.save(newCarDetial);

        Item item = new Item();
        item.setId(carDetailsDB.getCarId());
        return new GeneralResponse<>(StatusCode.OK, item);
    }

    public GeneralResponse<List<CarDetailTemplate>> findAllCars(int page, int noOfRows, byte language) {
        GeneralResponse<List<CarDetailTemplate>> response;
        List<CarDetails> result = carDetailsRepository.findByIsDeletedIsFalse(new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "createdDate"));
        if (result.isEmpty()) {
            response = new GeneralResponse<>(StatusCode.ZERO_RESULTS);
        } else {
            List<CarDetailTemplate> data = new ArrayList<>();
            result.forEach(cd -> {
                data.add(new CarDetailTemplate(cd, null, language));
            });

            response = new GeneralResponse<>(StatusCode.OK, data, carDetailsRepository.countByIsDeletedIsFalse());
        }
        return response;
    }

    public GeneralResponse<CarDetailTemplate> findBycarId(long carId, String token, byte language) {
        GeneralResponse<CarDetailTemplate> response;

        JWTFilter filter = new JWTFilter();
        String userFromToken = filter.getUserFromToken(token);
        Users userDB = null;
        if (token != null) {
            userDB = usersRepository.findByEmail(userFromToken);
        }

        CarDetails carDetailDB = carDetailsRepository.findOne(carId);
        if (carDetailDB == null) {
            response = new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        } else {
            if (userDB != null) {
                response = new GeneralResponse<>(StatusCode.OK, new CarDetailTemplate(carDetailDB, userDB.getUserId(), language));
            } else {
                response = new GeneralResponse<>(StatusCode.OK, new CarDetailTemplate(carDetailDB, null, language));
            }
        }
        return response;
    }

    public GeneralResponse updateCar(long id, String carNameAr, String carNameEn, String curbWeight, String fuelTankCapacity, String grossVehicleWeight,
            String groundClearance, String height, String width, MultipartFile mainPic, String maxPower, String maxTorque, String transmission, String trunkCapacity, Long carModelId,
            String price, MultipartFile internalImage1, MultipartFile internalImage2, MultipartFile internalImage3, MultipartFile externalImage1, MultipartFile externalImage2, MultipartFile externalImage3, String engineCapacity, String length, MultipartFile carCatalog) {
        GeneralResponse response;

        CarDetails carDB = carDetailsRepository.findByIsDeletedIsFalseAndCarId(id);
        if (carDB != null) {
            carDB.setCarModel(new CarModels(carModelId));
            carDB.setCarNameAr(carNameAr);
            carDB.setCarNameEn(carNameEn);
            carDB.setCurbWeight(curbWeight);
            carDB.setEngineCapacity(engineCapacity);
            carDB.setFuelTankCapacity(fuelTankCapacity);
            carDB.setGrossVehicleWeight(grossVehicleWeight);
            carDB.setGroundClearance(groundClearance);
            carDB.setHeight(height);
            carDB.setPrice(price);
            carDB.setLength(length);
            if (mainPic != null) {
                carDB.setMainPic(MultimediaUtil.uploadPhoto(mainPic, "car"));
            }
            carDB.setMaxPower(maxPower);
            carDB.setMaxTorque(maxTorque);
            carDB.setTransmission(transmission);
            carDB.setTrunkCapacity(trunkCapacity);
            carDB.setWidth(width);

            if (internalImage1 != null) {
                File f = new File("/ubuntu/home/" + carDB.getInternalImage1());
                if (f.isFile()) {
                    f.delete();
                }
                carDB.setInternalImage1(MultimediaUtil.uploadPhoto(internalImage1, "car"));
            }

            if (internalImage2 != null) {
                File f = new File("/ubuntu/home/" + carDB.getInternalImage2());
                if (f.isFile()) {
                    f.delete();
                }
                carDB.setInternalImage2(MultimediaUtil.uploadPhoto(internalImage2, "car"));
            }
            if (internalImage3 != null) {
                File f = new File("/ubuntu/home/" + carDB.getInternalImage3());
                if (f.isFile()) {
                    f.delete();
                }
                carDB.setInternalImage3(MultimediaUtil.uploadPhoto(internalImage3, "car"));
            }

            if (externalImage1 != null) {
                File f = new File("/ubuntu/home/" + carDB.getInternalImage1());
                if (f.isFile()) {
                    f.delete();
                }
                carDB.setExternalImage1(MultimediaUtil.uploadPhoto(externalImage1, "car"));
            }
            if (externalImage2 != null) {
                File f = new File("/ubuntu/home/" + carDB.getExternalImage2());
                if (f.isFile()) {
                    f.delete();
                }
                carDB.setExternalImage2(MultimediaUtil.uploadPhoto(externalImage2, "car"));
            }
            if (externalImage3 != null) {
                File f = new File("/ubuntu/home/" + carDB.getExternalImage3());
                if (f.isFile()) {
                    f.delete();
                }
                carDB.setExternalImage3(MultimediaUtil.uploadPhoto(externalImage3, "car"));
            }

            carDetailsRepository.saveAndFlush(carDB);
            response = new GeneralResponse(StatusCode.OK);
        } else {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse<List<CarDetailTemplate>> findByModel(Long modelId, int page, int noOfRows, byte language) {
        GeneralResponse<List<CarDetailTemplate>> response;
        List<CarDetails> result = carDetailsRepository.findByIsDeletedIsFalseAndCarModel_ModelId(modelId, new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "createdDate"));
        if (!result.isEmpty()) {
            List<CarDetailTemplate> data = new ArrayList<>();
            result.forEach(cd -> {
                data.add(new CarDetailTemplate(cd, null, language));
            });
            response = new GeneralResponse<>(StatusCode.OK, data, carDetailsRepository.countByIsDeletedIsFalseAndCarModel_ModelId(modelId));
        } else {
            response = new GeneralResponse<>(StatusCode.ZERO_RESULTS);
        }
        return response;
    }

    public GeneralResponse deleteCar(Long id) {
        GeneralResponse response;
        CarDetails carDB = carDetailsRepository.findOne(id);
        if (carDB == null) {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        } else {
            carDB.setIsDeleted(Boolean.TRUE);
            carDetailsRepository.saveAndFlush(carDB);
            response = new GeneralResponse(StatusCode.OK);
        }
        return response;
    }

}
