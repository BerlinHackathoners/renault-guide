package com.renault.guide.intent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/get_response")
public class IntentModuleController {

    @Autowired
    private IntentDetectionService intentDetectionService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getResponse(
            @RequestParam(value = "q", defaultValue = "") String query
    ) throws Exception
    {
        return intentDetectionService.getIntentAndEntity(query);
    }
}
