/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.OrangeSMSConfig.SMSAuthentication;
import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
public class EmailAndSmsController {

    @Autowired
    private EmailService andSmsService;
    private SMSAuthentication sms;

    public GeneralResponse sendSMS(@RequestBody GeneralParameter body) {
        sms.sendSMS(body.getTo(), body.getMsg());
        return new GeneralResponse(StatusCode.OK);
    }

    public GeneralResponse sendEmail(@RequestBody GeneralParameter body) {

        andSmsService.sendMail(body.getTo(), body.getSubject(), body.getMsg());

        return new GeneralResponse(StatusCode.OK);
    }

}
