/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.OrangeSMSConfig;

import java.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Client 1
 */
public class SMSAuthentication {

    AccessTokenTemplateResponse smsAccessToken = null;

    public AccessTokenTemplateResponse getSMSAccessToken() {

        HttpHeaders headers = new HttpHeaders();
        String clientId = "n6Q1XABhn999c1onvkPRvHsl4utBTG9r";
        String clientSecret = "yKMx8AtUtEzwAKz9";

        headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> bodyParam = new LinkedMultiValueMap<>();
        bodyParam.add("grant_type", "client_credentials");

        HttpEntity httpEntity = new HttpEntity(bodyParam, headers);
        RestTemplate restTemplate = new RestTemplate();
        AccessTokenTemplateResponse tokenResponse = restTemplate.exchange("https://api.orange.com/oauth/v2/token", HttpMethod.POST, httpEntity, AccessTokenTemplateResponse.class).getBody();
        System.out.println(tokenResponse.getAccess_token());
        return tokenResponse;
    }

    public void sendSMS(String sendToNumber, String messageBody) {
        String orangeEndPoint = "https://api.orange.com/smsmessaging/v1/outbound/" + sendToNumber + "/requests ";
        HttpHeaders headers = new HttpHeaders();

        if (smsAccessToken == null) {
            smsAccessToken = getSMSAccessToken();
        } else {
            if (smsAccessToken.getExpires_in() + System.currentTimeMillis() < System.currentTimeMillis()) {
                //expired generate new access token 
                smsAccessToken = getSMSAccessToken();
            }
        }

        headers.add("Authorization", "Bearer " + smsAccessToken.getAccess_token());
        System.out.println(smsAccessToken.getAccess_token());
        OutboundSMSMessageRequest messageRequest = new OutboundSMSMessageRequest(sendToNumber, messageBody);
        HttpEntity httpEntity = new HttpEntity(messageRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.exchange(orangeEndPoint, HttpMethod.POST, httpEntity, Void.class);

    }

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            SMSAuthentication smsAuthentication = new SMSAuthentication();
            for (int i = 0; i < 100000000; i++) {
                System.out.println(i);
                smsAuthentication.getSMSAccessToken();
            }
        });
        t.start();

    }

}
