/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.CarBrands;
import com.pltfhs.car.entity.CarModels;
import com.pltfhs.car.entity.ComplaintForm;
import com.pltfhs.car.entity.DaysOff;
import com.pltfhs.car.entity.LookupServiceType;
import com.pltfhs.car.entity.ServiceRequests;
import com.pltfhs.car.entity.UserCar;
import com.pltfhs.car.entity.Users;
import com.pltfhs.car.repository.AdminEmailsRepository;
import com.pltfhs.car.repository.CarBrandsRepository;
import com.pltfhs.car.repository.CarModelsRepository;
import com.pltfhs.car.repository.ComplaintFormRepository;
import com.pltfhs.car.repository.DaysOffRepository;
import com.pltfhs.car.repository.LookupServiceTypeRepository;
import com.pltfhs.car.repository.ServiceRequestsRepository;
import com.pltfhs.car.repository.UserCarRepository;
import com.pltfhs.car.repository.UsersRepository;
import com.pltfhs.car.view.DaysOffTemplate;
import com.pltfhs.car.view.Item;
import com.pltfhs.car.view.ServiceRequestsTemplate;
import com.pltfhs.car.view.UserCarView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
public class RequestService {

    @Autowired
    private ServiceRequestsRepository serviceRequestsRepository;
    @Autowired
    private ComplaintFormRepository complaintFormRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LookupServiceTypeRepository lookupServiceTypeRepository;
    @Autowired
    private DaysOffRepository daysOffRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AdminEmailsRepository adminEmailsRepository;
    @Autowired
    private UserCarRepository userCarRepository;
    @Autowired
    private CarModelsRepository carModelsRepository;
    @Autowired
    private CarBrandsRepository carBrandsRepository;

    public GeneralResponse sendComplaintForm(String emailLogin, String clientEmail, String clientName, String clientPhone, String description, Integer groupId) {
        GeneralResponse response;
        Users userDB = usersRepository.findByEmail(emailLogin);

        ComplaintForm complaintForm = new ComplaintForm(null, clientEmail, clientName, clientPhone);
        complaintForm.setGroupId(groupId);
        complaintForm.setDescription(description);
        ComplaintForm dublicate = complaintFormRepository.findOne(Example.of(complaintForm));
        if (dublicate == null) {
            complaintForm.setCreateDate(new Date());

            if (userDB != null) {
                complaintForm.setUserId(userDB.getUserId().intValue());
            }
            complaintForm = complaintFormRepository.save(complaintForm);
            emailService.sendMail(clientEmail, "Fourm Submit", description);
            response = new GeneralResponse(StatusCode.OK, complaintForm);
        } else {
            response = new GeneralResponse(StatusCode.DATA_DUBLICATE);
        }
        return response;
    }

    public GeneralResponse contactUs(String clientEmail, String clientName, String clientPhone, String description) {
        GeneralResponse response;

        ComplaintForm complaintForm = new ComplaintForm(null, clientEmail, clientName, clientPhone);
        complaintForm.setDescription(description);
        ComplaintForm dublicate = complaintFormRepository.findOne(Example.of(complaintForm));
        if (dublicate == null) {
            complaintForm.setCreateDate(new Date());
            complaintForm = complaintFormRepository.save(complaintForm);
            emailService.sendMail(clientEmail, "Contact Us Submit", description);
            response = new GeneralResponse(StatusCode.OK, complaintForm);
        } else {
            response = new GeneralResponse(StatusCode.DATA_DUBLICATE);
        }
        return response;
    }

    public GeneralResponse changeRequestStatus(int requestId, short status) {
        GeneralResponse response;
        ServiceRequests requestDB = serviceRequestsRepository.findOne(requestId);
        if (requestDB == null) {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        } else {
            switch (status) {
                case 1://approve
                    requestDB.setApprovedDate(new Date());
                    break;
                case 2://reject
                    requestDB.setDisapproveDate(new Date());
                    break;
                default:
                    throw new AssertionError();
            }
            requestDB.setRequestStatus(status);
            response = new GeneralResponse(StatusCode.OK);
            serviceRequestsRepository.saveAndFlush(requestDB);
        }
        return response;
    }

    public GeneralResponse respondComplaintForm(Long complainId, String responseComplain) {
        GeneralResponse response;
        ComplaintForm complaintDB = complaintFormRepository.findOne(complainId);
        if (complaintDB != null) {
            complaintDB.setRespondComplain(responseComplain);
            complaintDB.setRespondDate(new Date());
            complaintFormRepository.saveAndFlush(complaintDB);
            emailService.sendMail(complaintDB.getClientEmail(), "Response Complaint", responseComplain);
            response = new GeneralResponse(StatusCode.OK);
        } else {
            response = new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        }
        return response;
    }

    public GeneralResponse createMaintainceRequesteService(String email, int lookupServiceTypeId, int userCarId, String note, String noOfKilo) {
        GeneralResponse response;
        Users userDB = usersRepository.findByEmail(email);
//        if (isAvailiableToAddMaintainceReequest(lookupServiceTypeId, targetDate) && isDateIsOFF(lookupServiceTypeId, targetDate)) {
        //no calendar  checks just send mail to fixed mail
        ServiceRequests request = new ServiceRequests();
        request.setUserId(userDB.getUserId().intValue());
//            request.setTargetDate(new Date(targetDate));
        request.setServiceId(lookupServiceTypeId);
        request.setNote(note);
        request.setCode(UUID.randomUUID().toString());
        request.setCreationDate(new Date());
        request.setRequestStatus((short) 1);
        request.setUserCarId(userCarId);
        request.setKilometers(noOfKilo);
        ServiceRequests savedMaintainceService = serviceRequestsRepository.save(request);
        emailService.sendMail(userDB.getEmail(), "Maintaince Request is Pending", "body");
        adminEmailsRepository.findAll().forEach(ae -> {
            emailService.sendMail(ae.getEmail(), "Maintaince Request is Pending", "body");
        });

        response = new GeneralResponse(StatusCode.OK, new Item(savedMaintainceService.getRequestId().longValue(), null));
//        } else {
//            response = new GeneralResponse(StatusCode.INVALID_REQUEST);
//        }
        return response;
    }

    public GeneralResponse<Item> requestOtherService(String email, String note, Integer serviceId) {
        GeneralResponse response;
        Users userDB = usersRepository.findByEmail(email);
        if (userDB != null) {
            ServiceRequests serviceRequest = new ServiceRequests();
            serviceRequest.setUserId(userDB.getUserId().intValue());
            serviceRequest.setNote(note);
            serviceRequest.setServiceId(serviceId);
            serviceRequest.setCreationDate(new Date());
            serviceRequest.setCode(UUID.randomUUID().toString());
            ServiceRequests savedRequestDB = serviceRequestsRepository.save(serviceRequest);

            response = new GeneralResponse(StatusCode.OK, new Item(savedRequestDB.getServiceId().longValue(), savedRequestDB.getCode()));
            emailService.sendMail(userDB.getEmail(), "Other Service Requested", note);
        } else {
            response = new GeneralResponse(StatusCode.USER_NOT_FOUND);
        }
        return response;
    }

    private boolean isAvailiableToAddMaintainceReequest(int lookupServiceTypeId, long targetDate) {
        LookupServiceType serviceTypeDB = lookupServiceTypeRepository.findOne(lookupServiceTypeId);
        long currentApproveRequests = serviceRequestsRepository.findByTargetDateAndRequestStatusAndServiceId(new Date(targetDate), (short) 1, lookupServiceTypeId).size();
        long currentRequestsFromCRM = requestsFromCRM(lookupServiceTypeId, targetDate);

        return (currentApproveRequests + currentRequestsFromCRM) < serviceTypeDB.getMaxRequestsPerDay();
    }

    private long requestsFromCRM(int lookupServiceTypeId, long targetDate) {
        //remote request to get total current reservation on this day
        return 0;
    }

    private boolean isDateIsOFF(int lookupServiceTypeId, long targetDate) {
        //check if day is from day off or unavialable date table
        List<DaysOff> dayIsOff = daysOffRepository.getDayIsOff(lookupServiceTypeId, new Date(targetDate));
        return dayIsOff.isEmpty();
    }

    public GeneralResponse<DaysOffTemplate> getDaysOff(byte language) {
        List<DaysOff> data = daysOffRepository.getDaysOFF();
        List<DaysOffTemplate> result = new ArrayList<>(data.size());
        data.forEach(d -> {
            result.add(new DaysOffTemplate(d, language));
        });
        return new GeneralResponse(StatusCode.OK, result);
    }

    public GeneralResponse<DaysOffTemplate> addDaysOff(DaysOff daysOff, int serviceId, byte language) {
        LookupServiceType serviceTypeDB = lookupServiceTypeRepository.findOne(serviceId);
        if (serviceTypeDB == null) {
            return new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        }
        daysOff.setServiceId(serviceTypeDB);
        DaysOff isDublicate = daysOffRepository.findOne(Example.of(daysOff));
        if (isDublicate != null) {
            return new GeneralResponse<>(StatusCode.DATA_DUBLICATE);
        }
        DaysOff dayOffDB = daysOffRepository.save(daysOff);
        return new GeneralResponse<>(StatusCode.OK, new DaysOffTemplate(dayOffDB, language));
    }

    public GeneralResponse<Item> deleteDaysOff(int daysOffId) {
        DaysOff dayDB = daysOffRepository.findOne(daysOffId);
        if (dayDB == null) {
            return new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        }
        daysOffRepository.delete(daysOffId);
        return new GeneralResponse<>(StatusCode.OK);
    }

    public GeneralResponse<DaysOffTemplate> updateDaysOff(DaysOff daysOff, int serviceId, byte language) {
        LookupServiceType serviceTypeDB = lookupServiceTypeRepository.findOne(serviceId);
        DaysOff dayOffDB = daysOffRepository.findOne(daysOff.getDayId());
        if (serviceTypeDB == null || dayOffDB == null) {
            return new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        }
        daysOff.setServiceId(serviceTypeDB);
        daysOffRepository.saveAndFlush(daysOff);
        return new GeneralResponse<>(StatusCode.OK, new DaysOffTemplate(daysOff, language));
    }

    public GeneralResponse findMaintainceRequesteById(Long id, byte language) {
        ServiceRequests serviceRequestDB = serviceRequestsRepository.findOne(id.intValue());
        if (serviceRequestDB != null) {
            serviceRequestDB.setIsReaded(true);
            serviceRequestsRepository.saveAndFlush(serviceRequestDB);
            List<ServiceRequestsTemplate> data = new ArrayList<>();
            UserCar userCarDB = userCarRepository.findOne(serviceRequestDB.getUserCarId());
            CarModels carModel = carModelsRepository.findOne(userCarDB.getCarModelId().longValue());
            CarBrands carBrand = carBrandsRepository.findOne(userCarDB.getCarBrandId());
            data.add(new ServiceRequestsTemplate(
                    serviceRequestDB,
                    new UserCarView(userCarDB, carBrand, carModel, language),
                    usersRepository.findOne(serviceRequestDB.getUserId().longValue()),
                    lookupServiceTypeRepository.findOne(serviceRequestDB.getServiceId()),
                    language)
            );
            return new GeneralResponse(StatusCode.OK, serviceRequestDB);
        } else {
            return new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        }
    }

    public GeneralResponse findAllMaintainceRequeste(Integer page, Integer noOfRows, byte language) {
        Page<ServiceRequests> serviceRequestDB = serviceRequestsRepository.findAll(new PageRequest(page - 1, noOfRows));

        List<ServiceRequestsTemplate> data = new ArrayList<>();
        serviceRequestDB.forEach(sr -> {
            UserCar userCarDB = userCarRepository.findOne(sr.getUserCarId());
            CarModels carModel = carModelsRepository.findOne(userCarDB.getCarModelId().longValue());
            CarBrands carBrand = carBrandsRepository.findOne(userCarDB.getCarBrandId());
            data.add(new ServiceRequestsTemplate(
                    sr,
                    new UserCarView(userCarDB, carBrand, carModel, language),
                    usersRepository.findOne(sr.getUserId().longValue()),
                    lookupServiceTypeRepository.findOne(sr.getServiceId()),
                    language)
            );
        });

        if (!data.isEmpty()) {
            return new GeneralResponse(StatusCode.OK, data, serviceRequestDB.getTotalElements());
        } else {
            return new GeneralResponse(StatusCode.DATA_NOT_FOUND);
        }
    }
}
