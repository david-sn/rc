/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.repository.UsersRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David Shiref
 */
@RestController
public class HController {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private UsersRepository ur;

    @PostMapping("/executeNativeSQL")
    @Transactional
    public String executeNativeSQL(@RequestBody String sql) {
        int executeUpdate = em.createNativeQuery(sql).executeUpdate();
        return "done " + executeUpdate;
    }

    @GetMapping("/updateIsDeleteUser")
    public String updateIsDeleteUser() {
        ur.findAll().forEach(u -> {
            u.setIsDeleted(false);
            ur.saveAndFlush(u);
        });
        return "done ";
    }

}
