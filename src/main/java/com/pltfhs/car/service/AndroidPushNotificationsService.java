package com.pltfhs.car.service;

import com.pltfhs.car.HeaderRequestInterceptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAAscTR1bc:APA91bGv56EBEy-0BVhDe1vTtTVSh26e8Z8uspxresXvP6toxwl_4eR9JE5GkbT9h7HazCV8F8SAV_jMkLxpnQ81PM1v189I7OwncDG5JTVsPknkRJzo3IvZpGb8QtTx4QJqCzqTr_jp";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    private CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();
        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);
        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

    public boolean pushNotification(String mobileToken, String title, String bodyDescription, Object jsonData) {
        Map<String, Object> body = new HashMap<>();
        // body.put("to", "/topics/" + TOPIC);
        body.put("to", mobileToken);
        body.put("priority", "high");

        Map<String, Object> notification = new HashMap<>();
        notification.put("title", title);
        notification.put("body", bodyDescription);

        body.put("notification", notification);
        body.put("data", jsonData);

        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<String> pushNotification = send(request);
        CompletableFuture.allOf(pushNotification).join();

//        String firebaseResponse = pushNotification.get();
//        return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        return true;
    }
}
