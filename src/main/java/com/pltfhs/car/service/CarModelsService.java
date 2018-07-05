package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.CarBrands;
import com.pltfhs.car.entity.CarModels;
import com.pltfhs.car.repository.CarModelsRepository;
import com.pltfhs.car.util.MultimediaUtil;
import com.pltfhs.car.view.CarModelTemplate;
import com.pltfhs.car.view.Item;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CarModelsService {

    @Autowired
    private CarModelsRepository carModelsRepository;
    @Autowired
    private MultimediaUtil multimediaUtil;

    public GeneralResponse<Item> addCarModel(CarModels carModel, Long brandId) {
        carModel.setCreatedDate(new Date());
        carModel.setIsDeleted(false);
        if (carModel.getModelImg() != null) {
            byte[] image = Base64.decodeBase64(carModel.getModelImg());
            String imageUrl = multimediaUtil.uploadPhoto(image, "brand");
            carModel.setModelImg(imageUrl);
        }
        carModel.setCarBrands(new CarBrands(brandId.intValue()));
        CarModels saved = carModelsRepository.save(carModel);
        Item item = new Item();
        item.setId(saved.getModelId());

        return new GeneralResponse<>(StatusCode.OK, item);
    }

    public GeneralResponse<List<CarModelTemplate>> findAllModels(int page, int noOfRows, Short typeId, byte language) {
        GeneralResponse<List<CarModelTemplate>> response;
        System.out.println(typeId);
        List<CarModels> models = carModelsRepository.findByIsDeletedIsFalseAndTypeId(new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "createdDate"), typeId);
        if (models.isEmpty()) {
            response = new GeneralResponse<>(StatusCode.ZERO_RESULTS);
        } else {
            List<CarModelTemplate> carModelTemplates = new ArrayList<>();
            models.forEach(cm -> {
                carModelTemplates.add(new CarModelTemplate(cm, language));
            });
            response = new GeneralResponse<>(StatusCode.OK, carModelTemplates, carModelsRepository.countByIsDeletedIsFalseAndTypeId(typeId));
        }
        return response;
    }

    public GeneralResponse<CarModels> findByModelId(long modelId) {
        GeneralResponse<CarModels> response;
        CarModels model = carModelsRepository.findByModelIdAndIsDeletedIsFalse(modelId);
        if (model == null) {
            response = new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        } else {
            response = new GeneralResponse<>(StatusCode.OK, model);
        }
        return response;
    }

    public GeneralResponse<List<CarModelTemplate>> findByModelName(String modelName, Short typeId, byte language) {
        List<CarModels> models = carModelsRepository.findByModelNameArContainingOrModelNameEnContainingAndIsDeletedIsFalseAndTypeId(modelName, modelName, typeId);
        List<CarModelTemplate> carModelTemplates = new ArrayList<>();
        models.forEach(cm -> {
            carModelTemplates.add(new CarModelTemplate(cm, language));
        });
        return new GeneralResponse<>(StatusCode.OK, carModelTemplates, carModelsRepository.countByIsDeletedIsFalseAndTypeId(typeId));

    }

    public GeneralResponse<Item> updateCarModel(CarModels carModel, Long carBrandId) {
        CarModels modelToUpdate = carModelsRepository.findByModelIdAndIsDeletedIsFalse(carModel.getModelId());
        if (modelToUpdate != null) {

            modelToUpdate.setModelNameAr(carModel.getModelNameAr());
            modelToUpdate.setModelNameEn(carModel.getModelNameEn());
            modelToUpdate.setCarBrands(new CarBrands(carBrandId.intValue()));
            if (carModel.getModelImg() != null) {
                byte[] image = Base64.decodeBase64(carModel.getModelImg());
                String imageUrl = multimediaUtil.uploadPhoto(image, "brand");
                modelToUpdate.setModelImg(imageUrl);
            }

            carModelsRepository.save(modelToUpdate);
            Item item = new Item();
            item.setId(modelToUpdate.getModelId());
            return new GeneralResponse<>(StatusCode.OK, item);
        } else {
            return new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        }
    }

    public GeneralResponse<Item> deleteMode(Long id) {
        CarModels modelToDelete = carModelsRepository.findOne(id);
        if (modelToDelete == null) {
            return new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        }
        modelToDelete.setIsDeleted(true);
        carModelsRepository.save(modelToDelete);
        return new GeneralResponse<>(StatusCode.OK);
    }

    public GeneralResponse findModelByBrandId(int brandId, Short typeId, Byte language) {
        List<CarModels> result = carModelsRepository.findBycarBrands_brandIdAndIsDeletedIsFalseAndTypeId(brandId, typeId);
        List<CarModelTemplate> carModelTemplates = new ArrayList<>();
        result.forEach(cm -> {
            carModelTemplates.add(new CarModelTemplate(cm, language));
        });
        return new GeneralResponse(StatusCode.OK, result);
    }
}
