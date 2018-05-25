package com.renault.guide.texttospeech;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextToSpeechModule {
    @RequestMapping("/test")
    public String test(){

        return "test";
    }

}
