/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.controller;

import com.pltfhs.car.common.GeneralParameter;
import com.pltfhs.car.common.GeneralResponse;
import com.pltfhs.car.common.StatusCode;
import com.pltfhs.car.service.EntityInteractionService;
import com.pltfhs.car.view.Item;
import com.pltfhs.car.view.SingleKeys;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Client 1
 */
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class EntityInteractionController {

    @Autowired
    private EntityInteractionService entityInteractions;

    @RequestMapping("/likeEntity")
    public GeneralResponse<SingleKeys> like_disLikeEntity(@RequestBody GeneralParameter body, Principal principal) {
        return entityInteractions.like_disLikeEntity(
                principal.getName(),
                body.getEntityId(),
                body.getEntityType(),
                body.getLikeStatus()
        );
    }

    @RequestMapping("/rateEntity")
    public GeneralResponse<SingleKeys> rateEntity(@RequestBody GeneralParameter body, Principal principal) {
        return entityInteractions.rateEntity(
                principal.getName(),
                body.getEntityId(),
                body.getEntityType(),
                body.getRateValue()
        );
    }

    @RequestMapping("/addCommentEntity")
    public GeneralResponse<Item> addCommentEntity(@RequestBody GeneralParameter body, Principal principal) {
        return entityInteractions.addCommentEntity(
                principal.getName(),
                body.getEntityId(),
                body.getEntityType(),
                body.getValue()
        );
    }

    @RequestMapping("/editCommentEntity")
    public GeneralResponse<Item> editCommentEntity(@RequestBody GeneralParameter body, Principal principal) {
        return entityInteractions.editCommentEntity(
                principal.getName(),
                body.getValue(),
                body.getId()
        );
    }

    @RequestMapping("/deleteCommentEntity")
    public GeneralResponse<Item> deleteCommentEntity(@RequestBody GeneralParameter body, Principal principal) {
        return entityInteractions.deleteCommentEntity(
                principal.getName(),
                body.getId()
        );
    }

    @RequestMapping("/viewEntity")
    public GeneralResponse<SingleKeys> viewEntity(@RequestBody GeneralParameter body, Principal principal) {
        entityInteractions.addObjectView(body.getId(), body.getEntityType(), principal.getName());
        SingleKeys keys = new SingleKeys();
        keys.setTotalViewing(entityInteractions.getTotalNumberOfViewing(body.getId().toString(), body.getEntityType().getObjectTypeId()));
        return new GeneralResponse<>(StatusCode.OK, keys);
    }
}
