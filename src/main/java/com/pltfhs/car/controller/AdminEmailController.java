/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.entity.AdminEmails;
import com.pltfhs.car.repository.AdminEmailsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
public class AdminEmailController {

    @Autowired
    AdminEmailsRepository emailsRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/addAdminEmail/{email}")
    public boolean addEmail(@PathVariable String email) {
        emailsRepository.save(new AdminEmails(email));
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAdminEmail/{id}")
    public boolean deleteEmail(@PathVariable int id) {
        emailsRepository.delete(id);
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAdminEmail")
    public List<AdminEmails> getEmail() {

        return emailsRepository.findAll();
    }
}
