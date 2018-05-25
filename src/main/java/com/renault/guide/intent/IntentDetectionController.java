package com.renault.guide.intent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntentDetectionController {
	@Autowired
	private IntentDetectionService intentDetectionService;

	@GetMapping("/intent")
	public String intent(String query) {
		try {
			return intentDetectionService.getIntentAndEntity(query);
		} catch (Exception e) {
			return "There has been an error.";
		}
	}
}
