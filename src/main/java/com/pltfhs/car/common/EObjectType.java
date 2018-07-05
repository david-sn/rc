/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.common;

/**
 *
 * @author Client 1
 */
public enum EObjectType {
    CAR(6), FAQ(4), COMMENT(5), POST(2), BLOG(3), NEWS(1);

    private int objectTypeId;

    private EObjectType(int objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public int getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(int objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

}
