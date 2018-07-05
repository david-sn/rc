package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.CarBrands;
import com.pltfhs.car.repository.CarBrandsRepository;
import com.pltfhs.car.util.MultimediaUtil;
import com.pltfhs.car.view.ImagesPath;
import com.pltfhs.car.view.SingleKeys;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BrandService {

    @Autowired
    private CarBrandsRepository carBrandsRepository;
    @Autowired
    private MultimediaUtil multimediaUtil;

    public GeneralResponse<List<CarBrands>> findByBrandName(Short typeId, String name) {
        GeneralResponse<List<CarBrands>> response = null;
        List<CarBrands> brands = carBrandsRepository.findByBrandNameArOrBrandNameEnAndIsDeletedIsFalseAndTypeId(name, name, typeId);
        if (brands.isEmpty()) {
            response = new GeneralResponse<>(StatusCode.ZERO_RESULTS);
        } else {
            response = new GeneralResponse<>(StatusCode.OK, brands, carBrandsRepository.countByBrandNameArOrBrandNameEnAndIsDeletedIsFalseAndTypeId(name, name, typeId));
        }
        return response;
    }

    public GeneralResponse addNewCarBrand(CarBrands newCarBrand) {
        if (newCarBrand.getBrandImage() != null) {
            byte[] image = Base64.decodeBase64(newCarBrand.getBrandImage());
            String imageUrl = multimediaUtil.uploadPhoto(image, "brand");
            newCarBrand.setBrandImage(imageUrl);
        }

        newCarBrand.setCreatedDate(new Date());
        newCarBrand.setIsDeleted(false);
        carBrandsRepository.saveAndFlush(newCarBrand);

        SingleKeys keys = new SingleKeys();
        keys.setBrandId(newCarBrand.getBrandId().longValue());
        GeneralResponse response = new GeneralResponse(StatusCode.OK, keys);

        return response;
    }

    public GeneralResponse updateBrand(CarBrands carBrand) {

        CarBrands brandToUpdate = carBrandsRepository.findOne(carBrand.getBrandId());

        brandToUpdate.setBrandNameAr(carBrand.getBrandNameAr());
        brandToUpdate.setBrandNameEn(carBrand.getBrandNameEn());
        if (carBrand.getBrandImage() != null) {
            byte[] image = Base64.decodeBase64(carBrand.getBrandImage());
            String imageUrl = multimediaUtil.uploadPhoto(image, "brand");
            brandToUpdate.setBrandImage(imageUrl);
        }

        carBrandsRepository.saveAndFlush(brandToUpdate);
        SingleKeys keys = new SingleKeys();
        keys.setBrandId(brandToUpdate.getBrandId().longValue());
        GeneralResponse response = new GeneralResponse(StatusCode.OK, keys);

        return response;
    }

    public GeneralResponse<List<CarBrands>> findAllBrands(Short typeId, int page, int noOfRows) {
        GeneralResponse<List<CarBrands>> response = new GeneralResponse<>();
        List<CarBrands> brands = carBrandsRepository.findByIsDeletedIsFalseAndTypeId(typeId, new PageRequest(page - 1, noOfRows, Sort.Direction.DESC, "createdDate"));
        if (brands.isEmpty()) {
            response.setStatusCode(StatusCode.ZERO_RESULTS);
            return response;
        }
        response.setStatusCode(StatusCode.OK);
        response.setResult(brands);
        response.setTotalCount(carBrandsRepository.countByIsDeletedIsFalseAndTypeId(typeId));
        return response;
    }

    public GeneralResponse<CarBrands> findByBrandId(int brandId) {
        GeneralResponse<CarBrands> response = new GeneralResponse<>();
        CarBrands brand = carBrandsRepository.findByBrandIdAndIsDeletedIsFalse(brandId);
        if (brand == null) {
            response.setStatusCode(StatusCode.DATA_NOT_FOUND);
            return response;
        }
        response.setStatusCode(StatusCode.OK);
        response.setResult(brand);
        return response;
    }

    public GeneralResponse<String> deleteImage(@RequestBody ImagesPath imagesPath) {
        GeneralResponse<String> response = new GeneralResponse<String>();
        Map<String, Boolean> resp = new HashMap<>();
        System.out.println(imagesPath.getImagePath());
        if (imagesPath.getbId() != null) {
            CarBrands carBrand = carBrandsRepository.findOne(imagesPath.getbId());
            if (carBrand != null) {
                carBrand.setBrandImage("media/car-avatar.png");
                response.setResult("media/car-avatar.png");
                carBrandsRepository.save(carBrand);
                boolean isDeleted = MultimediaUtil.deleteImage(imagesPath.getImagePath());
                if (isDeleted) {
                    response.setStatusCode(StatusCode.OK);
                    return response;
                }
            }
        }

        response.setStatusCode(StatusCode.INVALID_REQUEST);
        return response;

    }

    public GeneralResponse deleteBrand(int brandId) {
        GeneralResponse response;
        CarBrands brandDB = carBrandsRepository.findByBrandIdAndIsDeletedIsFalse(brandId);
        if (brandDB == null) {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        } else {
            brandDB.setIsDeleted(true);
            carBrandsRepository.saveAndFlush(brandDB);
            response = new GeneralResponse(StatusCode.OK);
        }
        return response;
    }
}
