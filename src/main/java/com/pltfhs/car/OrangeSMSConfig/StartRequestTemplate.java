/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.OrangeSMSConfig;

/**
 *
 * @author Client 1
 */
public class StartRequestTemplate {

    private final OutboundSMSMessageRequest outboundSMSMessageRequest;

    public StartRequestTemplate(String sendTo, String msg) {
        this.outboundSMSMessageRequest = new OutboundSMSMessageRequest(sendTo, msg);
    }

    public OutboundSMSMessageRequest getOutboundSMSMessageRequest() {
        return outboundSMSMessageRequest;
    }

}
