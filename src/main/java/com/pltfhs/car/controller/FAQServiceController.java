/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.entity.Faqs;
import com.pltfhs.car.service.FAQService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class FAQServiceController {

    @Autowired
    private FAQService fAQService;

    @RequestMapping("/addFaq")
    public GeneralResponse<Faqs> addFAQuestion(@RequestBody GeneralParameter body, Principal principal) {
        return fAQService.addFAQuestion(
                principal.getName(),
                body.getFaqTitle(),
                body.getFaqDescription(),
                body.getTags(),
                body.getAnswer()
        );
    }

    @RequestMapping("/updateFaq")
    public GeneralResponse<Faqs> updateFAQuestion(@RequestBody GeneralParameter body, Principal principal) {
        return fAQService.updateFaqQuestion(
                body.getFaqId(),
                body.getFaqTitle(),
                body.getFaqDescription(),
                body.getAnswer(),
                body.getTags(),
                body.getDeletedTags()
        );
    }

    @RequestMapping("/addFAQAnswer")
    public GeneralResponse<Faqs> addFAQAnswer(@RequestBody GeneralParameter body) {
        return fAQService.addFAQAnswer(
                body.getId().intValue(),
                body.getValue()
        );
    }

    @RequestMapping("/getAllFaqs")
    public GeneralResponse<List<Faqs>> listAllQuestion(@RequestBody GeneralParameter body) {
        return fAQService.listAllQuestion(
                body.getPage(),
                body.getNoOfRows()
        );
    }

    @RequestMapping("/findFaqById/{id}")
    public GeneralResponse getFaqById(@PathVariable long id) {
        return fAQService.getFaqById(
              id
        );
    }

    @RequestMapping(value = "/deleteFaq",method = RequestMethod.POST)
    public GeneralResponse deleteFaq(@RequestBody GeneralParameter body) {
        return fAQService.deleteFaq(
                body.getFaqId()
        );
    }
}
