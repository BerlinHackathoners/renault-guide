package com.renault.guide.intent;

import com.renault.guide.intent.domain.ApiResponse;
import com.renault.guide.knowledge.KnowledgeController;
import com.renault.guide.knowledge.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class IntentDetectionService {

//    private final
    private final static String URL = "https://api.dialogflow.com/v1/query";
    private static final String ACCESS_TOKEN = "6fd2973c9a8d41af9dfab01de74a69b4";
    private static final String SESSION_ID = "4437d5f2-8c8e-4f59-b7eb-78cfd47bba79";
    private final static String API_VERSION = "20150910";

    @Autowired
    private KnowledgeService knowledgeService;


    public String getIntentAndEntity(String query) throws Exception {
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

        String intent = response.getBody().getResult().getMetaData().getIntentName();
        String places = response.getBody().getResult().getParameters().getPlaces();

        if(intent.equals("INFORMATION"))
        {
            return knowledgeService.getWikiExtract(response.getBody().getResult().getParameters().getPlaces());
        }
        else if(intent.equals("DROP"))
        {
            return "Finding nearby parking to drop you at " + places;
        }
        else if(intent.equals("stop_talking"))
        {
            return "Thank you for using Renault agent. Enjoy rest of the tour.";
        }
        else if(intent.equals("EVENT"))
        {
            return "There is special gala with Vanesha Diaz happening here from 25 to 27the May";
        }
        else
        {
            return "I don't understand your question";
        }

    }

}
