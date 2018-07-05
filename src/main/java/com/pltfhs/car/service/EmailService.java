/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.service;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Client 1
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;
    private SimpleMailMessage message = null;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to, String subject, String msg) {
//        cer();
        if (message == null) {
            message = new SimpleMailMessage();
        }
        Thread thread = new Thread(() -> {
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(msg);
            sender.send(message);
        });
        thread.start();
    }

    private void cer() {
        Properties systemProps = System.getProperties();
        systemProps.put("javax.net.ssl.keyStorePassword", "password");
        systemProps.put("javax.net.ssl.keyStore", "C:\\Users\\Client 5\\Desktop\\certificates\\alfarisict.com\\cacerts.jks");
        systemProps.put("javax.net.ssl.trustStore", "C:\\Users\\Client 5\\Desktop\\certificates\\alfarisict.com\\cacerts.jks");
        systemProps.put("javax.net.ssl.trustStorePassword", "password");
        System.setProperties(systemProps);
    }
}
