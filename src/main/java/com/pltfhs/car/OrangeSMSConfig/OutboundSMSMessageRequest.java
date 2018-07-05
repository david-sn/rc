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
public class OutboundSMSMessageRequest {

    private OutboundSMSTextMessage outboundSMSTextMessage;
    private String address;
    private final String senderAddress = "";//must be final not changeable value

    public OutboundSMSMessageRequest(String sendTo,String msg) {
        this.address=sendTo;
        this.outboundSMSTextMessage=new OutboundSMSTextMessage(msg);
    }

    
    public OutboundSMSTextMessage getOutboundSMSTextMessage() {
        return outboundSMSTextMessage;
    }

    public String getAddress() {
        return address;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

}
