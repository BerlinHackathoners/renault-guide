package com.renault.guide.intent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntentDetectionController {
	@Autowired
	private IntentDetectionService intentDetectionService;

	@GetMapping("/")
	public String base() {
		return "Hi!";
	}

	@GetMapping("/intent")
	public String intent(String query) {
		try {
			return intentDetectionService.getIntentAndEntity(query);
		} catch (Exception e) {
			return "I'm sorry, I didn't understand what you said.";
		}
	}
}
