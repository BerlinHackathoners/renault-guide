package com.renault.guide.intent;

import com.renault.guide.intent.dto.ApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class IntentDetectionService {

//    private final
    private final static String URL = "https://api.dialogflow.com/v1/query";
    private static final String ACCESS_TOKEN = "6fd2973c9a8d41af9dfab01de74a69b4";
    private static final String SESSION_ID = "4437d5f2-8c8e-4f59-b7eb-78cfd47bba79";
    private final static String API_VERSION = "20150910";


    public static void getIntentAndEntity(String query)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + ACCESS_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        URI uri = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("v", API_VERSION)
                .queryParam("lang", "en")
                .queryParam("sessionId", SESSION_ID)
                .queryParam("query", query)
                .build().encode().toUri();
        ResponseEntity<ApiResponse> response
                = restTemplate.exchange(uri, HttpMethod.GET, entity, ApiResponse.class);

        System.out.print("intent:" + response.getBody().getResult().getMetaData().getIntentName());
        System.out.println("-----------");
        System.out.println("place:" +response.getBody().getResult().getParameters().getPlaces());
    }

    public static void main(String[] args)
    {
        getIntentAndEntity("Shut the fuck up");
    }
}
