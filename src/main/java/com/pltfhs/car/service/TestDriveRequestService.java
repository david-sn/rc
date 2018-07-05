package com.pltfhs.car.service;

import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.entity.TestDirveRequest;
import com.pltfhs.car.repository.TestDriveRequestRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TestDriveRequestService {

    @Autowired
    private TestDriveRequestRepository testDriveRequestRepository;
    @Autowired
    private EmailService EmailService;

    public GeneralResponse<TestDirveRequest> addRequestTestDrive(String clientName, String clientEmail, String clientPhone, String note) {
        GeneralResponse<TestDirveRequest> response = null;

        List<TestDirveRequest> dublicated = testDriveRequestRepository.findByNoteAndClientNameContainsAndClientEmailContainsAndClientPhoneContains(note, clientName, clientEmail, clientPhone);
        if (!dublicated.isEmpty()) {
            response = new GeneralResponse(StatusCode.DATA_DUBLICATE);
        } else {
            TestDirveRequest willSaved = new TestDirveRequest(note, clientEmail, clientName, clientPhone, new Date());
            willSaved = testDriveRequestRepository.save(willSaved);
//            EmailService.sendMail(clientEmail, "Test Drive Request", note);
            //send sms    
            response = new GeneralResponse(StatusCode.OK, willSaved);
        }
        return response;
    }

    public GeneralResponse<TestDirveRequest> editRequestTestDrive(long testDriveId, Date testDriveDate, String clientName, String clientEmail, String clientPhone, String note) {
        GeneralResponse<TestDirveRequest> response = null;
        TestDirveRequest testDriveRequestDB = testDriveRequestRepository.findOne(testDriveId);
        if (testDriveRequestDB == null) {
            response = new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        } else {
            testDriveRequestDB.setClientEmail(clientEmail);
            testDriveRequestDB.setClientName(clientName);
            testDriveRequestDB.setClientPhone(clientPhone);
            testDriveRequestDB.setNote(note);
            testDriveRequestDB.setTestDriveDate(testDriveDate);
            TestDirveRequest updatedDirveRequest = testDriveRequestRepository.saveAndFlush(testDriveRequestDB);
            response = new GeneralResponse<>(StatusCode.OK, updatedDirveRequest);
        }
        return response;
    }

    public GeneralResponse deleteRequestTestDrive(long testDriveId) {
        GeneralResponse response;
        TestDirveRequest testDriveRequestDB = testDriveRequestRepository.findOne(testDriveId);
        if (testDriveRequestDB == null) {
            // 404 not found
            response = new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        } else {
            testDriveRequestRepository.delete(testDriveId);
            response = new GeneralResponse<>(StatusCode.OK);
        }
        return response;
    }

    public GeneralResponse findRequestTestDrive(Long testDriveId) {
        GeneralResponse response;
        TestDirveRequest testDriveRequestDB = testDriveRequestRepository.findOne(testDriveId);
        if (testDriveRequestDB == null) {
            // 404 not found
            response = new GeneralResponse<>(StatusCode.DATA_NOT_FOUND);
        } else {
            testDriveRequestDB.setIsReaded(true);
            testDriveRequestRepository.saveAndFlush(testDriveRequestDB);
            response = new GeneralResponse<>(StatusCode.OK, testDriveRequestDB);
        }
        return response;
    }

    public GeneralResponse listRequestTestDrive(int page, int siez) {
        Page<TestDirveRequest> date = testDriveRequestRepository.findAll(new PageRequest(page - 1, siez, Sort.Direction.DESC, "createDate"));
        return new GeneralResponse(StatusCode.OK, date.getContent(), date.getTotalElements());
    }
}
